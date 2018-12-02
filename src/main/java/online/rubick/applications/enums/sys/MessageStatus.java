package online.rubick.applications.enums.sys;

import org.springframework.util.StringUtils;

public enum MessageStatus {
	
	NO_READ("0","未读"),
	READED("1","已读");
	
	String code;
	
	String decription;
	
	

	private MessageStatus(String code, String decription) {
		this.code = code;
		this.decription = decription;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDecription() {
		return decription;
	}

	public void setDecription(String decription) {
		this.decription = decription;
	}

	public static MessageStatus getEnumByCode(String code) {
		if (StringUtils.isEmpty(code)) {
            return null;
        }
        for (MessageStatus element : MessageStatus.values()) {
            if (code.equals(element.getCode())) {
                return element;
            }
        }
        return null;
	}
}
