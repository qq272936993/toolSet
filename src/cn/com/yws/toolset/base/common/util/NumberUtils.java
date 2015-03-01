package cn.com.yws.toolset.base.common.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * 号码工具类:
 * 	用于区分是手机号码还是电话号码,手机号码获取前非0的前7位数,座机号码获取区号
 * */
public class NumberUtils {
	//用于匹配手机号码
	private final static String PEGEX_MOBILEPHONE = "^0?1[3458]\\d{9}$";
	//用于匹配固定电话
	private final static String REGEX_FIXEDPHONE = "^(010|02\\d|0[3-9]\\d{2})?\\d{6,8}$";
	//用于获取固定电话号码中的区号
	private final static String REGEX_ZIPCODE ="^(010|02\\d|0[3-9]\\d{2})\\d{6,8}$";
	
	
	private static Pattern PATTERN_MOBITEPHONE;
	private static Pattern PATTERN_FIXEDPHONE;
	private static Pattern PATTERN_ZIPCODE;
	
	static{
		PATTERN_FIXEDPHONE = Pattern.compile(REGEX_FIXEDPHONE);
		PATTERN_MOBITEPHONE = Pattern.compile(PEGEX_MOBILEPHONE);
		PATTERN_ZIPCODE = Pattern.compile(REGEX_ZIPCODE);
	}
	
	public static enum PhoneType{
		/**
		 * 手机
		 * */
		CELLPHONE,
		/***
		 * 固定电话
		 * */
		FIXEDPHONE,
		/**
		 * 非法格式号码
		 * */
		INVALIDPHONE
	}
	
	
	public static class Number{
		private PhoneType type;
		/**
		 * 如果是手机号码,则该字段存储的是手机号码前七位.
		 * 如果是固定电话,则该字段存储的是区号
		 * */
		private String code;
		private String number;
		public Number(PhoneType type, String code, String number) {
			super();
			this.type = type;
			this.code = code;
			this.number = number;
		}
		public PhoneType getType() {
			return type;
		}
		public void setType(PhoneType type) {
			this.type = type;
		}
		public String getCode() {
			return code;
		}
		public void setCode(String code) {
			this.code = code;
		}
		public String getNumber() {
			return number;
		}
		public void setNumber(String number) {
			this.number = number;
		}
		
		@Override
		public String toString(){
			return String.format("[number:%s,type:%s,code:%s]", number,type.name() , code);
		}
		
	}
	
	
	/**
	 * 判断是否为手机号码
	 * 
	 * @param number 手机号码
	 * @return
	 * */
	public static boolean isCellPhone(String number){
		Matcher matcher = PATTERN_MOBITEPHONE.matcher(number);
		
		return matcher.matches();
	}
	
	/**
	 *判断是否为固定电话号码
	 *@param number
	 *			固定电话号码
	 *@return
	 * */
	public static boolean isFixedPhone(String number){
		Matcher matcher = PATTERN_FIXEDPHONE.matcher(number);
		
		return matcher.matches();
	}
	
	/**
	 * 获取固定号码中的区号
	 * @param strNumber
	 *
	 * @return
	 * */
	public static String getZipFromHomephone(String strNumber){
		Matcher matcher = PATTERN_ZIPCODE.matcher(strNumber);
		
		if(matcher.find()){
			return matcher.group(1);
		}
		return null;
	}
	
	/**
	 * 检查号码类型,并获取号码前缀,手机前7位,固话获取区号
	 * @param number
	 * @return
	 * */
	public static Number checkNumber(String _number){
		String number = _number;
		Number rtNum = null;
		
		if(number != null && number.length() >0){
			if(isCellPhone(number)){
				//如果是手机号码以0开始则去掉0
				if(number.charAt(0) == '0'){
					number = number.substring(1);
				}
				
				rtNum = new Number(PhoneType.CELLPHONE, number.substring(0, 7), _number);
			}else if(isFixedPhone(number)){
				//获取区号
				String zipCode = getZipFromHomephone(number);
				rtNum = new Number(PhoneType.FIXEDPHONE, zipCode, _number);
			}else{
				rtNum = new Number(PhoneType.INVALIDPHONE, null, _number);
			}
		}
		return rtNum;
	}
	
	
	
}
