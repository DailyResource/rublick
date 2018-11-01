package online.rubick.applications.vo.sys;

import io.swagger.annotations.ApiModelProperty;

public class UpdatePasswordVO {

	private String passWord;
	
	private String newPassWord;
	
	private String confirmPassword;
	
	private String userId;

	@ApiModelProperty(value="当前用户的密码")
	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}
	@ApiModelProperty(value = "新密码")
	public String getNewPassWord() {
		return newPassWord;
	}

	public void setNewPassWord(String newPassWord) {
		this.newPassWord = newPassWord;
	}
	@ApiModelProperty(value = "确认密码")
	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
	@ApiModelProperty(value = "用户Id(必传)")
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
	@Override
	public String toString() {
		return "UpdatePasswordVO [passWord=" + passWord + ", newPassWord=" + newPassWord + ", confirmPassword="
				+ confirmPassword + ", userId=" + userId + "]";
	}
}
