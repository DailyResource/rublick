package online.rubick.applications.entity.sys;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class SysMenu implements Serializable {
	private String id;

	private String catalogId;

	private String menuName;

	private String url;

	private String iconPath;

	private String description;

	private Date createTime;
	
	private String backUrl;

	private String status;
	private List<SysMenu> sonMenu;
	private static final long serialVersionUID = 1L;

	public List<SysMenu> getSonMenu() {
		return sonMenu;
	}

	public void setSonMenu(List<SysMenu> sonMenu) {
		this.sonMenu = sonMenu;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id == null ? null : id.trim();
	}

	public String getCatalogId() {
		return catalogId;
	}

	public void setCatalogId(String catalogId) {
		this.catalogId = catalogId == null ? null : catalogId.trim();
	}

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName == null ? null : menuName.trim();
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url == null ? null : url.trim();
	}

	public String getIconPath() {
		return iconPath;
	}

	public void setIconPath(String iconPath) {
		this.iconPath = iconPath == null ? null : iconPath.trim();
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description == null ? null : description.trim();
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status == null ? null : status.trim();
	}

	public String getBackUrl() {
		return backUrl;
	}

	public void setBackUrl(String backUrl) {
		this.backUrl = backUrl;
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
		SysMenu other = (SysMenu) that;
		return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
				&& (this.getCatalogId() == null ? other.getCatalogId() == null
						: this.getCatalogId().equals(other.getCatalogId()))
				&& (this.getMenuName() == null ? other.getMenuName() == null
						: this.getMenuName().equals(other.getMenuName()))
				&& (this.getUrl() == null ? other.getUrl() == null : this.getUrl().equals(other.getUrl()))
				&& (this.getIconPath() == null ? other.getIconPath() == null
						: this.getIconPath().equals(other.getIconPath()))
				&& (this.getDescription() == null ? other.getDescription() == null
						: this.getDescription().equals(other.getDescription()))
				&& (this.getCreateTime() == null ? other.getCreateTime() == null
						: this.getCreateTime().equals(other.getCreateTime()))
				&& (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()));
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
		result = prime * result + ((getCatalogId() == null) ? 0 : getCatalogId().hashCode());
		result = prime * result + ((getMenuName() == null) ? 0 : getMenuName().hashCode());
		result = prime * result + ((getUrl() == null) ? 0 : getUrl().hashCode());
		result = prime * result + ((getIconPath() == null) ? 0 : getIconPath().hashCode());
		result = prime * result + ((getDescription() == null) ? 0 : getDescription().hashCode());
		result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
		result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
		return result;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(getClass().getSimpleName());
		sb.append(" [");
		sb.append("Hash = ").append(hashCode());
		sb.append(", id=").append(id);
		sb.append(", catalogId=").append(catalogId);
		sb.append(", menuName=").append(menuName);
		sb.append(", url=").append(url);
		sb.append(", iconPath=").append(iconPath);
		sb.append(", description=").append(description);
		sb.append(", createTime=").append(createTime);
		sb.append(", status=").append(status);
		sb.append(", serialVersionUID=").append(serialVersionUID);
		sb.append("]");
		return sb.toString();
	}
}