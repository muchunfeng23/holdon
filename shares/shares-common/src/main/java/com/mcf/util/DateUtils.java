package com.mcf.util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

public final class DateUtils {

	private static final String DEFAULT_DATE_PATTERN = "yyyy-MM-dd";

	private DateUtils() {

	}

	/**
	 * 获取过去30天日期
	 *
	 * @return List<String>
	 */
	public static List<String> getMonthToDate() {
		List<String> dateList=new ArrayList<String>();
		Date date=new Date();//取时间
		String dateString="";
		 Calendar calendar = new GregorianCalendar();
		 calendar.setTime(date);
		 SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		 for(int i=0;i<30;i++){
		 calendar.add(calendar.DATE,-1);//把日期往后增加一天.整数往后推,负数往前移动
		 date=calendar.getTime(); //这个时间就是日期往后推一天的结果
		  dateString = formatter.format(date);
		 dateList.add(dateString);

		 }
		return dateList;
	}

	/**
	 * 得到本周周一
	 * 
	 * @return Date
	 */
	public static Date getMondayOfThisWeek() {
		Calendar c = Calendar.getInstance();
		int day_of_week = c.get(Calendar.DAY_OF_WEEK) - 1;
		if (day_of_week == 0)
			day_of_week = 7;
		c.add(Calendar.DATE, -day_of_week + 1);
		return c.getTime();
	}

	/**
	 * 输入日期计算其他日期
	 * 
	 * @param sourceDay
	 * @param calDays
	 * @return
	 */
	public static Date calDays(Date sourceDay, int calDays) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(sourceDay);
		calendar.set(Calendar.DATE, calendar.get(Calendar.DATE) + calDays);
		return calendar.getTime();
	}

	public static String parseDate(Date date, String pattern) {
		if (date == null) {
			return null;
		}

		if (pattern == null) {
			throw new NullPointerException();
		}
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		return simpleDateFormat.format(date);
	}

	public static String parseDate(Date date) {
		return parseDate(date, DEFAULT_DATE_PATTERN);
	}

	public static java.sql.Date strToSqlDate(String strDate) {
		String str = strDate;
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date d = null;
		try {
			d = format.parse(str);
		} catch (Exception e) {
			e.printStackTrace();
		}
		java.sql.Date date = new java.sql.Date(d.getTime());
		return date;
	}

	/**
	 * 当前时间
	 * @return
	 */
	public static Calendar calendar() {
		final Calendar cal = Calendar.getInstance(Locale.CHINESE);
		cal.setFirstDayOfWeek(Calendar.MONDAY);
		return cal;
	}

	/**
	 *  为日期增加天数
	 * @param date 日期
	 * @param day 天数，为正 则往后,为负 则往前
	 * @return
	 */
	public static Date addDay(Date date,int day){
		Calendar cal = calendar();
		cal.setTime(date);
		cal.add(Calendar.DATE,day);

		return cal.getTime();
	}

	public static void main(String[] args) {
		Date monday = getMondayOfThisWeek();
		System.out.println(monday);

		Date calDay = calDays(monday, 6);
		System.out.println(calDay);

	}

}
