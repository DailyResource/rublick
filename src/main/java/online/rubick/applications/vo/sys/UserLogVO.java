package online.rubick.applications.vo.sys;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "UserLogVO", description = "用户登陆日志")
public class UserLogVO {
	
	private String logUser;
	
	private String logId;

    private String userId;
    
    private String action;

    private Date logTime;

    @ApiModelProperty(value = "登录用户")
    public String getLogUser() {
		return logUser;
	}

	public void setLogUser(String logUser) {
		this.logUser = logUser;
	}

	@ApiModelProperty(value = "用户登录的IP地址")
    public String getLogId() {
        return logId;
    }

    public void setLogId(String logId) {
        this.logId = logId;
    }
    @ApiModelProperty(value = "用户ID")
    public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
	@ApiModelProperty(value = "用户登录操作")
	public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action == null ? null : action.trim();
    }
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    public Date getLogTime() {
        return logTime;
    }

    public void setLogTime(Date logTime) {
        this.logTime = logTime;
    }
}
