package online.rubick.utils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.util.StringUtils;

/**
 *   
 * 
 * @Description:日期工具类
 * @author Jack 
 * @date 2017年9月2日 下午2:33:46
 * @version V1.0  
 */
public class DateHelper {

	/**
	 * 日期格式yyyy-MM-dd
	 */
	public static final String pattern_date = "yyyy-MM-dd";

	/**
	 * 日期时间格式yyyy-MM-dd HH:mm:ss
	 */
	public static final String pattern_time = "yyyy-MM-dd HH:mm:ss";

	/**
	 * 描述：日期格式化
	 * 
	 * @param date
	 *            日期
	 * @return 输出格式为 yyyy-MM-dd 的字串
	 */
	public static String formatDate(Date date) {
		return formatDate(date, pattern_time);
	}

	/**
	 * 格式化为指定日期的凌晨零点
	 */
	public static String formatDateToZore(Date date) {
		return formatDate(date, "yyyy-MM-dd 00:00:00");
	}

	/**
	 * 格式化为指定日期的晚上23点59分59秒
	 */
	public static String formatDateToFinallyTime(Date date) {
		return formatDate(date, "yyyy-MM-dd 23:59:59");
	}

	/**
	 * 获取上个月1号的日期
	 */
	public static Date getLastMonthFirstDay() {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MONTH, -1);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		Date time = calendar.getTime();
		return time;

	}

	/**
	 * 获取上个月1号的第一秒的日期字符串 格式yyyy-MM-dd HH:mm:ss
	 * 
	 * @throws Exception
	 */
	public static Date getLastMonthFirstDayFirstTimeDate() throws Exception {
		Date date = getLastMonthFirstDay();
		String string = formatDateToZore(date);
		date = parseString(string);
		return date;
	}

	/**
	 * 获取上个月最后一天最后一秒的日期 对象
	 * 
	 * @throws Exception
	 */
	public static Date getLastMonthLastDayLastTimeDate() throws Exception {
		Date date = getLastMonthLastDay();
		String string = formatDateToFinallyTime(date);
		Date parseString = parseString(string);
		return parseString;
	}

	/**
	 * 获取上个月1号的第一秒的日期字符串 格式yyyy-MM-dd HH:mm:ss
	 */
	public static String getLastMonthFirstDayFirstTimeString() {
		Date date = getLastMonthFirstDay();
		String string = formatDateToZore(date);
		return string;
	}

	/**
	 * 获取上个月最后一天最后一秒的日期字符串 格式yyyy-MM-dd HH:mm:ss
	 */
	public static String getLastMonthLastDayLastTimeString() {
		Date date = getLastMonthLastDay();
		String string = formatDateToFinallyTime(date);
		return string;
	}

	/**
	 * 获取上个月最后一天的日期
	 */
	public static Date getLastMonthLastDay() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		calendar.add(Calendar.DATE, -1);
		Date time = calendar.getTime();
		return time;
	}

	/**
	 * 描述：日期格式化
	 * 
	 * @param date
	 *            日期
	 * @param pattern
	 *            格式化类型
	 * @return
	 */
	public static String formatDate(Date date, String pattern) {
		SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
		return dateFormat.format(date);
	}

	/**
	 * 描述：解析日期字串
	 * 
	 * @param dateStr
	 *            日期字串
	 * @return 按 yyyy-MM-dd HH:mm:ss 格式解析
	 * @throws Exception
	 */
	public static Date parseString(String dateStr) throws Exception {
		return parseString(dateStr, "yyyy-MM-dd HH:mm:ss");
	}
	
	/**
	 * 判断 时间是否小于后面时间
	 */
	public static boolean  lessThanDate(Date date1,Date date2) {
		if(date1.getTime()<date2.getTime()){
			return true;
		}else{
			return false;
		}
	}

	/**
	 * 描述：解析日期字串
	 * 
	 * @param dateStr
	 *            日期字串
	 * @param pattern
	 *            字串日期格式
	 * @return 对应日期类型数据
	 * @throws Exception
	 */
	public static Date parseString(String dateStr, String pattern) throws Exception {
		SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
		if (!StringUtils.isEmpty(dateStr)) {
			return dateFormat.parse(dateStr);
		}
		return null;
	}

	/**
	 * 两个日期相减得到相差的分钟数
	 */
	public static int getMinutes(Date beforeDate, Date afterDate) {
		int minutes = (int) ((afterDate.getTime() - beforeDate.getTime()) / (1000 * 60));
		return minutes;
	}

	/**
	 * 根据统计周期: 1:年,2季,3月,4:周,5:日得到开始日期
	 */
	public static Date getStartDate(int statisticalPeriod, Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		if (1 == statisticalPeriod) {
			calendar.add(Calendar.YEAR, -1);//
		}
		if (2 == statisticalPeriod) {
			calendar.add(Calendar.MONTH, -3);// 得到前3个月
		}
		if (3 == statisticalPeriod) {
			calendar.add(Calendar.MONTH, -1);
		}
		if (4 == statisticalPeriod) {
			calendar.add(Calendar.DATE, -7);
		}
		if (5 == statisticalPeriod) {
			calendar.add(Calendar.DATE, -1);
		}
		date = calendar.getTime();
		return date;
	}

	/**
	 * 获取两个时间之间的天数
	 * 
	 * @return
	 */
	public static int getTotalDays(Date beginDate, Date endDate) {
		try {
			beginDate = parseString(formatDateToZore(beginDate));
			endDate = parseString(formatDateToFinallyTime(endDate));
		} catch (Exception e) {
			e.printStackTrace();
		}
		int days = (int) ((endDate.getTime() - beginDate.getTime()) / (1000 * 3600 * 24) + 1);
		return days == 0 ? 1 : days;
	}

	/**
	 * 获取当前日期 指定相差天数的天 days为相差天数 -为之前多少天
	 */
	public static String getDaysBefore(Date date, int days) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DATE, days);
		date = calendar.getTime();
		return formatDate(date, pattern_date);
	}

	/**
	 * 获取当前月
	 */

	public static int getCurrentMonth() {
		Calendar instance = Calendar.getInstance();
		int month = instance.get(Calendar.MONTH) + 1;
		return month;
	}

	/**
	 * 得到格式化的月
	 * 
	 * @param month
	 * @return
	 */
	public static String getFormatMonth(String month) {
		String newMonth = "0" + month;
		month = newMonth.substring(newMonth.length() - 2, newMonth.length());
		return month;
	}

	/**
	 * 获取当前年
	 */

	public static int getCurrentYear() {
		Calendar instance = Calendar.getInstance();
		int year = instance.get(Calendar.YEAR);
		return year;
	}

	/**
	 * 根据传的日期获取当前月
	 */

	public static int getCurrentMonthByDate(Date date) {
		Calendar instance = Calendar.getInstance();
		instance.setTime(date);
		int month = instance.get(Calendar.MONTH) + 1;
		return month;
	}

	/**
	 * 根据传的日期获取当前年
	 */

	public static int getCurrentYearByDate(Date date) {
		Calendar instance = Calendar.getInstance();
		instance.setTime(date);
		int year = instance.get(Calendar.YEAR);
		return year;
	}

	/**
	 * 获取某年第一天日期
	 * 
	 * @param year
	 *            年份
	 * @return Date
	 */
	public static Date getYearFirst(int year) {
		Calendar calendar = Calendar.getInstance();
		calendar.clear();
		calendar.set(Calendar.YEAR, year);
		Date currYearFirst = calendar.getTime();
		return currYearFirst;
	}

	/**
	 * 获取某年最后一天日期
	 * 
	 * @param year
	 *            年份
	 * @return Date
	 */
	public static Date getYearLast(int year) {
		Calendar calendar = Calendar.getInstance();
		calendar.clear();
		calendar.set(Calendar.YEAR, year);
		calendar.roll(Calendar.DAY_OF_YEAR, -1);
		Date currYearLast = calendar.getTime();

		return currYearLast;
	}

	/**
	 * 获取当前月的第一天日期
	 * 
	 * @return
	 */
	public static Date getCurrentMonthFirst(Date date) {
		Calendar cale = Calendar.getInstance();
		cale.setTime(date);
		cale.add(Calendar.MONTH, 0);
		cale.set(Calendar.DAY_OF_MONTH, 1);
		return cale.getTime();
	}

	/**
	 * 获取月的第一天日期
	 * 
	 * @return
	 */
	public static Date getMonthFirst(Date date) {
		Calendar cale = Calendar.getInstance();
		cale.setTime(date);
		cale.add(Calendar.MONTH, 0);
		cale.set(Calendar.DAY_OF_MONTH, 1);
		return cale.getTime();
	}

	/**
	 * 获取月的最后一天日期
	 * 
	 * @return
	 */
	public static Date getCurrentMonthLast(Date date) {
		Calendar cale = Calendar.getInstance();
		cale.setTime(date);
		cale.add(Calendar.MONTH, 1);
		cale.set(Calendar.DAY_OF_MONTH, 0);
		return cale.getTime();
	}

	/**
	 * 计算两个时间之间相差的月份数
	 * 
	 * @return
	 */

	public static int getMonthDiffer(Date max, Date min) {
		Calendar aft = Calendar.getInstance();
		Calendar bef = Calendar.getInstance();
		aft.setTime(max);
		bef.setTime(min);
		int result = (aft.get(Calendar.YEAR) - bef.get(Calendar.YEAR)) * 12 + aft.get(Calendar.MONTH)
				- bef.get(Calendar.MONTH);
		return result;
	}

	/**
	 * 判断两个时间的年月是否相等
	 */
	public static boolean isSameYearMonth(Date date1, Date date2) {
		Calendar calendar1 = Calendar.getInstance();
		calendar1.setTime(date1);
		Calendar calendar2 = Calendar.getInstance();
		calendar2.setTime(date2);
		if (calendar1.get(Calendar.YEAR) != calendar2.get(Calendar.YEAR)) {
			return false;
		}
		if (calendar1.get(Calendar.MONTH) != calendar2.get(Calendar.MONTH)) {
			return false;
		}
		return true;
	}

	/**
	 * 获取某个月份的天数
	 * 
	 * @param date
	 * @return
	 */
	public static int getDaysOfMonth(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
	}
/**
 * 两个日期之间相差多少天
 * @param early 小时间
 * @param late	大时间
 * @return
 */
	public static final int daysBetween(Date early, Date late) { 
	     
        Calendar calst = Calendar.getInstance();   
        Calendar caled = Calendar.getInstance();   
        calst.setTime(early);   
         caled.setTime(late);   
         //设置时间为0时   
         calst.set(Calendar.HOUR_OF_DAY, 0);   
         calst.set(Calendar.MINUTE, 0);   
         calst.set(Calendar.SECOND, 0);   
         caled.set(Calendar.HOUR_OF_DAY, 0);   
         caled.set(Calendar.MINUTE, 0);   
         caled.set(Calendar.SECOND, 0);   
        //得到两个日期相差的天数   
         int days = ((int) (caled.getTime().getTime() / 1000) - (int) (calst   
                .getTime().getTime() / 1000)) / 3600 / 24;   
         
        return days;   
   }   
	
	 /**
     * 获取两个日期之間的日期集合
     * @param startTime:String
     * @param endTime:String
     * @return list:yyyy-MM-dd
     */
    public static List<String> getBetweenDate(Date startDate, Date endDate){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        // 声明保存日期集合
        List<String> list = new ArrayList<String>();
        //用Calendar 进行日期比较判断
        Calendar calendar = Calendar.getInstance();
        while (startDate.getTime()<=endDate.getTime()){
            // 把日期添加到集合
            list.add(sdf.format(startDate));
            // 设置日期
            calendar.setTime(startDate);
            //把日期增加一天
            calendar.add(Calendar.DATE, 1);
            // 获取增加后的日期
            startDate=calendar.getTime();
        }
        return list;
    }
}