package online.rubick.applications.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @ClassName: DateUtil
 * @Description: 日期操作工具类
 * @version 1.0 创建时间: 2017年8月24日 下午2:47:28
 *
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

	private DateUtil() {
	}

	/***
	 * 
	 * @param date
	 *            时间对象
	 * @param pattern
	 *            匹配模式
	 * @return
	 */
	public static String dateToString(Date date, String pattern) {
		String dateStr = "";
		if (null == date) {
			//
		} else {
			SimpleDateFormat smp = new SimpleDateFormat(pattern);
			dateStr = smp.format(date);
		}
		return dateStr;
	}

	/***
	 * 检查日期
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
	 * 获取现在时间
	 * 
	 * @return 返回时间类型 yyyy-MM-dd HH:mm:ss
	 */
	public static Date getNowDate() {
		Date date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat(DATETIME_PATTENT);
		String dateString = formatter.format(date);

		DateFormat df = new SimpleDateFormat(DATETIME_PATTENT);
		Date currentTime = null;
		try {
			currentTime = df.parse(dateString);
		} catch (ParseException e) {
			//
		}
		return currentTime;
	}

	public static String getNowDateString() {
		Date date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat(YYYYMMDDHHMMSS_PATTERN);
		return formatter.format(date);
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
	 * 将长时间格式字符串转换为时间 yyyy-MM-dd HH:mm:ss
	 * 
	 * @param strDate
	 * @return
	 */
	public static Date strToDateLong(String strDate) {
		SimpleDateFormat formatter = new SimpleDateFormat(DATETIME_PATTENT);
		ParsePosition pos = new ParsePosition(0);
		return formatter.parse(strDate, pos);
	}

	/**
	 * 将长时间格式时间转换为字符串 yyyy-MM-dd HH:mm:ss
	 * 
	 * @param dateDate
	 * @return
	 */
	public static String dateToStrLong(Date dateDate) {
		SimpleDateFormat formatter = new SimpleDateFormat(DATETIME_PATTENT);
		return formatter.format(dateDate);
	}

	/**
	 * 将长时间格式时间转换为字符串 yyyy-MM
	 * 
	 * @param dateDate
	 * @return
	 */
	public static String dateToShortStr(Date dateDate) {
		SimpleDateFormat formatter = new SimpleDateFormat(YEAR_MONTH_PATTENT);
		return formatter.format(dateDate);
	}

	/**
	 * 将短时间格式时间转换为字符串 yyyy-MM-dd
	 * 
	 * @param dateDate
	 * @param k
	 * @return
	 */
	public static String dateToStr(Date dateDate) {
		SimpleDateFormat formatter = new SimpleDateFormat(DATE_PATTENT);
		return formatter.format(dateDate);
	}

	/**
	 * 将短时间格式字符串转换为时间 yyyy-MM-dd
	 * 
	 * @param strDate
	 * @return
	 */
	public static Date strToDate(String strDate) {
		SimpleDateFormat formatter = new SimpleDateFormat(DATE_PATTENT);
		ParsePosition pos = new ParsePosition(0);
		return formatter.parse(strDate, pos);
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
		cal.setTime(org.apache.commons.lang3.time.DateUtils.addDays(getTodayZeroTimes(), -1));
		return cal.getTime();
	}

	/**
	 * 获得明天零点时间
	 * 
	 * @return
	 */
	public static Date getTomorrowZeroTimes() {
		Calendar cal = Calendar.getInstance();
		cal.setTime(org.apache.commons.lang3.time.DateUtils.addDays(getTodayZeroTimes(), 1));
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
	 * 获得本周日24点时间
	 * 
	 * @return
	 */
	public static Date getSunday24HTimes() {
		Calendar cal = Calendar.getInstance();
		cal.setFirstDayOfWeek(Calendar.MONDAY);
		cal.setTime(getTodayZeroTimes());
		cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
		cal.setTime(org.apache.commons.lang3.time.DateUtils.addDays(cal.getTime(), 1));
		return cal.getTime();
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
		cal.setTime(org.apache.commons.lang3.time.DateUtils.addDays(cal.getTime(), 1));
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
		cal.setTime(org.apache.commons.lang3.time.DateUtils.addDays(cal.getTime(), 1));
		return cal.getTimeInMillis();
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
	 * 获取0到10的中文
	 */
	public static String getChineseByNumZore2Ten(Integer num) {
		String[] s1 = { "零", "一", "二", "三", "四", "五", "六", "七", "八", "九" };
		return s1[num];
	}
	
	/**判断是否是同一天
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
				&& calDateA.get(Calendar.DAY_OF_MONTH) == calDateB
						.get(Calendar.DAY_OF_MONTH);
	}
	/**
	 * 算出两个时间段的所有时间集合(包括当天)
	 * @param startDay
	 * @param endDay
	 * @return
	 */
	public static List<String> getbetweenDay(Date startDay,Date endDay){
			List<String> lDate = new ArrayList<String>();
	        SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
	        lDate.add(sd.format(startDay));
	        Calendar calBegin = Calendar.getInstance();
	        // 使用给定的 Date 设置此 Calendar 的时间
	        calBegin.setTime(startDay);
	        Calendar calEnd = Calendar.getInstance();
	        // 使用给定的 Date 设置此 Calendar 的时间
	        calEnd.setTime(endDay);
	        // 测试此日期是否在指定日期之后
	        while (endDay.after(calBegin.getTime()))
	        {
	            // 根据日历的规则，为给定的日历字段添加或减去指定的时间量
	            calBegin.add(Calendar.DAY_OF_MONTH, 1);
	            lDate.add(sd.format(calBegin.getTime()));
	        }
	        return lDate;
	}
	
}
