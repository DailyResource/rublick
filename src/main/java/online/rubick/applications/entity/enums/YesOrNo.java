package online.rubick.applications.entity.enums;

/**
 * 可用枚举类;
 * @author Medeson.Zhang
 * @Date 2017年10月18日
 */
public enum YesOrNo implements EnumUnit<YesOrNo> {
	YES("Y","是"),
	NO("N","否");

	private String code;
	private String desc;
	
	private YesOrNo(String code,String desc){
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

	public static YesOrNo getEnumByCode(String code) {
		if(code.equals("Y"))
			return YES;
		else if(code.equals("N"))
			return NO;
		return null;
	}

}
