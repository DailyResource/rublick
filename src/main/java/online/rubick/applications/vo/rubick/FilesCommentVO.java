package online.rubick.applications.vo.rubick;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class FilesCommentVO  {
    private String id;

    private String fileCode;

    private String commentUser;

    private String commentContent;

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

	public String getCommentUser() {
		return commentUser;
	}

	public void setCommentUser(String commentUser) {
		this.commentUser = commentUser;
	}

	public String getCommentContent() {
		return commentContent;
	}

	public void setCommentContent(String commentContent) {
		this.commentContent = commentContent;
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