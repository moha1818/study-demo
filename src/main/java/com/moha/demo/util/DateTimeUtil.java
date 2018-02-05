package com.moha.demo.util;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.*;

public final class DateTimeUtil {

	private static final Logger logger = Logger.getLogger(DateTimeUtil.class);

	public static final int YEAR = Calendar.YEAR;
	public static final int MONTH = Calendar.MONTH;
	public static final int DAY = Calendar.DAY_OF_MONTH;
	public static final int HOUR = Calendar.HOUR_OF_DAY;
	public static final int MINUTE = Calendar.MINUTE;
	public static final int SECOND = Calendar.SECOND;
	public static final int MILLISECOND = Calendar.MILLISECOND;
	public static final int WEEK = Calendar.WEEK_OF_YEAR;

	/**
	 * 得到 年年年年-月月-日日 时时-分分-秒秒 的字符串 替换掉 .toLocaleString() 方法 兼容LINUX
	 * @return
     */
	public static String getLocalTime(){
		Date nowTime = new Date(System.currentTimeMillis());
		SimpleDateFormat sdFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String retStrFormatNowDate = sdFormatter.format(nowTime);
		return retStrFormatNowDate;
	}

    public static Integer getInterval(String createtime) { //传入的时间格式必须类似于2012-8-21 17:53:20这样的格式
        //String interval = null;

        SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        ParsePosition pos = new ParsePosition(0);
        Date d1 = (Date) sd.parse(createtime, pos);

        //用现在距离1970年的时间间隔System.currentTimeMillis()减去以前的时间距离1970年的时间间隔d1.getTime()得出的就是以前的时间与现在时间的时间间隔
        long time = System.currentTimeMillis() - d1.getTime();// 得出的时间间隔是毫秒
		
        int se = (int) ((time) / 1000);
        //interval = se + "秒前";
        return se;
    }


	/**
	 * 2015-09-23 02:49:21.98 +00:00 转成 2015-09-23T10:49:21.98+08:00
	 * 
	 * @throws ParseException
	 */
	public static String gMT2uTC(String gMTString, Long timeZone) {
		String result = "";
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			Date date = sdf.parse(gMTString);
			date = new Date(date.getTime() + timeZone);

			long hour = timeZone / 3600 / 1000;// 得到小时数
			String zoneId = "+08:00";
			if (hour >= 0) {
				zoneId = Math.abs(hour) > 10 ? hour + ":00" : "+0" + hour + ":00";
			} else {
				zoneId = Math.abs(hour) > 10 ? "-" + Math.abs(hour) + ":00" : "-0" + Math.abs(hour) + ":00";
			}

			// UTC时间

			Calendar c = new GregorianCalendar();
			c.setTime(date);
			result = ((GregorianCalendar) c).toZonedDateTime().withZoneSameInstant(ZoneId.of(zoneId)).toString();
		} catch (Exception e) {
			return "";
		}
		return result;
	}

	/**
	 * 
	 * <p class="detail">
	 * 功能：返回某年某月的天数
	 * </p>
	 * 
	 * @author <a href="mailto:engineer11@financegt.com">朱超</a>
	 * @date 2015年9月14日
	 * @param year
	 * @param month
	 * @return
	 */
	public static int getDaysForMonth(int year, int month) {
		if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12) {
			return 31;
		}
		if (month == 4 || month == 6 || month == 9 || month == 11) {
			return 30;
		}
		if (month == 2) {
			if (year % 4 == 0 && year % 100 != 0 || year % 400 == 0) {

				return 29;

			} else {
				return 28;
			}
		}
		return 0;

	}

	/**
	 * timeZoneOffset表示时区，如中国一般使用东八区，因此timeZoneOffset就是8
	 * 
	 * @param timeZoneOffset
	 * @return
	 */
	public static String getFormatedDateString(String dateStr, int timeZoneOffset) {
		return getFormatedDateString(dateStr, timeZoneOffset, null);
	}

	public static String getFormatedDateString(String dateStr, int timeZoneOffset, String formater) {
		SimpleDateFormat sdf = new SimpleDateFormat(StringUtils.isEmpty(formater) ? "yyyy-MM-dd'T'HH:mm:ss" : formater);
		Date date;
		try {
			date = sdf.parse(dateStr);
		} catch (ParseException e) {
			return "";
		}

		if (timeZoneOffset > 13 * 3600 * 1000 || timeZoneOffset < -12 * 3600 * 1000) {
			timeZoneOffset = 0;
		}
		TimeZone timeZone;
		String[] ids = TimeZone.getAvailableIDs(timeZoneOffset);
		if (ids.length == 0) {
			timeZone = TimeZone.getTimeZone("Asia/Shanghai");
		} else {
			timeZone = new SimpleTimeZone(timeZoneOffset, ids[0]);
		}

		sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		sdf.setTimeZone(timeZone);

		String result = sdf.format(date);
		if (StringUtils.isNotEmpty(formater) && formater.equalsIgnoreCase("yyyy-MM-dd")) {
			result = StringUtils.split(result, " ")[0];
		}
		return result;
	}

	/**
	 * 
	 * <p class="detail">
	 * 功能：得到某个时间到当前的年数
	 * </p>
	 * 
	 * @author <a href="mailto:engineer11@financegt.com">朱超</a>
	 * @date 2015年6月3日
	 * @param dateStr
	 *            2013-5-3
	 * @return
	 */
	public static long differenceYearNumFromOneDayToNow(String dateStr) {
		long beginTime = DateTimeUtil.stringToDate(dateStr).getTime();
		long endTime = System.currentTimeMillis();
		long diff = (endTime - beginTime) / (1000 * 60 * 60 * 24);
		long mod = diff % 365;
		if (mod == 0) {
			return diff / 365;
		} else {
			return diff / 365 + 1;
		}
	}

	/**
	 * 
	 * <p class="detail">
	 * 功能：当前UTC时间
	 * </p>
	 * 
	 * @author <a href="mailto:engineer11@financegt.com">朱超</a>
	 * @date 2015年5月25日
	 * @return
	 */
	@Deprecated
	public static String getCurrentUTC() {
		return ZonedDateTime.of(LocalDateTime.now(), ZoneId.of("UTC+00:00")).withZoneSameInstant(ZoneId.of("+00:00")).toString();
	}

	public static Long diffTime(Date d0, Date d1) {
		return d0.getTime() - d1.getTime();
	}

	public static Long diffTime(String d0Str, String d1Str, String patten) {
		if (StringUtils.isEmpty(patten)) {
			patten = "yyyy-MM-dd HH:mm:ss";
		}
		DateFormat df = new SimpleDateFormat(patten);
		Date d0;
		Date d1;
		try {
			d0 = df.parse(d0Str);
			d1 = df.parse(d1Str);
		} catch (ParseException e) {
			return 0L;
		}
		return diffTime(d0, d1);
	}

	// yyyy-MM-dd HH:mm:ss
	public static String getDateTime(Date source, String patten) {
		if (source == null)
			return "";
		SimpleDateFormat formater = new SimpleDateFormat(patten);
		return formater.format(source);
	}


	public static String getCurrentDatetime(String format) {
		return dateTimeToString(getCurrentDate(), format);
	}

	/**
	 * 获取当前日期
	 * 
	 * @return 当前日期
	 */
	public static Date getCurrentDate() {
		return new Date(getCurrentDatetime().getTime());
	}

	/**
	 * 获取当前日期及时间
	 * 
	 * @return 当前日期及时间
	 */
	public static Timestamp getCurrentDatetime() {
		Timestamp t = new Timestamp(System.currentTimeMillis());
		return t;
	}

	/**
	 * 日期转换为字符串格式yyyy-MM-dd HH:mm:ss
	 * 
	 * @param source
	 *            待转换的日期
	 * @return 日期转换后的字符串
	 */
	public static String dateTimeToString(Date source) {
		if (source == null)
			return "";
		SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return formater.format(source);
	}

	/**
	 * 日期转换为字符串格式yyyy-MM-dd HH:mm:ss
	 * 
	 * @param source
	 *            待转换的日期
	 * @return 日期转换后的字符串
	 */
	public static String dateTimeToString(Calendar source) {
		if (source == null)
			return "";
		SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return formater.format(source);
	}

	/**
	 * 日期转换为字符串格式yyyy-MM-dd HH:mm:ss
	 * 
	 * @param source
	 *            待转换的日期
	 * @return 日期转换后的字符串
	 */
	public static String dateTimeToString(Timestamp source) {
		if (source == null)
			return "";
		SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return formater.format(source);
	}

	/**
	 * 日期转换为字符串格式
	 * 
	 * @param source
	 *            待转换的日期
	 * @param format
	 *            换的格式（如：yyyy-MM-dd HH:mm:ss）
	 * @return 日期转换后的字符串
	 */
	public static String dateTimeToString(Date source, String format) {
		if (source == null)
			return "";
		SimpleDateFormat formater = new SimpleDateFormat(format);
		try {
			return formater.format(source);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("格式化日期错误");
			return null;
		}
	}

	public static String dateTimeToString(String dateStr, String sFormat, String eFormat) {
		SimpleDateFormat formater = new SimpleDateFormat(sFormat);
		Date d;
		try {
			d = formater.parse(dateStr);
		} catch (ParseException e) {
			return null;
		}
		String result = DateTimeUtil.dateTimeToString(d, eFormat);
		return result;
	}

	/**
	 * 日期转换为字符串格式
	 * 
	 * @param source
	 *            待转换的日期
	 * @param format
	 *            换的格式（如：yyyy-MM-dd HH:mm:ss）
	 * @return 日期转换后的字符串
	 */
	public static String dateTimeToString(Timestamp source, String format) {
		if (source == null)
			return "";
		SimpleDateFormat formater = new SimpleDateFormat(format);
		try {
			return formater.format(source);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("格式化日期错误");
			return null;
		}
	}

	/**
	 * 日期转换为字符串格式
	 * 
	 * @param source
	 *            待转换的日期
	 * @param format
	 *            换的格式（如：yyyy-MM-dd HH:mm:ss）
	 * @return 日期转换后的字符串
	 */
	public static String dateTimeToString(Calendar source, String format) {
		if (source == null)
			return "";
		SimpleDateFormat formater = new SimpleDateFormat(format);
		try {
			return formater.format(source);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("格式化日期错误");
			return null;
		}
	}

	/**
	 * 将字符串转换为日期及时间
	 * 
	 * @param source
	 * @return
	 */
	public static Timestamp stringToDateTime(String source) {
		SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			return new Timestamp(formater.parse(source).getTime());
		} catch (ParseException e) {
			e.printStackTrace();
			logger.error("将字符串转换为日期及时间错误");
			return null;
		}
	}

	/**
	 * 将字符串转换为日期
	 * 
	 * @param source
	 * @return
	 */
	public static Date stringToDate(String source) {
		SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
		try {
			return new Date(formater.parse(source).getTime());
		} catch (Exception e) {
			logger.error("字符串转换为日期错误！\n" + e.getMessage());
			return null;
		}
	}


	public static Calendar getCalendar(Object dateObj) {
		String dateStr = dateObj + "";
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar c = Calendar.getInstance();
		dateStr = dateStr.replace("T", " ").replace("Z", "");
		Date date = new Date();
		try {
			date = dateFormat.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		c.setTime(date);
		return c;
	}

	// /////////////////////判断范围//////////////////////////////

	/**
	 * 是否在距现在的指定时间范围内
	 * 
	 * @param date
	 *            待判断时间
	 * @param amount
	 *            范围数量
	 * @param type
	 *            年月日时分秒类型（如：月：DateTimeUtil.MONTH）
	 * @return
	 */
	public static Timestamp isBoundToNow(Timestamp date, int amount, int type) {
		Calendar calendar = Calendar.getInstance();
		if (date == null)
			return null;
		calendar.setTime(date);
		calendar = isBoundToNow(calendar, amount, type);
		if (calendar == null) {
			return null;
		} else {
			return new Timestamp(calendar.getTime().getTime());
		}
	}

	/**
	 * 是否在距现在的指定时间范围内
	 * 
	 * @param date
	 *            待判断时间
	 * @param amount
	 *            范围数量
	 * @param type
	 *            年月日时分秒类型（如：月：DateTimeUtil.MONTH）
	 * @return
	 */
	public static Date isBoundToNow(Date date, int amount, int type) {
		Calendar calendar = Calendar.getInstance();
		if (date == null)
			return null;
		calendar.setTime(date);
		calendar = isBoundToNow(calendar, amount, type);
		if (calendar == null) {
			return null;
		} else {
			return new Date(calendar.getTime().getTime());
		}
	}

	/**
	 * 是否在距现在的指定时间范围内
	 * 
	 * @param calendar
	 *            待判断时间
	 * @param amount
	 *            范围数量
	 * @param type
	 *            年月日时分秒类型（如：月：DateTimeUtil.MONTH）
	 * @return
	 */
	public static Calendar isBoundToNow(Calendar calendar, int amount, int type) {
		Calendar now = Calendar.getInstance();
		if (calendar == null)
			return null;
		if (type != Calendar.WEEK_OF_YEAR) {
			calendar.add(type, amount);
		} else {
			calendar.add(Calendar.DAY_OF_WEEK, amount * 7);
		}
		if (now.getTimeInMillis() < calendar.getTimeInMillis()) {
			return calendar;
		} else {
			return null;
		}
	}

	// /////////////////////判断范围//////////////////////////////

	public static long seconds(Timestamp time) {
		long result = 0;
		Date date = new Date();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String nowtime = df.format(date);// 按以上格式 将当前时间转换成字符串
		String testtime = df.format(time);// 结束时间

		try {
			result = (df.parse(testtime).getTime() - df.parse(nowtime).getTime()) / 1000;// 当前时间减去测试时间
																							// 这个的除以1000得到秒，相应的60000得到分，3600000得到小时

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	public static long seconds2(String time) {
		long result = 0;
		// Date date = new Date();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		// String nowtime=df.format(date);//按以上格式 将当前时间转换成字符串
		// String testtime=time;//结束时间

		try {
			result = (df.parse(time).getTime() - System.currentTimeMillis()) / 1000;// 当前时间减去测试时间
																				// 这个的除以1000得到秒，相应的60000得到分，3600000得到小时

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	/*
	 * Long longkTiem=yfg.grp.common.DateTiem.seconds(item.getEndTime());
	 * request.setAttribute("ktiem",String.valueOf(longkTiem));
	 */

	/**
	 * 
	 * <p class="detail">
	 * 功能：输出UTC时间字符串
	 * </p>
	 * 
	 * @author <a href="mailto:wangs@financegt.com">Kings</a>
	 * @date 2015年9月9日
	 * @param dateStr
	 * @param format
	 *            date dateTime
	 * @return
	 * @throws Exception
	 */
	/*
	 * public static String getUTCtime(String dateStr,String format) throws
	 * Exception{ return getUTCtime(dateStr, format, "UTC+8"); }
	 *//**
	 * 
	 * <p class="detail">
	 * 功能：输出UTC时间字符串
	 * </p>
	 * 
	 * @author <a href="mailto:wangs@financegt.com">Kings</a>
	 * @date 2015年9月9日
	 * @param dateStr
	 * @param format
	 * @param zone
	 *            UTC+8 GMT+9 +08:15
	 * @return
	 * @throws Exception
	 */
	/*
	 * public static String getUTCtime(String dateStr,String format,String zone)
	 * throws Exception{ format =
	 * "date".equals(format)?"yyyy-MM-dd":"dateTime".equals
	 * (format)?"yyyy-MM-dd HH:mm:ss":format; SimpleDateFormat fmt = new
	 * SimpleDateFormat(format); SimpleDateFormat fmtUtc = new
	 * SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss"); SimpleDateFormat fmtNoSecond =
	 * new SimpleDateFormat("yyyy-MM-dd'T'HH:mm"); GregorianCalendar c = new
	 * GregorianCalendar(); c.setTime(fmt.parse(dateStr)); String s =
	 * c.toZonedDateTime().withZoneSameInstant(ZoneId.of(zone)).toString();
	 * 
	 * if(c.get(Calendar.SECOND) == 0){ c.setTime(fmtNoSecond.parse(s)); } else
	 * { c.setTime(fmtUtc.parse(s)); } return fmtUtc.format(c.getTime()); }
	 */
	

	/**
	 * 
	 * <p class="detail">
	 * 功能：计算时间差
	 * </p>
	 * @author <a href="mailto:engineer20@financegt.com">WuHongyong</a>
	 * @date 2016年3月18日 
	 * @param startDate 起始日期  如: 2016-3-18
	 * @param endDate 截止日期  如: 2016-5-18
	 * @return 返回天数
	 * @throws Exception
	 */
	 public static int daysBetween(String startDate,String endDate) throws Exception{  
	        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");  
	        Calendar cal = Calendar.getInstance();    
	        cal.setTime(sdf.parse(startDate));    
	        long time1 = cal.getTimeInMillis();    
	        cal.setTime(sdf.parse(endDate));    
	        long time2 = cal.getTimeInMillis();         
	        long between_days=(time2-time1)/(1000*3600*24);  
	       return Integer.parseInt(String.valueOf(between_days));     
	  }  
	 
		/**
		 * 
		 * <p class="detail">
		 * 功能：距离当前日期的时间差
		 * </p>
		 * @author <a href="mailto:engineer20@financegt.com">WuHongyong</a>
		 * @date 2016年3月18日 
		 * @param endDate 截止日期  如: 2016-5-18
		 * @return 返回天数
		 * @throws Exception
		 */
		 public static int daysBetween(String endDate) throws Exception{  
		        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");  
		        Calendar cal = Calendar.getInstance();    
		        cal.setTime(sdf.parse(sdf.format(new Date())));    
		        long time1 = cal.getTimeInMillis();    
		        cal.setTime(sdf.parse(endDate));    
		        long time2 = cal.getTimeInMillis();         
		        long between_days=(time2-time1)/(1000*3600*24);  
		       return Integer.parseInt(String.valueOf(between_days));     
		  }  
	
		 
		 /**
			 * 
			 * <p class="detail">
			 * 功能：距离当前日期的时间差
			 * </p>
			 * @author <a href="mailto:engineer20@financegt.com">zqb</a>
			 * @date 2016年3月18日 
			 * @param endDate 截止日期  如: 2016-5-18
			 * @return 返回天数
			 * @throws Exception
			 */
			 public static int hoursBetween(String endDate) throws Exception{  
			        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
			        Calendar cal = Calendar.getInstance();    
			        cal.setTime(sdf.parse(sdf.format(new Date())));    
			        long time1 = cal.getTimeInMillis();    
			        cal.setTime(sdf.parse(endDate));    
			        long time2 = cal.getTimeInMillis();         
			        long between_days=(time2-time1)/(1000*3600);  
			       return Integer.parseInt(String.valueOf(between_days));     
			  } 
		 
}