package online.rubick.applications.entity.sys;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class SysMessage implements Serializable {
	//0,发送失败；1,发送成功 status
	public static final String STATUS_FIELD= "0";
	public static final String STATUS_SUCCESS = "1";
	//1,系统通知类消息；2，短信发送信息
	//1,系统通知类消息；2，短信发送信息  messageType
	//是否已读。0，未读，1，已读
	public static final String READFLAG_FIELD= "0";
	public static final String READFLAG_SUCCESS = "1";
	
    private String id;
    
    private String deviceId;
    
    private String title;
    //类型为系统通知时，此字段为发布人；类型为短信时，此字段为联系人
    private String contactUser;
    //**********************
    //发布人名称
    private String username;
    //**********************

    private String contactTel;

    private Date sendTime;

    private Integer sendNum;

    private Integer resendNum;

    private String status;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    
    private String content;
    
    private String messageType;
    
    private String readFlag;

    private String warnType;
    
    

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getWarnType() {
		return warnType;
	}

	public void setWarnType(String warnType) {
		this.warnType = warnType;
	}

	public String getMessageType() {
		return messageType;
	}

	public void setMessageType(String messageType) {
		this.messageType = messageType;
	}

	private static final long serialVersionUID = 1L;

    public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getContactUser() {
        return contactUser;
    }

    public void setContactUser(String contactUser) {
        this.contactUser = contactUser == null ? null : contactUser.trim();
    }

    public String getContactTel() {
        return contactTel;
    }

    public void setContactTel(String contactTel) {
        this.contactTel = contactTel == null ? null : contactTel.trim();
    }

    public Date getSendTime() {
        return sendTime;
    }

    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }

    public Integer getSendNum() {
        return sendNum;
    }

    public void setSendNum(Integer sendNum) {
        this.sendNum = sendNum;
    }

    public Integer getResendNum() {
        return resendNum;
    }

    public void setResendNum(Integer resendNum) {
        this.resendNum = resendNum;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }


	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getReadFlag() {
		return readFlag;
	}

	public void setReadFlag(String readFlag) {
		this.readFlag = readFlag;
	}

}