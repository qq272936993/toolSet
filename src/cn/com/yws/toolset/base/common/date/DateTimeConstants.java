package cn.com.yws.toolset.base.common.date;

/**
 * 文件名称: DateTimeConstants.java
 * 包路径: cn.com.yws.comm.date
 * 描述:
 * 内容摘要 
 *    作者: 杨文松
 *    版本: 1.0
 *    时间: 2013-12-9
 *    邮箱: yangwensong@talkweb.com.cn
 * 修改历史:  
 * 修改日期           修改人员        版本	       修改内容  		说明
 * ---------------------------------------------- 
 * 
 */
public class DateTimeConstants {
	
	/**
	 * 显示年月日,日期格式
	 * yyyy-MM-dd	如 ：年-月-日
	 * */
	public static final String BARS_YEARTODAY = "yyyy-MM-dd";

	/**
	 * 显示年月,日期格式
	 * yyyy-MM	如:年-月
	 * */
	public static final String BARS_YEARTOMONTH ="yyyy-MM";
	
	/**
	 * 显示日期时间格式
	 * yyyy-MM-dd HH:mm:ss	如:年-月-日 时:分:秒
	 * */
	public static final String BARS_DATEOFTIME ="yyyy-MM-dd HH:mm:ss";
	
	/**
	 * 显示年月日,日期格式
	 * yyyy/MM/dd 如:年/月/日
	 * */
	public static final String SLASH_YEARTODAY="yyyy/MM/dd";
	
	/**
	 * 显示年月,日期格式
	 * yyyy/MM	如:年/月
	 * */
	public static final String SLASH_YEARTOMONTH ="yyyy/MM";
	
	/**
	 * 显示日期时间格式
	 * yyyy/MM/dd HH:mm:ss	如:年/月/日 时:分:秒
	 * */
	public static final String SLASH_DATEOFTIME ="yyyy/MM/dd HH:mm:ss";
	
	
	
	/**
	 * 显示简体中文日期的格式
	 * 如yyyy年MM月dd日
	 * */
	public static final String ZHCN_DATEFORMAT_YEARTODAY="yyyy年MM月dd日";
	
	/**
	 * 显示简体中文日期时间格式
	 * 如yyyy年MM月dd日 HH时mm分ss秒
	 * */
	public static final String ZHCN_DATEFORMATE_DATEOFTIME ="yyyy年MM月dd日 HH时mm分ss秒";
	
	/**
	 * 显示简体中文提起时间格式(不包含秒)
	 * 如yyyy年MM月dd日 HH时mm分
	 * */
	public static final String ZHCN_DATEDORMATE_DTAETOMIN ="yyyy年MM月dd日 HH时mm分";
	
	/**
	 * 没有分割符的时间数据
	 * */
	public static final String NOT_DELIMITER_DATE ="yyyyMMdd";
	
	/**
	 * 日历最大操作
	 * */
	public static final String ACTUAL_MAX ="MAX";
	/**
	 * 日历最小操作
	 * */
	public static final String ACTUAL_MIN = "MIN";
	
	/**
	 * 一天当中的毫秒数
	 * */
	public static final long ONE_DAY_MILLIS= 24 * 60 *60 * 1000;
	
	/**
	 * 一天当中的秒数
	 * */
	public static final long ONE_DAY_SECONDS = 86400;
	
	
}
