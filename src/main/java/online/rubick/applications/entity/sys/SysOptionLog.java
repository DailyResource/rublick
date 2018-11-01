package online.rubick.applications.entity.sys;

import java.io.Serializable;
import java.util.Date;

public class SysOptionLog implements Serializable {
	private String id;

	private String userId;

	private Date optionTime;

	private String optionUrl;

	private String optionValue;

	private String optionInfo;
	private String name;
	private String roleName;
	private String mobile;

	private static final long serialVersionUID = 1L;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id == null ? null : id.trim();
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId == null ? null : userId.trim();
	}

	public Date getOptionTime() {
		return optionTime;
	}

	public void setOptionTime(Date optionTime) {
		this.optionTime = optionTime;
	}

	public SysOptionLog() {
		super();
	}

	public SysOptionLog(String id, String userId, Date optionTime, String optionUrl, String optionValue, String optionInfo,
			String name, String roleName, String mobile) {
		super();
		this.id = id;
		this.userId = userId;
		this.optionTime = optionTime;
		this.optionUrl = optionUrl;
		this.optionValue = optionValue;
		this.optionInfo = optionInfo;
		this.name = name;
		this.roleName = roleName;
		this.mobile = mobile;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getOptionUrl() {
		return optionUrl;
	}

	public void setOptionUrl(String optionUrl) {
		this.optionUrl = optionUrl == null ? null : optionUrl.trim();
	}

	public String getOptionValue() {
		return optionValue;
	}

	public void setOptionValue(String optionValue) {
		this.optionValue = optionValue == null ? null : optionValue.trim();
	}

	public String getOptionInfo() {
		return optionInfo;
	}

	public void setOptionInfo(String optionInfo) {
		this.optionInfo = optionInfo == null ? null : optionInfo.trim();
	}

	@Override
	public boolean equals(Object that) {
		if (this == that) {
			return true;
		}
		if (that == null) {
			return false;
		}
		if (getClass() != that.getClass()) {
			return false;
		}
		SysOptionLog other = (SysOptionLog) that;
		return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
				&& (this.getUserId() == null ? other.getUserId() == null : this.getUserId().equals(other.getUserId()))
				&& (this.getOptionTime() == null ? other.getOptionTime() == null
						: this.getOptionTime().equals(other.getOptionTime()))
				&& (this.getOptionUrl() == null ? other.getOptionUrl() == null
						: this.getOptionUrl().equals(other.getOptionUrl()))
				&& (this.getOptionValue() == null ? other.getOptionValue() == null
						: this.getOptionValue().equals(other.getOptionValue()))
				&& (this.getOptionInfo() == null ? other.getOptionInfo() == null
						: this.getOptionInfo().equals(other.getOptionInfo()));
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
		result = prime * result + ((getUserId() == null) ? 0 : getUserId().hashCode());
		result = prime * result + ((getOptionTime() == null) ? 0 : getOptionTime().hashCode());
		result = prime * result + ((getOptionUrl() == null) ? 0 : getOptionUrl().hashCode());
		result = prime * result + ((getOptionValue() == null) ? 0 : getOptionValue().hashCode());
		result = prime * result + ((getOptionInfo() == null) ? 0 : getOptionInfo().hashCode());
		return result;
	}

	@Override
	public String toString() {
		return "OptionLog [id=" + id + ", userId=" + userId + ", optionTime=" + optionTime + ", optionUrl=" + optionUrl
				+ ", optionValue=" + optionValue + ", optionInfo=" + optionInfo + ", name=" + name + ", roleName="
				+ roleName + ", mobile=" + mobile + "]";
	}
}