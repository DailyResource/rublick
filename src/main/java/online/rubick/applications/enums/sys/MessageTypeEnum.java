package online.rubick.applications.enums.sys;

import org.springframework.util.StringUtils;

public enum MessageTypeEnum {
	
	SYS_MESS("SYS","系统通知"),
	SMS_MESS("SMS","手机短信");
	
	String code;
	
	String description;
	

	private MessageTypeEnum(String code, String description) {
		this.code = code;
		this.description = description;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public static MessageTypeEnum getEnumByCode(String code) {
		if (StringUtils.isEmpty(code)) {
            return null;
        }
        for (MessageTypeEnum element : MessageTypeEnum.values()) {
            if (code.equals(element.getCode())) {
                return element;
            }
        }
        return null;
	}
}
