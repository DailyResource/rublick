package online.rubick.applications.enums.rubick;

import org.springframework.util.StringUtils;

import online.rubick.applications.enums.sys.EnumUnit;

public enum FileStatus implements EnumUnit<FileStatus>{

	DELETE("0","已刪除"),
	ONLINE("1","上架"),
	OFFLINE("2","下架"),
	READY("3","筹备中");

	private String code;
	private String desc;
	
	private FileStatus(String code,String desc){
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

	public static FileStatus getEnumByCode(String code) {
		if (StringUtils.isEmpty(code)) {
            return null;
        }
        for (FileStatus element : FileStatus.values()) {
            if (code.equals(element.getCode())) {
                return element;
            }
        }
        return null;
	}
}
