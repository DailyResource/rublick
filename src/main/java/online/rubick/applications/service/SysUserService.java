package online.rubick.applications.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.stereotype.Service;

import online.rubick.applications.dao.sys.SysUserLogMapper;
import online.rubick.applications.dao.sys.SysUserMapper;
import online.rubick.applications.entity.enums.UserState;
import online.rubick.applications.entity.enums.YesOrNo;
import online.rubick.applications.entity.sys.SysRole;
import online.rubick.applications.entity.sys.SysUser;
import online.rubick.applications.entity.sys.SysUserLog;
import online.rubick.applications.entity.sys.SysUserRole;
import online.rubick.applications.util.IdUtil;

/**
 * 系统用户相关服务
 * 
 * @author 张峻峰
 * @Date 2017年11月14日
 */
@Service
public class SysUserService {

	@Autowired
	private SysUserMapper userDao;

	@Autowired
	private SysUserLogMapper userLogDao;

	@Autowired
	private SysUserRoleService sysUserRoleService;

	@Autowired
	private SysRoleService sysRoleService;

	public List<SysUser> getUserByLoginId(String loginId) {
		return userDao.selectByLoginId(loginId);
	}

	public boolean createUser(SysUser user) {
		user.setUserId(IdUtil.getId() + "");
		user.setCreateDate(Calendar.getInstance().getTime());
		user.setState(UserState.INIT);
		PasswordEncoder passwordEncode = new StandardPasswordEncoder();
		String encode = passwordEncode.encode("123456");
		user.setPassword(encode);
		return userDao.insertSelective(user) > 0;
	}

	public boolean deleteUserById(String userId) {
		return userDao.deleteUserById(userId) > 0;
	}

	public boolean login(SysUser user) {
		SysUser updUser = new SysUser();
		updUser.setUserId(user.getUserId());
		updUser.setLoginIp(user.getLoginIp());
		updUser.setLoginTime(user.getLoginTime());
		updUser.setIslogin(YesOrNo.YES);
		userDao.updateByPrimaryKeySelective(updUser);

		SysUserLog userLog = new SysUserLog();
		userLog.setUserId(user.getUserId());
		userLog.setLogId(Long.toString(IdUtil.getId()));
		userLog.setLogTime(user.getLoginTime());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		userLog.setAction("在" + sdf.format(user.getLoginTime()) + "登录今创集团设备监控系统。登录IP为" + user.getLoginIp());
		userLogDao.insert(userLog);

		return true;
	}

	public boolean logout(SysUser user) {
		SysUser updUser = new SysUser();
		updUser.setUserId(user.getUserId());
		updUser.setIslogin(YesOrNo.NO);
		userDao.updateByPrimaryKeySelective(updUser);

		SysUserLog userLog = new SysUserLog();
		userLog.setUserId(user.getUserId());
		userLog.setLogId(Long.toString(IdUtil.getId()));
		Date curTime = Calendar.getInstance().getTime();
		userLog.setLogTime(curTime);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		userLog.setAction("在" + sdf.format(curTime) + "退出今创集团设备监控系统。");
		userLogDao.insert(userLog);

		return true;
	}

	public SysUser selectByPrimaryKey(String userId) {
		return userDao.selectByPrimaryKey(userId);
	}

	public boolean updateByPrimaryKeySelective(SysUser user) {

		return userDao.updateByPrimaryKeySelective(user) == 1;
	}

	public List<String> getPermissionListByUserId(String userId) {
		// 这里直接存角色 code了，如果要改permission，用下面的代码
		List<String> permissionList = new ArrayList<String>();
		List<SysUserRole> sysUserRoleList = sysUserRoleService.getByUserId(userId);
		for (SysUserRole userRole : sysUserRoleList) {
			SysRole role = sysRoleService.findById(userRole.getRoleId());
			if (null != role) {
				permissionList.add(role.getRoleCode());
			}
		}
		return permissionList;
	}

	public void updatePasswordByLoginId(String password, String loginId) {
		userDao.updatePasswordByLoginId(password, loginId);
	}

	public SysUser selectUserInfoByUserId(String id) {
		return userDao.selectByPrimaryKey(id);
	}

	public List<SysUser> getUserByMoble(String mobile) {
		return userDao.getUserByMoble(mobile);
	}

	public void updateClientId(SysUser user) {
		userDao.updateClientId(user);
	}

	public List<SysUser> selectAll() {
		return userDao.selectAll();
	}

	public Page<SysUser> getAllPage(String keyword, Pageable pageable) {
		return userDao.getAllPage(keyword, pageable);
	}

	public SysUser findByUserId(String userId) {
		return userDao.findByUserId(userId);
	}

	public SysUser findByLoginId(String loginId) {
		return userDao.findByLoginId(loginId);
	}

}
