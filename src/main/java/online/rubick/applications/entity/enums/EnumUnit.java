package online.rubick.applications.entity.enums;

/**
 * Enum 接口,标准化系统内所有Enum的读取方式;
 * @author Medeson.Zhang
 * @Date 2017年10月18日
 */
public interface EnumUnit<T> {
    /**
     * 获取枚举的编码值;
     * @return 枚举实例的值
     */
    String getCode();
    
    /**
     * 获取枚举实例的描述信息
     * @return 描述信息
     */
    String getDescription();
    
}
