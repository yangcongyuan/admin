package com.etnlgravtnl.common.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.apache.commons.lang3.time.DateFormatUtils;

/**
 * 日期工具类, 继承org.apache.commons.lang.time.DateUtils类
 * @author guozheng
 * @version 2016-05-07
 */
public class DateUtils extends org.apache.commons.lang3.time.DateUtils {
	
	private static String[] parsePatterns = {
		"yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm", "yyyy-MM", 
		"yyyy/MM/dd", "yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd HH:mm", "yyyy/MM",
		"yyyy.MM.dd", "yyyy.MM.dd HH:mm:ss", "yyyy.MM.dd HH:mm", "yyyy.MM"};

	/**
	 * 得到当前日期字符串 格式（yyyy-MM-dd）
	 */
	public static String getDate() {
		return getDate("yyyy-MM-dd");
	}
	
	/**
	 * 得到当前日期字符串 格式（yyyy-MM-dd） pattern可以为："yyyy-MM-dd" "HH:mm:ss" "E"
	 */
	public static String getDate(String pattern) {
		return DateFormatUtils.format(new Date(), pattern);
	}
	
	/**
	 * 得到日期字符串 默认格式（yyyy-MM-dd） pattern可以为："yyyy-MM-dd" "HH:mm:ss" "E"
	 */
	public static String formatDate(Date date, Object... pattern) {
		String formatDate = null;
		if (pattern != null && pattern.length > 0) {
			formatDate = DateFormatUtils.format(date, pattern[0].toString());
		} else {
			formatDate = DateFormatUtils.format(date, "yyyy-MM-dd");
		}
		return formatDate;
	}
	
	/**
	 * 得到日期时间字符串，转换格式（yyyy-MM-dd HH:mm:ss）
	 */
	public static String formatDateTime(Date date) {
		return formatDate(date, "yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * 得到当前时间字符串 格式（HH:mm:ss）
	 */
	public static String getTime() {
		return formatDate(new Date(), "HH:mm:ss");
	}

	/**
	 * 得到当前日期和时间字符串 格式（yyyy-MM-dd HH:mm:ss）
	 */
	public static String getDateTime() {
		return formatDate(new Date(), "yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * 得到当前年份字符串 格式（yyyy）
	 */
	public static String getYear() {
		return formatDate(new Date(), "yyyy");
	}

	/**
	 * 得到当前月份字符串 格式（MM）
	 */
	public static String getMonth() {
		return formatDate(new Date(), "MM");
	}

	/**
	 * 得到当天字符串 格式（dd）
	 */
	public static String getDay() {
		return formatDate(new Date(), "dd");
	}

	/**
	 * 得到当前星期字符串 格式（E）星期几
	 */
	public static String getWeek() {
		return formatDate(new Date(), "E");
	}
	
	/**
	 * 日期型字符串转化为日期 格式
	 * { "yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm", 
	 *   "yyyy/MM/dd", "yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd HH:mm",
	 *   "yyyy.MM.dd", "yyyy.MM.dd HH:mm:ss", "yyyy.MM.dd HH:mm" }
	 */
	public static Date parseDate(Object str) {
		if (str == null){
			return null;
		}
		try {
			return parseDate(str.toString(), parsePatterns);
		} catch (ParseException e) {
			return null;
		}
	}

	/**
	 * 获取过去的天数
	 * @param date
	 * @return
	 */
	public static long pastDays(Date date) {
		long t = new Date().getTime()-date.getTime();
		return t/(24*60*60*1000);
	}

	/**
	 * 获取过去的小时
	 * @param date
	 * @return
	 */
	public static long pastHour(Date date) {
		long t = new Date().getTime()-date.getTime();
		return t/(60*60*1000);
	}
	
	/**
	 * 获取过去的分钟
	 * @param date
	 * @return
	 */
	public static long pastMinutes(Date date) {
		long t = new Date().getTime()-date.getTime();
		return t/(60*1000);
	}
	
	/**
	 * 转换为时间（天,时:分:秒.毫秒）
	 * @param timeMillis
	 * @return
	 */
    public static String formatDateTime(long timeMillis){
		long day = timeMillis/(24*60*60*1000);
		long hour = (timeMillis/(60*60*1000)-day*24);
		long min = ((timeMillis/(60*1000))-day*24*60-hour*60);
		long s = (timeMillis/1000-day*24*60*60-hour*60*60-min*60);
		long sss = (timeMillis-day*24*60*60*1000-hour*60*60*1000-min*60*1000-s*1000);
		return (day>0?day+",":"")+hour+":"+min+":"+s+"."+sss;
    }
	
	/**
	 * 获取两个日期之间的天数
	 * 
	 * @param before
	 * @param after
	 * @return
	 */
	public static double getDistanceOfTwoDate(Date before, Date after) {
		long beforeTime = before.getTime();
		long afterTime = after.getTime();
		return (afterTime - beforeTime) / (1000 * 60 * 60 * 24);
	}

	/**
	 * 获取此时此刻是上午还是下午
	 * @return
	 */
	public static String isAMorPM(){
		GregorianCalendar gregorianCalendar = new GregorianCalendar();
		int i = gregorianCalendar.get(gregorianCalendar.AM_PM);
		if(i==0){
			return "上午";
		}
		return "下午";
	}

	/**
	 * 获取此时此刻是上午还是下午
	 * @return
	 */
	public static long getTimeInterval(Date subtrahend,Date minuend){
		return subtrahend.getTime()-minuend.getTime();
	}

	/**
	 * 比较两个时间的早晚
	 * @param time1
	 * @param time2
	 * @return
	 * @throws ParseException
	 */
	public static boolean compare(String time1,String time2){
		//如果想比较日期则写成"yyyy-MM-dd"就可以了
//        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		SimpleDateFormat sdf=new SimpleDateFormat("HH:mm:ss");
		//将字符串形式的时间转化为Date类型的时间
		Date a= null;
		Date b= null;
		try {
			a = sdf.parse(time1);
			b = sdf.parse(time2);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		//Date类的一个方法，如果a早于b返回true，否则返回false
		if(a.before(b))
			return true;
		else
			return false;
		/*
		 * 如果你不喜欢用上面这个太流氓的方法，也可以根据将Date转换成毫秒
		if(a.getTime()-b.getTime()<0)
			return true;
		else
			return false;
		*/
	}

	public static Date getDate4Later(int laterMinutes)
	{
		Calendar calendar=Calendar.getInstance();
		calendar.add(Calendar.MINUTE,laterMinutes);
		return calendar.getTime();
	}
	
	/**
	 * @param args
	 * @throws ParseException
	 */
	public static void main(String[] args) throws ParseException {
//		System.out.println(formatDate(parseDate("2010/3/6")));
//		System.out.println(getDate("yyyy年MM月dd日 E"));
//		long time = new Date().getTime()-parseDate("2012-11-19").getTime();
//		System.out.println(time/(24*60*60*1000));

	}
	
	////////////////////////////////////////////////////////////////////
	
	public static final String DEFAULT_DATE_PATTERN = "yyyy-MM-dd";
	
	public static final String DEFAULT_DATETIME_PATTERN = "yyyy-MM-dd HH:mm:ss";
	
	/**
	 * 获得月末日期
	 * @param year
	 * @param month
	 * @return
	 */
	public static Date getMonthLastDate(int year, int month)
	{
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR, year);
		calendar.set(Calendar.MONTH, month - 1);
		calendar.set(Calendar.DATE, calendar.getActualMaximum(Calendar.DATE));
		return calendar.getTime();
	}
	
	/**
	 * 计算某日期的前/后几天的日期
	 * @param date
	 * @param days
	 * @return
	 */
	public static Date getDateCompute(Date date, int days)
	{
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) + days);
		return calendar.getTime();
	}
	
	/**
	 * 获得某日期所在周的周一
	 * @param date
	 * @return
	 */
	public static Date getMonday(Date date)
	{
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(getDateCompute(date, -1));
		calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		return calendar.getTime();
	}
	
	/**
	 * 获得某日期所在周的周日
	 * @param date
	 * @return
	 */
	public static Date getSunday(Date date)
	{
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(getDateCompute(date, 6));
		calendar.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
		return calendar.getTime();
	}
	
	/**
	 * 计算某月份的前/后几月
	 * @param curMonth yyyy-MM
	 * @param months
	 * @return
	 * @throws ParseException
	 */
	public static String getMonthCompute(String currMonth, int months) throws ParseException
	{
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(parseDate(currMonth, "yyyy-MM"));
		calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) + months);
		return parseDate(calendar.getTime(), "yyyy-MM");
	}
	
	/**
	 * 获得两个日期间的天数
	 * @param dateFrom
	 * @param dateTo
	 * @return
	 */
	public static long getDaysBetweenDate(Date dateFrom, Date dateTo)
	{
		int per = 24 * 60 * 60 * 1000;
		return ( dateTo.getTime() - dateFrom.getTime() ) / per;
	}
	
	/**
	 * 把字符串转化成日期类yyyy-MM-dd
	 * @param date
	 * @return
	 * @throws ParseException
	 */
	public static Date parseDate(String date) throws ParseException
	{
		return parseDate(date, DEFAULT_DATE_PATTERN);
	}
	
	/**
	 * 把字符串转化成日期类yyyy-MM-dd HH:mm:ss
	 * @param date
	 * @return
	 * @throws ParseException
	 */
	public static Date parseDateTime(String date) throws ParseException
	{
		return parseDate(date, DEFAULT_DATETIME_PATTERN);
	}
	
	/**
	 * 把字符串转化成日期类
	 * @param date
	 * @param pattern
	 * @return
	 * @throws ParseException
	 */
	public static Date parseDate(String date, String pattern) throws ParseException
	{
		DateFormat dateFormat = new SimpleDateFormat(pattern);
		return dateFormat.parse(date);
	}
	
	/**
	 * 把日期类转化成字符串yyyy-MM-dd
	 * @param date
	 * @return
	 */
	public static String parseDate(Date date)
	{
		return parseDate(date, DEFAULT_DATE_PATTERN);
	}
	
	/**
	 * 把日期类转化成字符串yyyy-MM-dd HH:mm:ss
	 * @param date
	 * @return
	 */
	public static String parseDateTime(Date date)
	{
		return parseDate(date, DEFAULT_DATETIME_PATTERN);
	}
	
	/**
	 * 把日期类转化成字符串
	 * @param date
	 * @param pattern
	 * @return
	 */
	public static String parseDate(Date date, String pattern)
	{
		DateFormat dateFormat = new SimpleDateFormat(pattern);
		return dateFormat.format(date);
	}

	/**
	 * 查询开始时间
	 * @param date yyyy-MM-dd
	 * @return
	 */
	public static String getDateFrom(String date)
	{
		return date + " 00:00:00";
	}
	
	/**
	 * 查询截止时间
	 * @param date yyyy-MM-dd
	 * @return
	 */
	public static String getDateTo(String date)
	{
		return date + " 23:59:59";
	}
}
