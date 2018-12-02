package online.rubick.applications.vo.rubick;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class FilesFavoriteVO  {
    private String id;

    private String fileCode;

    private String favoriteUser;

    private String isFavorite;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFileCode() {
		return fileCode;
	}

	public void setFileCode(String fileCode) {
		this.fileCode = fileCode;
	}

	public String getFavoriteUser() {
		return favoriteUser;
	}

	public void setFavoriteUser(String favoriteUser) {
		this.favoriteUser = favoriteUser;
	}

	public String getIsFavorite() {
		return isFavorite;
	}

	public void setIsFavorite(String isFavorite) {
		this.isFavorite = isFavorite;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

  
}