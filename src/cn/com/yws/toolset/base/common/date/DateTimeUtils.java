package cn.com.yws.toolset.base.common.date;


import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateTimeUtils {
	
	public static final String LAST_DAY ="lastday";
	
	public static final String FIRST_DAY = "firstday";
	
	private static final SimpleDateFormat sdf = new SimpleDateFormat();
	
	/**
	 *时间字符串转换为时间格式
	 *@param date 时间字符串	 
	 *@param formate 时间格式
	 *@return {@link Date} 转换的时间
	 * eg:date="2012年12月12日" formate="yyyy年MM月dd日"
	 * */
	public static synchronized Date FormatDate(String date ,String formate) throws Exception{
		sdf.applyPattern(formate);
		return sdf.parse(date);
	}
	
	
	/**
	 * 时间转换为时间字符串
	 * @param Date 时间
	 * @param formate 转换格式
	 * @warn 非线程安全
	 * */
	public static synchronized String formatDate(Date date,String formate){
		sdf.applyPattern(formate);
		return sdf.format(date);
	}
	
	
	/**
	 * 获取系统时间
	 * @return {@link Date} 
	 * */
	public static Date getSystemTime(){
		return new Date();
	}
	
	/**
	 * 传递时间的毫秒数,创建时间类型
	 * @param dateTime 毫秒数
	 * @return {@link Date}
	 * */
	public static Date createDate(Long dateTime){
		return new Date(dateTime);
	}
	
	/**
	 * Date类型转换为Calender类型
	 * @param date
	 * @return {@link Calendar}
	 * */
	public static Calendar toCalendar(Date date)
	{
	    Calendar c = Calendar.getInstance();
	    c.setTime(date);
	    return c;
	}
	
	/**
	/**
	 * 通过指定时间参数,创建时间
	 * @param year 年
	 * @param month 月
	 * @param day 日
	 * @return {@link Date}
	 * */
	public static Date createDate(int year,int month,int day){
		Calendar calendar  = Calendar.getInstance();
		calendar.clear();	//clear 是不要受到当前时间的影响
		calendar.set(year, month, day);
		return calendar.getTime();
	}
	
	/**
	 * 指定时间参数,创建时间
	 * @param year 年
	 * @param month 月
	 * @param day 日
	 * @param hourOfDay 时
	 * @param minute 分
	 * @return {@link Date}
	 * */
	public static Date createDate(int year,int month,int day,int hourOfDay,int minute){
		Calendar calendar = Calendar.getInstance();
		calendar.clear();
		calendar.set(year, month, day, hourOfDay, minute);
		return calendar.getTime();
	}
	
	
	/**
	 * 指定时间参数,穿件时间
	 * @param year 年
	 * @param month 月
	 * @param date 日
	 * @param hourOfDay 时
	 * @param minute 分
	 * @param second 秒
	 * @return {@link Date}
	 * */
	public static Date createDate(int year,int month,int date,int hourOfDay,int minute,int second){
		Calendar calendar = Calendar.getInstance();
		calendar.clear();
		calendar.set(year, month, date, hourOfDay, minute, second);
		return calendar.getTime();
	}
	
	
	
	/**
	 * 为给定的日历字段添加或减去指定的时间量
	 * @param date 操作时间
	 * @param dateField 时间字段
	 * @param mount 时间量
	 * @return {@link Date}
	 * */
	public static Date add(Date date, int dateField, int mount){
		if (date == null) return null;
		Calendar calendar = toCalendar(date);
		calendar.add(dateField, mount);
		return calendar.getTime();
	}
	
	/**
	 * 两个时间相差毫秒数
	 * @param start
	 * @param end
	 * @return {@link Long} 
	 */
	public static Long minus(Date start, Date end){
		if (start.after(end)){//交换
			Date tmp = start;
			start = end;
			end = tmp;
		}
		
		return end.getTime()-start.getTime();
	}
	
	
	/**
	 * 比较两个 Calendar 对象表示的时间值
	 * @param start
	 * @param end
	 * @return int
	 * */
	public static Integer compareTo(Calendar start,Calendar end){
		return start.compareTo(end);
	}
	
	/**
	 * 返回给定日历字段的值
	 * @param date 时间
	 * @param dateField 日历字段
	 * @return {@link Integer}
	 * */
	public static Integer getDateField(Date date,int dateField){
		Calendar calendar = toCalendar(date);
		return calendar.get(dateField);
	}
	
	/**
	 * 获取
	 * 给定此 Calendar 的时间值，返回指定日历字段可能拥有的最大值/最小值。
	 * 例如 : 在某些年份中，MONTH 字段的实际最大值是 12，而在希伯来日历系统的其他年份中，该字段的实际最大值是 13
	 * 例如 : 获取在页面中获取 2月的最大时间  28
	 * @param date 指定时间
	 * @param opt 操作 min / max
	 * @param dateFiled 年/月/日 等字段数据
	 * @return {@link Integer}
	 * */
	public static Integer getFieldActualBest(Date date,final String opt,int dateFiled){
		Calendar calendar = toCalendar(date);
		if(DateTimeConstants.ACTUAL_MAX.equals(opt))
			return calendar.getActualMaximum(dateFiled); 
		if(DateTimeConstants.ACTUAL_MIN.equals(opt))
			return calendar.getActualMinimum(dateFiled);
		return null;
	}
	
	/**
	 * 获取一个星期的第一天
	 * @param calendar
	 * */
	public static int getFirstDayOfWeek(Calendar calendar){
		return calendar.getFirstDayOfWeek();
	}
	
	/**
	 * 获取指定时间下,一个星期的第一天
	 * */
	public static int getFirstDayOfWeek(Date date){
		Calendar calendar = toCalendar(date);
		return getFirstDayOfWeek(calendar);
	}
	
	 /**
	  * 获取当前时间所在年的周数
	  * */   
	 public static int getWeekOfYear(Date date) {  
	  Calendar c = new GregorianCalendar();  
	  c.setFirstDayOfWeek(Calendar.MONDAY);  
	  c.setMinimalDaysInFirstWeek(7);  
	  c.setTime(date);  
	  
	  return c.get(Calendar.WEEK_OF_YEAR);  
	 }  
	
	 
	 /**
	  * 获取当前时间所在年的最大周数
	  * */   
	 public static int getMaxWeekNumOfYear(int year) {  
		  Calendar c = new GregorianCalendar();  
		  c.set(year, Calendar.DECEMBER, 31, 23, 59, 59);  
		  
		  return getWeekOfYear(c.getTime());  
	 }  
	
	 
	 /**
	  * 获取某年的第几周的开始日期
	  * */   
	 public static Date getFirstDayOfWeek(int year, int week) {  
		  Calendar c = new GregorianCalendar();  
		  c.set(Calendar.YEAR, year);  
		  c.set(Calendar.MONTH, Calendar.JANUARY);  
		  c.set(Calendar.DATE, 1);  
		  
		  Calendar cal = (GregorianCalendar) c.clone();  
		  cal.add(Calendar.DATE, week * 7);  
		  
		  return getFirstDayOfWeeks(cal.getTime());  
	 }  
	
	 
	 /**
	  * 获取某年的第几周的结束日期
	  * */   
	 public static Date getLastDayOfWeek(int year, int week) {  
	  Calendar c = new GregorianCalendar();  
	  c.set(Calendar.YEAR, year);  
	  c.set(Calendar.MONTH, Calendar.JANUARY);  
	  c.set(Calendar.DATE, 1);  
	  
	  Calendar cal = (GregorianCalendar) c.clone();  
	  cal.add(Calendar.DATE, week * 7);  
	  
	  return getLastDayOfWeek(cal.getTime());  
	 }  
	  
	 /**
	  *  获取当前时间所在周的开始日期
	  * */  
	 public static Date getFirstDayOfWeeks(Date date) {  
	  Calendar c = new GregorianCalendar();  
	  c.setFirstDayOfWeek(Calendar.MONDAY);  
	  c.setTime(date);  
	  c.set(Calendar.DAY_OF_WEEK, c.getFirstDayOfWeek()); // Monday  
	  return c.getTime();  
	 }  
	  
	 /**
	  * 获取当前时间所在周的结束日期 
	  * @param date
	  * */  
	 public static Date getLastDayOfWeek(Date date) {  
		 Calendar c = new GregorianCalendar();  
		 c.setFirstDayOfWeek(Calendar.MONDAY);  
		 c.setTime(date);  
		 c.set(Calendar.DAY_OF_WEEK, c.getFirstDayOfWeek() + 6); // Sunday  
		 return c.getTime();  
	 }  
	  
	
	 
	 /**
	   * 获取给定时间所在月的第一天Ｆ的日期和最后一天的日期
	   * 
	   * @param calendar
	   * @return Date数组，[0]为第一天的日期，[1]最后一天的日期
	   */
	  public static Date[] getMonthStartAndEndDate(Date date) {
		Calendar calendar = Calendar.getInstance();  
		calendar.setTime(date);
	    Date[] dates = new Date[2];
	    Date firstDateOfMonth, lastDateOfMonth;
	    // 得到当天是这月的第几天
	    int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
	    // 减去dayOfMonth,得到第一天的日期，因为Calendar用０代表每月的第一天，所以要减一
	    calendar.add(Calendar.DAY_OF_MONTH, -(dayOfMonth - 1));
	    firstDateOfMonth = calendar.getTime();
	    // calendar.getActualMaximum(Calendar.DAY_OF_MONTH)得到这个月有几天
	    calendar.add(Calendar.DAY_OF_MONTH, calendar
	        .getActualMaximum(Calendar.DAY_OF_MONTH) - 1);
	    lastDateOfMonth = calendar.getTime();

	    dates[0] = firstDateOfMonth;
	    dates[1] = lastDateOfMonth;
	    return dates;
	  }
	  
	  
	  public static Date setCanlendarField(Date date,int field,int value){
		  Calendar calendar = Calendar.getInstance();
		  calendar.setTime(date);
		  calendar.set(field, value);
		  return calendar.getTime();
	  }
	  
	  public static Date addMillisTime(Date date,long millis){
		  Calendar calendar = Calendar.getInstance();
		  calendar.setTime(date);
		  calendar.setTimeInMillis(date.getTime()+millis);
		  
		  return calendar.getTime();
	  }

	  
	  /**
	   * 获取当天开始时间
	   * @return {@link Date}
	   * */
	  public static Date getNowDayStartTime(){
		  Calendar currentDate = new GregorianCalendar();   
		  
		  currentDate.set(Calendar.HOUR_OF_DAY, 0);  
		  currentDate.set(Calendar.MINUTE, 0);  
		  currentDate.set(Calendar.SECOND, 0);  
		  return currentDate.getTime();  
	  }
	  
	  /**
	   * 获取设置时间天的开始时间
	   * @param date
	   * @return {@link Date}
	   * */
	  public static Date getNowDayStartTime(Date date){
		  Calendar currentDate = new GregorianCalendar();  
		  
		  currentDate.set(Calendar.YEAR, getDateField(date, Calendar.YEAR));
		  currentDate.set(Calendar.MONDAY, getDateField(date, Calendar.MONTH));
		  currentDate.set(Calendar.DAY_OF_MONTH, getDateField(date, Calendar.DAY_OF_MONTH));
		  
		  currentDate.set(Calendar.HOUR_OF_DAY, 0);  
		  currentDate.set(Calendar.MINUTE, 0);  
		  currentDate.set(Calendar.SECOND, 0);  
		  return currentDate.getTime();  
	  }
	  
	  /**
	   * 获取当天的结束时间
	   * @return {@link Date}
	   * */
	  public static Date getNowDayEndTime(){
		  Calendar currentDate = new GregorianCalendar();   
		  
		  currentDate.set(Calendar.HOUR_OF_DAY, 23);  
		  currentDate.set(Calendar.MINUTE, 59);  
		  currentDate.set(Calendar.SECOND, 59);  
		  return currentDate.getTime(); 
	  }
	  
	  /**
	   * 获取指定时间的结束时间
	   * @param date
	   * */
	  public static Date getNowDayEndTime(Date date){
		  Calendar currentDate = new GregorianCalendar();   
		  currentDate.set(Calendar.YEAR, getDateField(date, Calendar.YEAR));
		  currentDate.set(Calendar.MONDAY, getDateField(date, Calendar.MONTH));
		  currentDate.set(Calendar.DAY_OF_MONTH, getDateField(date, Calendar.DAY_OF_MONTH));
		  currentDate.set(Calendar.HOUR_OF_DAY, 23);  
		  currentDate.set(Calendar.MINUTE, 59);  
		  currentDate.set(Calendar.SECOND, 59);  
		  return currentDate.getTime(); 
	  }
	  
	  /**
	   * 获取当周开始时间
	   * */
	  public static Date getNowWeekStartTime(){
		  Calendar currentDate = new GregorianCalendar();   
		  currentDate.setFirstDayOfWeek(Calendar.MONDAY);  
		            
		  currentDate.set(Calendar.HOUR_OF_DAY, 0);  
		  currentDate.set(Calendar.MINUTE, 0);  
		  currentDate.set(Calendar.SECOND, 0);  
		  currentDate.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);  
		  return currentDate.getTime();
	  }
	  
	  /**
	   * 获取当周结束时间
	   * */
	  public static Date getNowWeekEndTime(){
		  Calendar currentDate = new GregorianCalendar();   
		  currentDate.setFirstDayOfWeek(Calendar.MONDAY);  
		  currentDate.set(Calendar.HOUR_OF_DAY, 23);  
		  currentDate.set(Calendar.MINUTE, 59);  
		  currentDate.set(Calendar.SECOND, 59);  
		  currentDate.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY); 
		  return currentDate.getTime();
	  }
	  
	
	/**
	 * 获取当前时间	上个月的第一天
	 * */
	public static Date lastMonFirstDay(){
		Calendar calendar = Calendar.getInstance();  
        calendar.set(Calendar.DAY_OF_MONTH, 1);  
        calendar.add(Calendar.MONTH, -1);
	        
	    return calendar.getTime();
	} 
	
	/**
	 * 获取当前时间	上个月的最后一天
	 * */
	public static Date lastMonLastDay(){
		Calendar calendar = Calendar.getInstance();  
	    calendar.set(Calendar.DAY_OF_MONTH, 1);  
	    calendar.add(Calendar.DAY_OF_MONTH, -1); 
	    return calendar.getTime();
	}
	
	
	/**
	 * 获取传递如期的当月的最后一天
	 * */
	public static Date getDateMonthLastDay(Date date){
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR, getDateField(date, Calendar.YEAR) );
		calendar.set(Calendar.MONTH, getDateField(date, Calendar.MONTH));
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		calendar.add(Calendar.DAY_OF_MONTH, -1);
		
		return calendar.getTime();
	}
	
	/**
	 * 计算一个月的第1天和最后1天的日期
	 * @param date 日期
	 * @param type	first表示第1天，lastday表示最后1天
	 * @return 
	 */
	@SuppressWarnings("deprecation")
	public static String calcFirstDayAndLastDay4Month(String date, String type) {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, Integer.parseInt(date.substring(0, 4)));
		cal.set(Calendar.MONTH,
				Integer.parseInt(date.substring(4, date.length())));
		cal.set(Calendar.DAY_OF_MONTH, 1);
		String s = null;
		if(type != null && type.equals(LAST_DAY)){
			cal.add(Calendar.DAY_OF_MONTH, -1);
			Date lastDate = cal.getTime();
			s = date + String.valueOf(lastDate.getDate());
		}else if(type != null && type.equals(FIRST_DAY)){
			s = date + "01";
		}
		return s;
	}
	
	/***
	 * 获取指定年和月和周下的开始时间
	 * 说明 : 这里的周是相对于月的说法
	 * 例如 : 2014 年下的1月第二周的开始时间
	 * @param year 年
	 * @param month 月
	 * @param week 日
	 * @return {@link Date}
	 * */
	public static Date specifiedTimeWeekOfStartTime(int year,int month,int week){
		Calendar currentDate = new GregorianCalendar();   
		currentDate.setFirstDayOfWeek(Calendar.MONDAY);  
		
		currentDate.set(Calendar.YEAR, year);
		currentDate.set(Calendar.MONTH, month-1);
		currentDate.set(Calendar.WEEK_OF_MONTH, week);
		currentDate.set(Calendar.HOUR_OF_DAY, 0);  
		currentDate.set(Calendar.MINUTE, 0);  
		currentDate.set(Calendar.SECOND, 0);  
		currentDate.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);  
		return  (Date)currentDate.getTime().clone();
	}
	
	/**
	 * 获取指定年和月和周下的结束时间
	 * 说明 : 这里的周是相对于月的说法
	 * 例如 : 2014 年下的1月第二周的结束时间
	 * @param year 年
	 * @param month 月
	 * @param week 周
	 * @return {@link Date}
	 * */
	public static Date specifiedTimeWeekOfEndTime(int year,int month,int week){
		
		Calendar currentDate = new GregorianCalendar();   
		currentDate.setFirstDayOfWeek(Calendar.MONDAY);  
		
		currentDate.set(Calendar.YEAR, year);
		currentDate.set(Calendar.MONTH, month-1);
		currentDate.set(Calendar.WEEK_OF_MONTH, week);
		
		currentDate.set(Calendar.HOUR_OF_DAY, 23);  
		currentDate.set(Calendar.MINUTE, 59);  
		currentDate.set(Calendar.SECOND, 59);  
		currentDate.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);  
		
		return (Date)currentDate.getTime().clone();
	}
	
	/**
	 * 指定年和月,最大有几周
	 * @param year 年
	 * @param month 月
	 * @return int 第几周(最大)
	 * */
	public static int getTimeMaxWeek(int year,int month){
		Calendar calendar = Calendar.getInstance();
		calendar.set(year, month-1, 1);
		
		Date date = calendar.getTime();
		date = getDateMonthLastDay(date);
		calendar.setTime(date);
		
		return calendar.get(Calendar.WEEK_OF_MONTH);
	}
	
	
	
	public static void main(String[] args) {
//		System.out.println(calcFirstDayAndLastDay4Month("201308", "lastday"));
		
//		System.out.println(specifiedTimeWeekOfEndTime(2014,1,2));
//		System.out.println(new Date());
		System.out.println(getTimeMaxWeek(2014, 1));
	}
	  
	
}
