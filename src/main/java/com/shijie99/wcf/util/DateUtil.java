package com.shijie99.wcf.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang.StringUtils;

public class DateUtil {
	public static final String YYYYMMDDHHMMSS = "yyyy-MM-dd HH:mm:ss";
	public static final String YYYYMMDD = "yyyy-MM-dd";
	public static final String YYYYMMDD2 = "yyyyMMdd";
	/**
	 * 日期转换成字符串 
	 * @param date  日期
	 * @param format 转换格式
	 * @return
	 */
	public static String format(Date date,String format){
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(date);
	}
	
	public static String format(String date,String fromat,String fromat2){
		if(StringUtils.isNotBlank(date)){
			SimpleDateFormat sdf = new SimpleDateFormat(fromat);
			Date dates = null;
			try {
				dates = sdf.parse(date);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			sdf = new SimpleDateFormat(fromat2);
			return sdf.format(dates);
		}
		return "";
	}
	
	/**
	 * 获得格式化时间
	 * 
	 * @param date
	 *            日期
	 * @param pattern
	 *            格式
	 * @return String
	 * @throws ParseException
	 */
	public static Date stringToDate(String strDate, String pattern)
			throws ParseException {
		if ("".equals(strDate.trim()))
			return null;
		SimpleDateFormat df = new SimpleDateFormat(pattern);
		Date d = df.parse(strDate);
		return d;
	}
	/**
	 * 获取当前时间所在周的第一天(周一为第一天)
	 * @return
	 */
	public static String getFirstDayOfWeek(){
		Calendar c = Calendar.getInstance();
		c.setFirstDayOfWeek(Calendar.MONDAY);
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		return DateUtil.format(c.getTime(),DateUtil.YYYYMMDDHHMMSS);
	}
	
	/**
	 * 获取当前时间所在周的最后一天
	 * @return
	 */
	public static String getLastDayOfWeek(){
		Calendar c = Calendar.getInstance();
        c.setFirstDayOfWeek(Calendar.MONDAY);
        c.set(Calendar.HOUR_OF_DAY, 23);
        c.set(Calendar.MINUTE, 59);
        c.set(Calendar.SECOND, 59);
        c.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
        return DateUtil.format(c.getTime(),DateUtil.YYYYMMDDHHMMSS);
	}
	public static void main(String[] args) {
		System.out.println(getLastDayOfWeek());
	}
}
