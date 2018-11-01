package online.rubick.applications.vo.sys;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.hibernate.validator.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonFormat;
import online.rubick.applications.entity.enums.UserState;
import online.rubick.applications.entity.enums.YesOrNo;
import online.rubick.applications.security.authc.Account;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 用户对象
 * 
 * @author 张峻峰
 * @Date 2017年10月21日
 */
@ApiModel(value = "UserVO", description = "登录用户信息")
public class UserVO implements Account {
	
	//**************
	private String companyId;
	private String companyName;
	//**************
	
	
	
	@NotBlank(message = "请登陆后操作")
	private String userId;

	private String userName;

	private String loginId;

	private String loginIp;

	private String mobile;

	private String email;

	// 用户密码
	private String password;
	private String roleName;

	@JsonFormat(pattern="yyyy-MM-dd")
	private Date createDate;
	
	private List<String> permissions;

	private Date updateMyInfoTime;

	private String photoPath;

	// 新增字段 判断当前用户是否登录
	private YesOrNo islogin;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date loginTime;

	private String role;
	
	private String remark;
	
	private UserState state;
	
	private String userCompanyState;
	
	


	public String getUserCompanyState() {
		return userCompanyState;
	}

	public void setUserCompanyState(String userCompanyState) {
		this.userCompanyState = userCompanyState;
	}

	public UserState getState() {
		return state;
	}

	public void setState(UserState state) {
		this.state = state;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getCompanyId() {
		return companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserVO other = (UserVO) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (islogin != other.islogin)
			return false;
		if (loginId == null) {
			if (other.loginId != null)
				return false;
		} else if (!loginId.equals(other.loginId))
			return false;
		if (loginIp == null) {
			if (other.loginIp != null)
				return false;
		} else if (!loginIp.equals(other.loginIp))
			return false;
		if (loginTime == null) {
			if (other.loginTime != null)
				return false;
		} else if (!loginTime.equals(other.loginTime))
			return false;
		if (mobile == null) {
			if (other.mobile != null)
				return false;
		} else if (!mobile.equals(other.mobile))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (permissions == null) {
			if (other.permissions != null)
				return false;
		} else if (!permissions.equals(other.permissions))
			return false;
		if (photoPath == null) {
			if (other.photoPath != null)
				return false;
		} else if (!photoPath.equals(other.photoPath))
			return false;
		if (role == null) {
			if (other.role != null)
				return false;
		} else if (!role.equals(other.role))
			return false;
		if (roleName == null) {
			if (other.roleName != null)
				return false;
		} else if (!roleName.equals(other.roleName))
			return false;
		if (updateMyInfoTime == null) {
			if (other.updateMyInfoTime != null)
				return false;
		} else if (!updateMyInfoTime.equals(other.updateMyInfoTime))
			return false;
		if (userId == null) {
			if (other.userId != null)
				return false;
		} else if (!userId.equals(other.userId))
			return false;
		if (userName == null) {
			if (other.userName != null)
				return false;
		} else if (!userName.equals(other.userName))
			return false;
		return true;
	}

	public Date getCreateDate() {
		return createDate;
	}

	@ApiModelProperty(value = "用户邮箱")
	public String getEmail() {
		return email;
	}

	@ApiModelProperty(value = "判断当前用户是否登录")
	public YesOrNo getIslogin() {
		return islogin;
	}

	@ApiModelProperty(value = "登录名(增加用户时必传)")
	public String getLoginId() {
		return loginId;
	}

	@ApiModelProperty(value = "登录的IP地址")
	public String getLoginIp() {
		return loginIp;
	}

	@ApiModelProperty(value = "登录时间")
	public Date getLoginTime() {
		return loginTime;
	}

	@ApiModelProperty(value = "联系电话")
	public String getMobile() {
		return mobile;
	}

	@ApiModelProperty(value = "用户密码")
	public String getPassword() {
		return password;
	}

	public List<String> getPermissions() {
		return permissions;
	}

	@ApiModelProperty(value = "头像路径")
	public String getPhotoPath() {
		return photoPath;
	}

	public String getRole() {
		return role;
	}

	@ApiModelProperty(value = "角色名字")
	public String getRoleName() {
		return roleName;
	}

	// 预留，暂时不用
	@Override
	public Collection<String> getRoles() {
		return null;
	}

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@ApiModelProperty(value = "用户更新个人信息时间")
	public Date getUpdateMyInfoTime() {
		return updateMyInfoTime;
	}

	@ApiModelProperty(value = "用户ID")
	public String getUserId() {
		return userId;
	}

	// 预留，暂时不用
	@Override
	public long getUserID() {
		return 0;
	}

	@ApiModelProperty(value = "用户名")
	public String getUserName() {
		return userName;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((islogin == null) ? 0 : islogin.hashCode());
		result = prime * result + ((loginId == null) ? 0 : loginId.hashCode());
		result = prime * result + ((loginIp == null) ? 0 : loginIp.hashCode());
		result = prime * result + ((loginTime == null) ? 0 : loginTime.hashCode());
		result = prime * result + ((mobile == null) ? 0 : mobile.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((permissions == null) ? 0 : permissions.hashCode());
		result = prime * result + ((photoPath == null) ? 0 : photoPath.hashCode());
		result = prime * result + ((role == null) ? 0 : role.hashCode());
		result = prime * result + ((roleName == null) ? 0 : roleName.hashCode());
		result = prime * result + ((updateMyInfoTime == null) ? 0 : updateMyInfoTime.hashCode());
		result = prime * result + ((userId == null) ? 0 : userId.hashCode());
		result = prime * result + ((userName == null) ? 0 : userName.hashCode());
		return result;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setIslogin(YesOrNo islogin) {
		this.islogin = islogin;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	public void setLoginIp(String loginIp) {
		this.loginIp = loginIp;
	}

	public void setLoginTime(Date loginTime) {
		this.loginTime = loginTime;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setPermissions(List<String> permissions) {
		this.permissions = permissions;
	}

	public void setPhotoPath(String photoPath) {
		this.photoPath = photoPath;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public void setRoleName(String roleNames) {
		this.roleName = roleNames;
	}

	public void setUpdateMyInfoTime(Date updateMyInfoTime) {
		this.updateMyInfoTime = updateMyInfoTime;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	// 预留，暂时不用
	@Override
	public void setUserID(long userID) {
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Override
	public String toString() {
		return "UserVO [userId=" + userId + ", userName=" + userName + ", loginId=" + loginId + ", loginIp=" + loginIp
				+ ", mobile=" + mobile + ", email=" + email + ", password=" + password + ", roleName=" + roleName
				+ ", permissions=" + permissions + ", updateMyInfoTime=" + updateMyInfoTime + ", photoPath=" + photoPath
				+ ", islogin=" + islogin + ", loginTime=" + loginTime + ", role=" + role + "]";
	}
	
	

}
