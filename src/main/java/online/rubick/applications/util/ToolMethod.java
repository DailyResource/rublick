package online.rubick.applications.util;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.Collection;

public class ToolMethod {

	/**
	 * 根据指定字符串拼接集合
	 * 
	 * @param coll
	 * @param middle
	 * @return
	 */
	public static String join(Collection<String> coll, String middle) {
		StringBuilder sb = new StringBuilder();
		if (checkCollection(coll)) {
			for (String string : coll) {
				if (sb.length() > 0) {
					sb.append(middle == null ? "" : middle);
				}
				sb.append(string);
			}
		}
		return sb.toString();
	}

	/**
	 * 检查集合
	 * 
	 * @param coll
	 * @return
	 */
	public static boolean checkCollection(@SuppressWarnings("rawtypes") Collection coll) {
		if (coll == null || coll.isEmpty()) {
			return false;
		}
		return true;
	}

	/**
	 * 解决模糊查询时通配符的问题
	 * 
	 * @param target
	 * @return
	 */
	public static String escapeStringAsSql(String target) {
		return target == null ? target : target.replace("\\", "\\\\").replace("_", "\\_").replace("%", "\\%");
	}

	/**
	 * 浮点数乘法计算,无精度损失
	 * 
	 * @param v1
	 * @param v2
	 * @return
	 */
	public static double mul(String v1, String v2) {
		BigDecimal b1 = new BigDecimal(v1);
		BigDecimal b2 = new BigDecimal(v2);
		return b1.multiply(b2).doubleValue();
	}
	
	// 判断对象的属性值是否为null
		public static boolean checkObjFieldIsNull(Object obj) throws IllegalAccessException {
			boolean flag = false;
			for (Field f : obj.getClass().getDeclaredFields()) {
				f.setAccessible(true);
				if (f.get(obj) != null) {
					flag = true;
					return flag;
				}
			}
			return flag;
		}
}
