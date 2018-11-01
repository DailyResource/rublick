package online.rubick.applications.vo.sys;

import java.io.Serializable;
import java.util.Date;

import org.hibernate.validator.constraints.NotBlank;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import online.rubick.applications.vaild.InsertVaild;
import online.rubick.applications.vaild.UpdateVaild;

/**
 * @author admin
 *
 */
@ApiModel(description = "角色管理参数列表")
public class SysRoleVO  implements Serializable{
	private static final long serialVersionUID = 1L;
	@ApiModelProperty("id")
	@NotBlank(message="{id.isNull}",groups= {UpdateVaild.class})
	private String id;
	@ApiModelProperty("角色编号")
	@NotBlank(message="{role.isNull}",groups= {InsertVaild.class})
	private String roleCode;
	@ApiModelProperty("创建时间")
	private Date createTime;
	@ApiModelProperty(hidden = true)
	private String status;
	@ApiModelProperty("角色名称")
	@NotBlank(message="{name.isNull}",groups= {InsertVaild.class,UpdateVaild.class})
	private String roleName;
	@ApiModelProperty("描述")
	private String description;
	@ApiModelProperty("修改时间")
	private Date updateTime;
	private String meuns;
	public String getId() {
		return id;
	}
	
	public String getMeuns() {
		return meuns;
	}

	public void setMeuns(String meuns) {
		this.meuns = meuns;
	}

	public void setId(String id) {
		this.id = id;
	}
	public String getRoleCode() {
		return roleCode;
	}
	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
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
		this.status = status;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	@Override
	public String toString() {
		return "SysRoleVO [id=" + id + ", roleCode=" + roleCode + ", createTime=" + createTime + ", status=" + status
				+ ", roleName=" + roleName + ", description=" + description + ", updateTime=" + updateTime + "]";
	}
	
}
