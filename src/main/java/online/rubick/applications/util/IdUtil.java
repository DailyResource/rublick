package online.rubick.applications.util;

import org.springframework.util.StringUtils;

/**
 * ID生成器工具类；使用此类，需要在系统环境变量添加参数：LETSIOT_WORKID；例如：LETSIOT_WORKID=1；在分布式环境下，各服务器需要设置不一样的值
 */
public class IdUtil {

    private static final long WORK_ID;

    static {
    	String workId = System.getenv("LETSIOT_WORKID");
    	if (StringUtils.isEmpty(workId)){
    		throw new RuntimeException("需要配置LETSIOT_WORKID环境变量，值为整数");
    	}
        WORK_ID = Long.valueOf(workId);
    }

    private static IdGenerator idGenerator = new IdGenerator(WORK_ID);

    public static synchronized long getId() {
        return idGenerator.nextId();
    }

}
