package online.rubick.applications.entity.enums.typehandler;

import org.apache.ibatis.type.MappedTypes;

import online.rubick.applications.entity.enums.YesOrNo;

/**
 * 为适配MyBatis;定向Useable枚举类的读取;
 */
@MappedTypes(YesOrNo.class)
public class YesOrNoHandler extends EnumCodeTypeHandler<YesOrNo>{

	public YesOrNoHandler() {
		super(YesOrNo.class);
	}

}
