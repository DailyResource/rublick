package online.rubick.applications.entity.sys;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import online.rubick.applications.enums.sys.UserState;
import online.rubick.applications.enums.sys.YesOrNo;

public class SysUser implements Serializable,UserDetails{
	//********************
	private String companyName;
	private String companyId;
	private String userCompanyState;
	//*********************
	
	public static final String system = "SYSTEM";
	private String userId;

	private String loginId;

	private String name;

	private String mobile;

	private String email;

	private String password;

	private String role;

	private String roleName;

	private String projectName;

	private String project;

	private UserState state;

	private Date createDate;

	private Date loginTime;

	private String loginIp;

	private YesOrNo islogin;
	
	private String orderBy;
	
	private String clientId;

	private String appType;
	
	private String remark;
	
	
	
	public String getUserCompanyState() {
		return userCompanyState;
	}

	public void setUserCompanyState(String userCompanyState) {
		this.userCompanyState = userCompanyState;
	}

	public String getCompanyId() {
		return companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getAppType() {
		return appType;
	}

	public void setAppType(String appType) {
		this.appType = appType;
	}

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public String getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}

	List<String> projects;
	// 新增字段 修改密码时间
	private Date changePasswordTime;
	// 新增字段 更新个人信息时间
	private Date updateMyInfoTime;

	private String photoPath;

	private List<String> roles;

	public String getPhotoPath() {
		return photoPath;
	}

	public void setPhotoPath(String photoPath) {
		this.photoPath = photoPath;
	}

	private static final long serialVersionUID = 1L;

	public List<String> getRoles() {
		return roles;
	}

	public void setRoles(List<String> roles) {
		this.roles = roles;
	}

	public List<String> getProjects() {
		return projects;
	}

	public void setProjects(List<String> projects) {
		this.projects = projects;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId == null ? null : userId.trim();
	}

	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId == null ? null : loginId.trim();
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getProject() {
		return project;
	}

	public void setProject(String project) {
		this.project = project;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name == null ? null : name.trim();
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile == null ? null : mobile.trim();
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email == null ? null : email.trim();
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password == null ? null : password.trim();
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role == null ? null : role.trim();
	}

	public UserState getState() {
		return state;
	}

	public void setState(UserState state) {
		this.state = state;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getLoginTime() {
		return loginTime;
	}

	public void setLoginTime(Date loginTime) {
		this.loginTime = loginTime;
	}

	public String getLoginIp() {
		return loginIp;
	}

	public void setLoginIp(String loginIp) {
		this.loginIp = loginIp == null ? null : loginIp.trim();
	}

	public YesOrNo getIslogin() {
		return islogin;
	}

	public void setIslogin(YesOrNo islogin) {
		this.islogin = islogin;
	}

	public Date getChangePasswordTime() {
		return changePasswordTime;
	}

	public void setChangePasswordTime(Date changePasswordTime) {
		this.changePasswordTime = changePasswordTime;
	}

	public Date getUpdateMyInfoTime() {
		return updateMyInfoTime;
	}

	public void setUpdateMyInfoTime(Date updateMyInfoTime) {
		this.updateMyInfoTime = updateMyInfoTime;
	}

	@Override  
	public boolean equals(Object obj) {  
	    if (obj instanceof SysUser && this.hashCode() == obj.hashCode()) {  
	        return true;  
	    } else {  
	        return false;  
	    }  
	}  
	  
	@Override  
	public int hashCode() {  
	    return this.loginId.hashCode() + this.password.hashCode();  
	}  

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(getClass().getSimpleName());
		sb.append(" [");
		sb.append("Hash = ").append(hashCode());
		sb.append(", userId=").append(userId);
		sb.append(", loginId=").append(loginId);
		sb.append(", name=").append(name);
		sb.append(", mobile=").append(mobile);
		sb.append(", email=").append(email);
		sb.append(", password=").append(password);
		sb.append(", role=").append(role);
		sb.append(", state=").append(state);
		sb.append(", createDate=").append(createDate);
		sb.append(", loginTime=").append(loginTime);
		sb.append(", loginIp=").append(loginIp);
		sb.append(", islogin=").append(islogin);

		sb.append(", changePasswordTime=").append(changePasswordTime);
		sb.append(", updateMyInfoTime=").append(updateMyInfoTime);
		sb.append(", photoPath=").append(photoPath);

		sb.append(", serialVersionUID=").append(serialVersionUID);
		sb.append("]");
		return sb.toString();
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return false;
	}
}