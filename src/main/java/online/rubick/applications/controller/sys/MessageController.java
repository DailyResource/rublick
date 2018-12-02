package online.rubick.applications.controller.sys;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpSession;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;
import org.hibernate.validator.constraints.NotBlank;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import online.rubick.applications.entity.sys.SysUser;
import online.rubick.applications.enums.sys.MessageStatus;
import online.rubick.applications.exception.ApplicationException;
import online.rubick.applications.query.MessageManagementQuery;
import online.rubick.applications.security.annotation.SessionAccount;
import online.rubick.applications.service.SysMessageService;
import online.rubick.applications.service.SysUserService;
import online.rubick.applications.util.DateUtil;
import online.rubick.applications.vo.sys.MessCodeVO;
import online.rubick.applications.vo.sys.MessageVo;
import online.rubick.applications.vo.sys.UserVO;

@Api(description = "消息")
@RestController
@RequestMapping("/message")
public class MessageController {

	private Logger log = LoggerFactory.getLogger(this.getClass());

	// 短信验证码过期时间
	private static final int TIMEOUT = 5 * 60;

	// 拼接在手机号码前
	private static final String PRE_STR = "letstech";
	// 拼接在手机号码后，组成明文
	private static final String POST_STR = "iepm";

	@Autowired
	private SysMessageService messageService;
	@Autowired
	private SysUserService sysUserService;
	@Autowired
	private RedisTemplate<String, Object> template;

	@ApiOperation(value = "消息管理条件分页查询", notes = "消息管理条件分页查询")
	@GetMapping(value = "/messageAdministrationPage")
	public Page<MessageVo> messageAdministrationPage(@ModelAttribute MessageManagementQuery query,
			@SessionAccount UserVO userVo) {
		query.setUserId(userVo.getUserId());
		Pageable pageable = new PageRequest(query.getPage(), query.getSize());
		Page<MessageVo> pageList = messageService.selectAllMessage(query, pageable);
		ArrayList<MessageVo> voList = new ArrayList<MessageVo>();
		for (MessageVo MessageVo : pageList) {
			MessageVo vo = new MessageVo();
			BeanUtils.copyProperties(MessageVo, vo);
			voList.add(vo);
		}
		return new PageImpl<MessageVo>(voList, pageable, pageList.getTotalElements());
	}

	@ApiOperation(value = "根据Id将未读消息状态标记为已读", notes = "将未读消息状态标记为已读")
	@GetMapping(value = "/updateReadFlagById")
	public void getupdateReadFlagById(@RequestParam(value = "id") String Id, @SessionAccount UserVO userVO) {
		// 根据登录用户的id找出登录人的姓名
		SysUser selectByPrimaryKey = sysUserService.selectByPrimaryKey(userVO.getUserId());
		if (!StringUtils.isEmpty(Id)) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date date = new Date();
			try {
				messageService.updateReadFlagById(MessageStatus.READED.getCode(), selectByPrimaryKey.getName(),
						sdf.format(date), Id);
			} catch (Exception e) {
				log.info(e.getMessage());
				throw new ApplicationException("修改信息已读状态失败");
			}
		} else {
			throw new ApplicationException("输入的id不能为空");
		}
	}

	@ApiOperation(value = "将所有未读消息状态标记为已读", notes = "将所有未读消息状态标记为已读")
	@GetMapping(value = "/updateReadFlagAll")
	public void getupdateReadFlagAll(@ModelAttribute MessageManagementQuery query, @SessionAccount UserVO userVO) {
		// 加入登录用户的id关联查询所属公司
		// 根据登录用户的id找出登录人的姓名
		SysUser selectByPrimaryKey = sysUserService.selectByPrimaryKey(userVO.getUserId());
		// 查询出所有未读消息的id集合
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date();
		// 添加搜索未读的条数
		query.setReadFlag(MessageStatus.NO_READ.getCode());
		// 根据条件进行搜索相关的未读信息id
		List<String> Ids = messageService.selectAllMessageByAllNORead(query);
		try {
			// 循环修改所有状态
			for (String string : Ids) {
				messageService.updateReadFlagById(MessageStatus.READED.getCode(), selectByPrimaryKey.getName(),
						sdf.format(date), string);
			}
		} catch (Exception e) {
			log.info(e.getMessage());
			throw new ApplicationException("修改信息已读状态失败");
		}
	}

	@ApiOperation(value = "发送短信验证码(小程序)", notes = "发送短信验证码(小程序)")
	@GetMapping(value = "/sendMessCode")
	public void sendMessCode(@RequestParam(value = "mobile", required = true) String mobile,
			@RequestParam(value = "secret", required = true) String secret) {
		// 首先需要校驗該手機號碼 在用戶表内存在(账户要为正常在用状态)
		List<SysUser> userList = sysUserService.findByMobile(mobile);
		if (!CollectionUtils.isEmpty(userList)) {
			String clearStr = PRE_STR + mobile + POST_STR;
			String cipher = DigestUtils.sha1Hex(clearStr);
			if (cipher.equals(secret)) {
				// 密文校验成功
				Object mess = template.opsForValue().get("CACHE" + mobile);
				if (mess != null) {
					MessCodeVO messCode = (MessCodeVO) mess;
					String oldMobile = messCode.getMobile();
					Date time = DateUtil.strToDate(messCode.getTime(), DateUtil.YYYYMMDDHHMMSS_PATTERN);
					time = new Date(time.getTime() + 1 * 60 * 1000);
					Date endDate = new Date();
					if (oldMobile.equals(mobile) && endDate.getTime() < time.getTime()) {// 一分钟以内禁止重复发送
						throw new ApplicationException("验证码已发送,一分钟内不能重复发送");
					}
				}
				String code = messageService.sendMessCode(mobile);
				MessCodeVO messCode = new MessCodeVO();
				messCode.setCode(code);
				messCode.setMobile(mobile);
				messCode.setTime(DateUtil.getNowDateString());
				// 设置5分钟过期时间
				template.opsForValue().set("CACHE" + mobile, messCode, TIMEOUT, TimeUnit.SECONDS);
			} else {
				throw new ApplicationException("非法请求");
			}
		} else {
			throw new ApplicationException("该用户不存在");
		}
	}

	@ApiOperation(notes = "忘记密码:验证码校验", value = "忘记密码:验证码校验")
	@PostMapping("/checkVerificationCode")
	public void checkVerificationCode(
			@NotBlank(message = "验证码不能为空") @RequestParam(value = "code", required = false) String code,
			@RequestParam(value = "loginId", required = true) @NotBlank(message = "用户名不能为空") String loginId,
			@RequestParam(value = "secret", required = true) String secret, HttpSession session) {
		List<SysUser> userList = sysUserService.getUserByLoginId(loginId);
		if (CollectionUtils.isEmpty(userList)) {
			throw new ApplicationException("用户名不存在");
		} else {
			session.setAttribute("loginId", loginId);
		}
		String vrifyCode = (String) session.getAttribute("vrifyCode");
		if (!code.equalsIgnoreCase(vrifyCode)) {
			throw new ApplicationException("验证码错误");
		}
		// 取消短信发送按钮，验证码正确之后，直接调用发送短信验证码
		sendMessCodeWeb(session, loginId, secret);
	}

	@ApiOperation(value = "发送短信验证码(web)", notes = "发送短信验证码(web)")
	@GetMapping(value = "/sendMessCodeWeb")
	public void sendMessCodeWeb(HttpSession session, @RequestParam(value = "loginId", required = false) String loginId,
			@RequestParam(value = "secret", required = true) String secret) {
		if (org.springframework.util.StringUtils.isEmpty(loginId)) {
			throw new ApplicationException("用户名不能为空");
		}
		List<SysUser> userList = sysUserService.getUserByLoginId(loginId);
		if (!CollectionUtils.isEmpty(userList)) {
			String clearStr = PRE_STR + loginId + POST_STR;
			String cipher = DigestUtils.sha1Hex(clearStr);
			if (cipher.equals(secret)) {
				String mobile = userList.get(0).getMobile();
				if (org.springframework.util.StringUtils.isEmpty(mobile)) {
					throw new ApplicationException("该用户联系电话为空");
				}
				Object mess = template.opsForValue().get("CACHE" + mobile);
				if (mess != null) {
					MessCodeVO messCode = (MessCodeVO) mess;
					String oldMobile = messCode.getMobile();
					Date time = DateUtil.strToDate(messCode.getTime(), DateUtil.YYYYMMDDHHMMSS_PATTERN);
					time = new Date(time.getTime() + 1 * 60 * 1000);
					Date endDate = new Date();
					if (oldMobile.equals(mobile) && endDate.getTime() < time.getTime()) {// 一分钟以内禁止重复发送
						throw new ApplicationException("验证码已发送,一分钟内不能重复发送");
					}
				}
				String code = messageService.sendMessCode(mobile);
				MessCodeVO messCode = new MessCodeVO();
				messCode.setCode(code);
				messCode.setMobile(mobile);
				messCode.setTime(DateUtil.getNowDateString());
				// 设置5分钟过期时间
				template.opsForValue().set("CACHE" + mobile, messCode, TIMEOUT, TimeUnit.SECONDS);
			} else {
				throw new ApplicationException("非法请求");
			}
		} else {
			throw new ApplicationException("该用户不存在");
		}
	}

	@ApiOperation(notes = "忘记密码:密码重置", value = "忘记密码:密码重置")
	@PostMapping("/resetPasswords")
	public void resetPasswords(
			@RequestParam(value = "password", required = false) @NotBlank(message = "密码不能为空") String password,
			@RequestParam(value = "confirmPassword", required = false) @NotBlank(message = "确认密码不能为空") String confirmPassword,
			@RequestParam(value = "messageCode", required = false) @NotBlank(message = "短信验证码不能为空") String messageCode,
			HttpSession session) {
		if (!password.equals(confirmPassword)) {
			throw new ApplicationException("两次输入的密码不一致");
		}
		String loginId = (String) session.getAttribute("loginId");
		List<SysUser> userList = sysUserService.getUserByLoginId(loginId);
		if (!CollectionUtils.isEmpty(userList)) {
			String mobile = userList.get(0).getMobile();
			if (org.springframework.util.StringUtils.isEmpty(mobile)) {
				throw new ApplicationException("该用户联系电话为空");
			}
			Object mess = template.opsForValue().get("CACHE" + mobile);
			if (mess != null) {
				MessCodeVO messCode = (MessCodeVO) mess;
				if (messageCode.equals(messCode.getCode())) {
					sysUserService.updatePasswordByLoginId(new StandardPasswordEncoder().encode(password),
							(String) session.getAttribute("loginId"));
				} else {
					throw new ApplicationException("手机验证码错误");
				}
			} else {
				throw new ApplicationException("验证码已过期");
			}
			sysUserService.updatePasswordByLoginId(new StandardPasswordEncoder().encode(password), loginId);
		} else {
			throw new ApplicationException("该用户不存在");
		}

	}
}
