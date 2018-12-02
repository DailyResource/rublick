package online.rubick.applications.enums.typehandler;

import org.apache.ibatis.type.MappedTypes;

import online.rubick.applications.enums.sys.UserState;

/**
 * 为适配MyBatis;定向Useable枚举类的读取;
 */
@MappedTypes(UserState.class)
public class UserStateHandler extends EnumCodeTypeHandler<UserState>{

	public UserStateHandler() {
		super(UserState.class);
	}

}
