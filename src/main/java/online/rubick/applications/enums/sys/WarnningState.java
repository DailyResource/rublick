package online.rubick.applications.enums.sys;

import org.springframework.util.StringUtils;

public enum WarnningState {

	NOTDEAL("1","未处理"),
	DEAL("2","已处理");
	
	String code;
	
	String decription;
	

	private WarnningState(String code, String decription) {
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
	
	public static WarnningState getEnumByCode(String code) {
		if (StringUtils.isEmpty(code)) {
            return null;
        }
        for (WarnningState element : WarnningState.values()) {
            if (code.equals(element.getCode())) {
                return element;
            }
        }
        return null;
	}
}
