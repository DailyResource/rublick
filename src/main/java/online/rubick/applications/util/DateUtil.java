package online.rubick.applications.util;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.time.DateUtils;
import org.springframework.util.StringUtils;

/**
 * @ClassName: DateUtil
 * @Description: 日期操作工具类
 */
public class DateUtil {
	public static final String TIME_PATTERN = "HH:mm:ss";
	public static final String DATE_PATTENT = "yyyy-MM-dd";
	public static final String DATETIME_PATTENT = "yyyy-MM-dd HH:mm:ss";
	public static final String YEAR_MONTH_PATTENT = "yyyy-MM";
	public static final String YYYYMMDD_PATTERN = "yyyyMMdd";
	public static final String MMDD_PATTERN = "MM-dd";
	public static final String YYYYMMDDHHMMSS_PATTERN = "yyyyMMddHHmmss";
	public static final String DATE_SHORTTIME_PATTENT = "yyyy-MM-dd HH:mm";

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
		String str = "";
		if (null != date) {
			SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
			str = dateFormat.format(date);
		}
		return str;
	}

	/**
	 * 描述：日期格式化
	 * 
	 * @param date
	 *            日期
	 * @return 输出格式为 yyyy-MM-dd HH:mm:ss 的字串
	 */
	public static String formatDate(Date date) {
		return formatDate(date, DATETIME_PATTENT);
	}

	/**
	 * 将短时间格式时间转换为字符串 yyyy-MM-dd
	 * 
	 * @param dateDate
	 * @param k
	 * @return
	 */
	public static String dateToStrYMD(Date date) {
		return formatDate(date, DATE_PATTENT);
	}

	/**
	 * 转换为字符串 yyyy-MM
	 * 
	 * @param dateDate
	 * @return
	 */
	public static String dateToStrYM(Date date) {
		return formatDate(date, YEAR_MONTH_PATTENT);
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
	 * 描述：解析日期字串 yyyy-MM-dd HH:mm:ss
	 * 
	 * @param dateStr
	 *            日期字串
	 * @return 按 yyyy-MM-dd HH:mm:ss 格式解析
	 * @throws Exception
	 */
	public static Date parseString(String dateStr) throws Exception {
		return parseString(dateStr, DATETIME_PATTENT);
	}

	/**
	 * 将时间字符串转换为指定格式的时间
	 * 
	 * @param strDate
	 * @param pattent
	 * @return
	 */
	public static Date strToDate(String strDate, String pattent) {
		SimpleDateFormat formatter = new SimpleDateFormat(pattent);
		ParsePosition pos = new ParsePosition(0);
		return formatter.parse(strDate, pos);
	}

	/**
	 * 将短时间格式字符串转换为时间 yyyy-MM-dd
	 * 
	 * @param strDate
	 * @return
	 */
	public static Date strToDate(String strDate) {
		return strToDate(strDate, DATE_PATTENT);
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
	 * 获取现在时间
	 * 
	 * @return返回字符串格式 yyyy-MM-dd HH:mm:ss
	 */
	public static String getStringDate() {
		Date currentTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat(DATETIME_PATTENT);
		return formatter.format(currentTime);
	}

	/**
	 * 获取现在时间
	 * 
	 * @return返回字符串格式 yyyyMMddHHmmss
	 */
	public static String getNowDateString() {
		Date date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat(YYYYMMDDHHMMSS_PATTERN);
		return formatter.format(date);
	}

	/**
	 * 获取某天23:59:59时间
	 * 
	 * @return Date
	 */
	public static Date getEndTime(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		return calendar.getTime();
	}

	/**
	 * 获取某天0:0:0时间
	 * 
	 * @return Date
	 */
	public static Date getOpenTime(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		return calendar.getTime();
	}

	/**
	 * 获得当天零点时间
	 * 
	 * @return
	 */
	public static Date getTodayZeroTimes() {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTime();
	}

	/**
	 * 获得昨天零点时间
	 * 
	 * @return
	 */
	public static Date getYesterdayZeroTimes() {
		Calendar cal = Calendar.getInstance();
		cal.setTime(DateUtils.addDays(getTodayZeroTimes(), -1));
		return cal.getTime();
	}

	/**
	 * 获得明天零点时间
	 * 
	 * @return
	 */
	public static Date getTomorrowZeroTimes() {
		Calendar cal = Calendar.getInstance();
		cal.setTime(DateUtils.addDays(getTodayZeroTimes(), 1));
		return cal.getTime();
	}

	/**
	 * 获得本周一零点时间
	 * 
	 * @return
	 */
	public static Date getMondayZeroTimes() {
		Calendar cal = Calendar.getInstance();
		cal.setFirstDayOfWeek(Calendar.MONDAY);
		cal.setTime(getTodayZeroTimes());
		cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		return cal.getTime();
	}

	/**
	 * 获得指定时间的第二天零点
	 * 
	 * @param milliSecond
	 * @return
	 */
	public static long getNextDayZeroTime(long milliSecond) {
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(milliSecond);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		cal.setTime(DateUtils.addDays(cal.getTime(), 1));
		return cal.getTimeInMillis();
	}

	/**
	 * 获取某天七天前从0点开始的时间
	 * 
	 * @return Date
	 */
	public static Date getStartTime(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) - 7);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar.getTime();
	}

	/**
	 * 获得本周日24点时间
	 * 
	 * @return
	 */
	public static Date getSunday24HTimes() {
		Calendar cal = Calendar.getInstance();
		cal.setFirstDayOfWeek(Calendar.MONDAY);
		cal.setTime(getTodayZeroTimes());
		cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
		cal.setTime(DateUtils.addDays(cal.getTime(), 1));
		return cal.getTime();
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
	 * 根据传的日期获取月
	 */

	public static int getMonthByDate(Date date) {
		Calendar instance = Calendar.getInstance();
		instance.setTime(date);
		int month = instance.get(Calendar.MONTH) + 1;
		return month;
	}

	/**
	 * 获得本月一号零点时间
	 * 
	 * @return
	 */
	public static Date getMonthStartZeroTimes() {
		Calendar cal = Calendar.getInstance();
		cal.setTime(getTodayZeroTimes());
		cal.set(Calendar.DAY_OF_MONTH, cal.getActualMinimum(Calendar.DAY_OF_MONTH));
		return cal.getTime();
	}

	/**
	 * 获得本月最后一天24点时间
	 * 
	 * @return
	 */
	public static Date getMonthEndTimes() {
		Calendar cal = Calendar.getInstance();
		cal.setTime(getTodayZeroTimes());
		cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
		cal.setTime(DateUtils.addDays(cal.getTime(), 1));
		return cal.getTime();
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
	 * 获取上个月1号的第一秒的日期Date格式yyyy-MM-dd HH:mm:ss
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
	 * 获取上个月1号的第一秒的日期字符串 格式yyyy-MM-dd HH:mm:ss
	 */
	public static String getLastMonthFirstDayFirstTimeString() {
		Date date = getLastMonthFirstDay();
		String string = formatDateToZore(date);
		return string;
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
	 * 获取上个月最后一天最后一秒的日期 字符串
	 */
	public static String getLastMonthLastDayLastTimeString() {
		Date date = getLastMonthLastDay();
		String string = formatDateToFinallyTime(date);
		return string;
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
	 * 根据传的日期获取年
	 */

	public static int getYearByDate(Date date) {
		Calendar instance = Calendar.getInstance();
		instance.setTime(date);
		int year = instance.get(Calendar.YEAR);
		return year;
	}

	/**
	 * 获取当年的第一天
	 * 
	 * @param year
	 * @return
	 */
	public static Date getCurrYearFirst() {
		Calendar currCal = Calendar.getInstance();
		int currentYear = currCal.get(Calendar.YEAR);
		return getYearFirst(currentYear);
	}

	/**
	 * 获取当年的最后一天
	 * 
	 * @param year
	 * @return
	 */
	public static Date getCurrYearLast() {
		Calendar currCal = Calendar.getInstance();
		int currentYear = currCal.get(Calendar.YEAR);
		return getYearLast(currentYear);
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
	 * 获取今天指定时分秒的日期
	 * 
	 * @param hour
	 * @param minute
	 * @param second
	 * @param millisecond
	 * @return
	 */
	public static Date getTadeyTimeByParams(int hour, int minute, int second, int millisecond) {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.HOUR_OF_DAY, hour);
		cal.set(Calendar.MINUTE, minute);
		cal.set(Calendar.SECOND, second);
		cal.set(Calendar.MILLISECOND, millisecond);
		return cal.getTime();
	}

	/**
	 * 获取当前日期 指定相差天数的天 days为相差天数 -为之前多少天
	 */
	public static String getDaysBeforeString(Date date, int days) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DATE, days);
		date = calendar.getTime();
		return formatDate(date, DATE_PATTENT);
	}

	/**
	 * 获取当前日期 指定相差天数的天 days为相差天数 -为之前多少天
	 */
	public static Date getDaysBeforeDate(Date date, int days) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DATE, days);
		return calendar.getTime();
	}

	/***
	 * 检查日期,now在开始时间与结束时间之间
	 * 
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public static boolean checkDateValid(Date startDate, Date endDate) {
		long time = System.currentTimeMillis();
		if (startDate == null || endDate == null) {
			return false;
		}
		long stTime = startDate.getTime();
		long edTime = endDate.getTime();
		if (stTime > time || edTime > time) {
			return false;
		}
		return stTime < edTime;
	}

	/**
	 * 判断是否是同一天
	 * 
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static boolean isSameDay(Date date1, Date date2) {
		Calendar calDateA = Calendar.getInstance();
		calDateA.setTime(date1);

		Calendar calDateB = Calendar.getInstance();
		calDateB.setTime(date2);

		return calDateA.get(Calendar.YEAR) == calDateB.get(Calendar.YEAR)
				&& calDateA.get(Calendar.MONTH) == calDateB.get(Calendar.MONTH)
				&& calDateA.get(Calendar.DAY_OF_MONTH) == calDateB.get(Calendar.DAY_OF_MONTH);
	}

	/**
	 * 判断 时间是否小于后面时间
	 */
	public static boolean lessThanDate(Date date1, Date date2) {
		if (date1.getTime() < date2.getTime()) {
			return true;
		} else {
			return false;
		}
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
	 * 两个日期相减得到相差的分钟数
	 */
	public static int getMinutes(Date beforeDate, Date afterDate) {
		int minutes = (int) ((afterDate.getTime() - beforeDate.getTime()) / (1000 * 60));
		return minutes;
	}

	/**
	 * 两个日期之间相差多少天
	 * 
	 * @param early
	 *            小时间
	 * @param late
	 *            大时间
	 * @return
	 */
	public static int daysBetween(Date early, Date late) {

		Calendar calst = Calendar.getInstance();
		Calendar caled = Calendar.getInstance();
		calst.setTime(early);
		caled.setTime(late);
		// 设置时间为0时
		calst.set(Calendar.HOUR_OF_DAY, 0);
		calst.set(Calendar.MINUTE, 0);
		calst.set(Calendar.SECOND, 0);
		caled.set(Calendar.HOUR_OF_DAY, 0);
		caled.set(Calendar.MINUTE, 0);
		caled.set(Calendar.SECOND, 0);
		// 得到两个日期相差的天数
		int days = ((int) (caled.getTime().getTime() / 1000) - (int) (calst.getTime().getTime() / 1000)) / 3600 / 24;

		return days;
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
	 * 获取0到10的中文
	 */
	public static String getChineseByNumZore2Ten(Integer num) {
		String[] s1 = { "零", "一", "二", "三", "四", "五", "六", "七", "八", "九" };
		return s1[num];
	}

	/**
	 * 根据数字月获取对应的中文
	 * 
	 * @param num
	 * @return
	 */
	public static String getMonthByNum(int num) {
		String month = "";
		switch (num) {
		case 1:
			month = "一月";
			break;
		case 2:
			month = "二月";
			break;
		case 3:
			month = "三月";
			break;
		case 4:
			month = "四月";
			break;
		case 5:
			month = "五月";
			break;
		case 6:
			month = "六月";
			break;
		case 7:
			month = "七月";
			break;
		case 8:
			month = "八月";
			break;
		case 9:
			month = "九月";
			break;
		case 10:
			month = "十月";
			break;
		case 11:
			month = "十一月";
			break;
		case 12:
			month = "十二月";
			break;
		default:
			break;
		}
		return month;
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
	 * 获取两个日期之间的日期集合，按天
	 * 
	 * @param startTime:String
	 * @param endTime:String
	 * @return list:yyyy-MM-dd
	 */
	public static List<String> getBetweenDate(Date startDate, Date endDate) {
		SimpleDateFormat sdf = new SimpleDateFormat(DATE_PATTENT);
		// 声明保存日期集合
		List<String> list = new ArrayList<String>();
		// 用Calendar 进行日期比较判断
		Calendar calendar = Calendar.getInstance();
		while (startDate.getTime() <= endDate.getTime()) {
			// 把日期添加到集合
			list.add(sdf.format(startDate));
			// 设置日期
			calendar.setTime(startDate);
			// 把日期增加一天
			calendar.add(Calendar.DATE, 1);
			// 获取增加后的日期
			startDate = calendar.getTime();
		}
		return list;
	}


	/**
	 * 获取当前日期所在月份中=>每周的开始时间和结束时间
	 * 
	 * @param date
	 * 		需要查询的日期
	 * @return
	 * 		list:yyyy-MM-dd,yyyy-MM-dd 
	 * 		返回每周的开始和结束时间的String集合
	 */
	public static List<String> getWeekDateByMonth(Date date) {
		// 声明保存日期集合
		List<String> list = new ArrayList<>();
		// 用Calendar 进行日期比较判断
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		// 日期所在月的天数
		int dayMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);

		// 设置为当前第一天的时间
		calendar.set(Calendar.DAY_OF_MONTH, 1);

		for (int i = 0; i < dayMonth; i++) {
			if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY || i == 0) {
				list.add(formatDate(calendar.getTime(), DATE_PATTENT));
			}
			if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY || i == dayMonth - 1) {
				list.set(list.size() - 1,
						list.get(list.size() - 1) + "," + formatDate(calendar.getTime(), DATE_PATTENT));
			}
			calendar.add(Calendar.DAY_OF_MONTH, 1);
		}
		return list;
	}
}