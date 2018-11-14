package online.rubick.applications.controller.sys;

import java.io.InputStream;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.digest.DigestUtils;
import org.hibernate.validator.constraints.NotBlank;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.google.common.collect.Lists;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import online.rubick.applications.entity.enums.RoleType;
import online.rubick.applications.entity.sys.SysUser;
import online.rubick.applications.entity.sys.SysUserLog;
import online.rubick.applications.entity.sys.SysUserRole;
import online.rubick.applications.exception.ApplicationException;
import online.rubick.applications.security.annotation.SessionAccount;
import online.rubick.applications.service.SysUserLogService;
import online.rubick.applications.service.SysUserRoleService;
import online.rubick.applications.service.SysUserService;
import online.rubick.applications.util.FtpUtil;
import online.rubick.applications.vo.sys.UpdatePasswordVO;
import online.rubick.applications.vo.sys.UserLogVO;
import online.rubick.applications.vo.sys.UserVO;

@Api(description = "用户信息")
@RestController
@RequestMapping("/user")
@ResponseBody
public class UserController {
	@Autowired
	private SysUserService userService;
	@Autowired
	private SysUserLogService userLogService;
	@Autowired
	private SysUserRoleService userRoleService;

	private Logger log = LoggerFactory.getLogger(this.getClass());

	@ApiOperation(value = "重置密码", notes = "重置密码")
	@RequestMapping(method = RequestMethod.POST, value = "resetPassWord")
	public void resetPassWord(
			@RequestParam(value = "userId", required = true) @NotBlank(message = "用户名不能为空") String userId) {
		SysUser userInfo = null;
		// 2.根据用户的id，查询出用户的信息
		if (!StringUtils.isEmpty(userId)) {
			userInfo = userService.selectByPrimaryKey(userId);
		}
		if (userInfo == null) {
			throw new ApplicationException("登录信息异常");
		}
		// 3.验证用户的旧密码是否与数据库的密码一致
		PasswordEncoder passwordEncode = new StandardPasswordEncoder();
		// 先MD5加密，再由spring加密
		
		String md5Encode = DigestUtils.md5Hex("123456");
		String encode = passwordEncode.encode(md5Encode);
		userInfo.setPassword(encode);
		// 4.记录修改密码的时间
		userInfo.setChangePasswordTime(new Date());
		userService.updateByPrimaryKeySelective(userInfo);

	}

	@ApiOperation(value = "修改密码", notes = "修改密码", response = Map.class)
	@RequestMapping(method = RequestMethod.POST, value = "UpdatePassWord")
	public void UpdatePassWord(@RequestBody UpdatePasswordVO passWordVo, @SessionAccount UserVO userVO) {
		String newPassword = passWordVo.getNewPassWord();
		String confirmPassword = passWordVo.getConfirmPassword();
		String passWord = passWordVo.getPassWord();
		String userId = passWordVo.getUserId();
		// 1.判断两次输入的重置密码是否一致
		if (StringUtils.isEmpty(newPassword) || StringUtils.isEmpty(confirmPassword)
				|| !newPassword.equals(confirmPassword)) {
			throw new ApplicationException(402, "重置密码输入不一致");
		}
		SysUser userInfo = null;
		// 2.根据用户的id，查询出用户的信息
		if (!StringUtils.isEmpty(userId) && userVO.getUserId().equals(userId)) {
			userInfo = userService.selectByPrimaryKey(userId);
		}
		if (userInfo == null) {
			throw new ApplicationException("登录信息异常");
		}
		// 3.验证用户的旧密码是否与数据库的密码一致
		PasswordEncoder passwordEncoder = new StandardPasswordEncoder();
		if (passwordEncoder.matches(passWord, userInfo.getPassword())) {
			userInfo.setPassword(passwordEncoder.encode(newPassword));
			// 4.记录修改密码的时间
			userInfo.setChangePasswordTime(new Date());
			userService.updateByPrimaryKeySelective(userInfo);
		} else {
			throw new ApplicationException("您输入的密码有误");
		}
	}

	@ApiOperation(value = "查看我的信息", notes = "查看个人信息", response = UserVO.class)
	@RequestMapping(method = RequestMethod.GET, value = "CheckInfo")
	@ResponseBody
	public UserVO CheckInfo(@SessionAccount UserVO userVO) {
		if (StringUtils.isEmpty(userVO.getUserId())) {
			throw new ApplicationException("登录信息异常");
		}
		userVO.setPassword(null);
		List<SysUserRole> byUserId = userRoleService.getByUserId(userVO.getUserId());
		if (!CollectionUtils.isEmpty(byUserId)) {
			StringBuilder sb = new StringBuilder();
			for (SysUserRole sysUserRole : byUserId) {
				sb.append(RoleType.getEnumByCode(sysUserRole.getRoleId()).getMessage()).append(",");
			}
			userVO.setRoleName(sb.deleteCharAt(sb.length() - 1).toString());
		}
		return userVO;
	}

	@ApiOperation(value = "更新个人信息", notes = "更新个人信息", response = Map.class)
	@RequestMapping(method = RequestMethod.PUT, value = "UpdateMyInfo")
	@ResponseBody
	public void UpdateMyInfo(@RequestBody UserVO vo, @SessionAccount UserVO sessionUser) {
		SysUser user = new SysUser();
		if (vo != null) {
			// 更新个人信息时间
			vo.setUpdateMyInfoTime(new Date());
			BeanUtils.copyProperties(sessionUser, user);
			user.setName(vo.getUserName());
			user.setMobile(vo.getMobile());
			user.setEmail(vo.getEmail());
			user.setUserId(sessionUser.getUserId());
			userService.updateByPrimaryKeySelective(user);
			if (!StringUtils.isEmpty(vo.getUserName()))
				sessionUser.setUserName(vo.getUserName());
			if (!StringUtils.isEmpty(vo.getMobile()))
				sessionUser.setMobile(vo.getMobile());
			if (!StringUtils.isEmpty(vo.getEmail()))
				sessionUser.setEmail(vo.getEmail());
		} else
			throw new ApplicationException("提交数据无效");
	}

	@ApiOperation(value = "查询登陆日志", notes = "查询登录日志", response = UserLogVO.class)
	@RequestMapping(method = RequestMethod.GET, value = "getUserLog")
	@ResponseBody
	public Page<UserLogVO> SearchLoginLog(
			@ApiParam(name = "page", value = "页码") @RequestParam(value = "page", defaultValue = "0") Integer page,
			@ApiParam(name = "size", value = "页长") @RequestParam(value = "size", defaultValue = "10") Integer size,
			String userId, String logUser) {
		List<UserLogVO> volist = new LinkedList<UserLogVO>();
		Pageable pageable = new PageRequest(page, size);
		// 直接根据用户id，查询出登陆日志的所有信息
		Page<SysUserLog> sysUserLogPage = userLogService.getSysUserLogByUserId(userId, logUser, pageable);
		sysUserLogPage.forEach(new Consumer<SysUserLog>() {
			@Override
			public void accept(SysUserLog sysUserLog) {
				UserLogVO userLogVO = new UserLogVO();
				BeanUtils.copyProperties(sysUserLog, userLogVO);
				volist.add(userLogVO);
			}
		});
		return new PageImpl<>(volist, pageable, sysUserLogPage.getTotalElements());
	}

	@ApiResponses(value = { @ApiResponse(code = 200, message = "请求成功") })
	@ApiOperation(value = "上传头像", notes = "上传成功后，服务器将返回头像保存的地址信息。")
	@RequestMapping(value = "/uploadHeadPhoto", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> uploadHeadPhoto(@ApiParam(name = "file", value = "头像文件") MultipartFile file,
			@SessionAccount UserVO userVO) throws Exception {
//		// 1.首先判断id不为空
//		if (StringUtils.isEmpty(userVO.getUserId())) {
//			throw new ApplicationException("用户信息登录异常");
//		}
//		// 2.判断获取的文件信息是否为空
//		if (StringUtils.isEmpty(file)) {
//			throw new ApplicationException("读取文件发生错误");
//		}
//		Attachment attachment = new Attachment(file);
//		if (!StringUtils.isEmpty(attachment)) {
//			String path = fileSvc.uploadFile(userVO.getUserId(), attachment);
//			SysUser sysUser = new SysUser();
//			userVO.setPhotoPath(path);
//			BeanUtils.copyProperties(userVO, sysUser);
//			// 3.把路径保存到数据库
//			userService.updateByPrimaryKeySelective(sysUser);
//			// 4.把保存的路径返回给前台
//			HashMap<String, Object> hashMap = new HashMap<>();
//			hashMap.put("photoPath", path);
//			return hashMap;
//		}
		return null;
	}

	@ApiOperation(value = "用户头像下载", notes = "用户头像下载")
	@RequestMapping(method = RequestMethod.GET, value = "downloadHeadPhoto")
	public void downloadHeadPhoto(HttpServletResponse response, HttpServletRequest request,
			@SessionAccount UserVO userVO) throws Exception {
//		if (StringUtils.isEmpty(userVO.getUserId())) {
//			throw new ApplicationException("用户信息登录异常");
//		} else if (!StringUtils.isEmpty(userVO.getUserId())) {
//			SysUser sysUser = userService.selectByPrimaryKey(userVO.getUserId());
//			String photoPath = sysUser.getPhotoPath();
//			if (StringUtils.isEmpty(photoPath)) {
//				response.setCharacterEncoding("utf-8");
//				response.getWriter().print("没有相关的图片信息");
//			} else {
//				byte[] downloadFile = fileSvc.downloadFile(photoPath);
//				if (downloadFile == null) {
//					response.setCharacterEncoding("utf-8");
//					response.getWriter().print("没有相关的图片信息");
//				} else {
//					response.setHeader("Cache-Control", "no-store");
//					response.setHeader("Pragma", "no-cache");
//					response.setDateHeader("Expires", 0);
//					response.setContentType("image/jpeg");
//					ServletOutputStream responseOutputStream = response.getOutputStream();
//					responseOutputStream.write(downloadFile);
//					responseOutputStream.flush();
//					responseOutputStream.close();
//				}
//			}
//		}
	}

	@ApiOperation(value = "新增用户", notes = "新增用户", response = UserVO.class)
	@PostMapping("/add")
	public void add(@RequestBody UserVO vo) {
		if (vo == null) {
			throw new ApplicationException("请提交有效数据");
		}
		SysUser user = userService.findByLoginId(vo.getLoginId());
		if (user != null) {
			throw new ApplicationException("该用户已存在,请重新输入");
		}
		List<SysUser> users = userService.findByMobile(vo.getMobile());
		if (!CollectionUtils.isEmpty(users)) {
			throw new ApplicationException("该手机号码已被注册");
		}
		SysUser sysUser = new SysUser();
		BeanUtils.copyProperties(vo, sysUser);
		sysUser.setName(vo.getUserName());
		try {
			userService.createUser(sysUser);
		} catch (Exception e) {
			log.info("异常信息", e);
		}
	}

	// 删除用户信息 软删除 更改用户状态
	@ApiOperation(value = "删除用户", notes = "删除用户")
	@DeleteMapping("/delete")
	public void delete(
			@ApiParam(name = "userId", value = "用户id") @RequestParam(value = "userId", required = true) String userId) {
		try {
			userService.deleteUserById(userId);
		} catch (Exception e) {
			log.info(e.getMessage());
			throw new ApplicationException("删除失败");
		}
	}

	@ApiOperation(value = "修改用户", notes = "修改用户")
	@PostMapping("/update")
	public void update(@RequestBody UserVO vo) {
		if (vo == null) {
			throw new ApplicationException("请提交有效数据");
		}
		List<SysUser> users = userService.findByMobile(vo.getMobile());
		if (!CollectionUtils.isEmpty(users)) {
			String userId = users.get(0).getUserId();
			if (!vo.getUserId().equals(userId)) {
				throw new ApplicationException("该手机号码已被注册");
			}
		}
		try {
			userService.updateUser(vo);
		} catch (Exception e) {
			log.info("异常信息", e);
		}
	}

	// 用户列表
	@ApiOperation(value = "用户列表查询", notes = "用户列表查询")
	@GetMapping("/list")
	public Page<UserVO> list(
			@ApiParam(name = "keyword", value = "keyword") @RequestParam(value = "keyword", required = false) String keyword,
			@ApiParam(name = "page", value = "页码") @RequestParam(value = "page", defaultValue = "0") Integer page,
			@ApiParam(name = "size", value = "条数") @RequestParam(value = "size", defaultValue = "10") Integer size) {
		Pageable pageable = new PageRequest(page, size);
		Page<SysUser> listPage = userService.getAllPage(keyword, pageable);
		List<UserVO> newArrayList = Lists.newArrayList();
		for (SysUser user : listPage) {
			UserVO userVO = new UserVO();
			BeanUtils.copyProperties(user, userVO);
			userVO.setUserName(user.getName());
			userVO.setPassword(null);
			newArrayList.add(userVO);
		}
		return new PageImpl<UserVO>(newArrayList, pageable, listPage.getTotalElements());
	}

	// 根据loginid查询用户信息
	@ApiOperation(value = "根据登录id查询", notes = "根据登录id查询")
	@GetMapping("/findByLoginId")
	public void findByLoginId(
			@ApiParam(name = "loginId", value = "账号id") @RequestParam(value = "loginId", required = true) String loginId,
			@ApiParam(name = "userId", value = "用户id") @RequestParam(value = "userId", required = false) String userId) {
		if (StringUtils.isEmpty(userId)) {
			SysUser sysUser = userService.findByLoginId(loginId);
			if (sysUser != null) {
				throw new ApplicationException("该账号已存在");
			}
		} else {
			SysUser sysUser = userService.findByLoginId(loginId);
			if (sysUser != null) {
				if (!sysUser.getUserId().equals(userId)) {
					throw new ApplicationException("该账号已存在");
				}
			}
		}
	}

	// 根据用户手机号码查询用户信息
	@ApiOperation(value = "根据手机号码查询", notes = "根据手机号码查询")
	@GetMapping("/findByMobile")
	public void findByMobile(
			@ApiParam(name = "mobile", value = "手机号码") @RequestParam(value = "mobile", required = true) String mobile,
			@ApiParam(name = "userId", value = "用户id") @RequestParam(value = "userId", required = false) String userId) {
		List<SysUser> users = userService.findByMobile(mobile);
		if (StringUtils.isEmpty(userId)) {
			if (!CollectionUtils.isEmpty(users)) {
				throw new ApplicationException("该手机号码已被注册");
			}
		} else {
			if (!CollectionUtils.isEmpty(users)) {
				String userIdOri = users.get(0).getUserId();
				if (!userId.equals(userIdOri)) {
					throw new ApplicationException("该手机号码已被注册");
				}
			}
		}
	}
	
    @ApiOperation(value = "上传升级文件", notes = "上传成功后，服务器将返回升级文件保存的地址信息")
    @PostMapping("/upgradeFileUpload")
	 public void upgradeFileUpload(@ApiParam(name = "file", value = "头像文件") MultipartFile file) throws Exception {
		// Sftp上传文件
// 		SftpUtil sftp = new SftpUtil("lihe", "lihe2018", "10.0.5.239", 2020);
// 		sftp.login(); InputStream is = file.getInputStream();
// 		sftp.upload("dms", file.getOriginalFilename(), is); sftp.logout();
		// ftp上传文件
        InputStream is = file.getInputStream();
        FtpUtil ftp = new FtpUtil();
        ftp.uploadFile("dms", file.getOriginalFilename(), is);
        // 将跟新文件目录存入数据库
//        DmsUpdateFile dmsUpdateFile = new DmsUpdateFile();
//        dmsUpdateFile.setId(String.valueOf(IdUtil.getId()));
//        dmsUpdateFile.setUpdateFileName(file.getOriginalFilename());
//        dmsUpdateFile.setUpdateFilePath("dms");
//        dmsUpdateFile.setUploadTime(new Date());
	}
}
