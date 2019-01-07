package online.rubick.applications.enums.rubick;

import org.springframework.util.StringUtils;

import online.rubick.applications.enums.sys.EnumUnit;

public enum GroupTypeEnum implements EnumUnit<GroupTypeEnum>{

	DIY("0","用户自定义分组"),
	DROP("1","下落图标分组");

	private String code;
	private String desc;
	
	private GroupTypeEnum(String code,String desc){
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

	public static GroupTypeEnum getEnumByCode(String code) {
		if (StringUtils.isEmpty(code)) {
            return null;
        }
        for (GroupTypeEnum element : GroupTypeEnum.values()) {
            if (code.equals(element.getCode())) {
                return element;
            }
        }
        return null;
	}
}
