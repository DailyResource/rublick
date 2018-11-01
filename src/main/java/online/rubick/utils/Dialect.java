package online.rubick.utils;

/**
 * 分页代理.不同的数据库需要做单独实现;
 * @author Medeson.Zhang
 * @Date 2017年10月19日
 */
public interface Dialect {
	
	/**
	 * 获取当前页的sql语句
	 * @param sql 原SQL查询语句
	 * @param offset 开始位置
	 * @param limit 笔数
	 * @return 重新构造的SQL语句
	 */
	String getPageSql(String sql, int offset, int limit);
	
	/**
	 * 获取总笔数的sql语句
	 * @param sql SQL语句
	 * @return sql语句
	 */
	String getCountSql(String sql);
}
