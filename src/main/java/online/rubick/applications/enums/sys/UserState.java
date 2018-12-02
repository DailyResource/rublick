package online.rubick.applications.enums.sys;

import org.springframework.util.StringUtils;

/**
 * 处理状态枚举类;
 */
public enum UserState implements EnumUnit<UserState> {
	INIT("0","初始化"),
	ENABLE("1","正常"),
	LOCKED("2","锁定"),
	DELETED("9","已删除");

	private String code;
	private String desc;
	
	private UserState(String code,String desc){
		this.code = code;
		this.desc = desc;
	}
	
	@Override
	public String getCode() {
		return code;
	}

	@Override
	public String getDescription() {
		return desc;
	}

	public static UserState getEnumByCode(String code) {
		if (StringUtils.isEmpty(code)) {
            return null;
        }
        for (UserState element : UserState.values()) {
            if (code.equals(element.getCode())) {
                return element;
            }
        }
        return null;
	}

}
