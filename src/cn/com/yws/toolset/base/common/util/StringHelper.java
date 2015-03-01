package cn.com.yws.toolset.base.common.util;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <pre>
 * 文件名称: StringHelper.java
 * 包路径: cn.com.yws.toolset.base.common.util cn.com.talkweb.sch.controller.build
 * 描述:	 
 * 内容摘要 
 *    作者: 杨文松
 *    版本: 1.0
 *    时间: 2015年1月14日
 *    邮箱: 272936993@qq.com
 * 修改历史:  
 * 修改日期           修改人员        版本	       修改内容  		说明
 * ---------------------------------------------- 
 * 
 * </pre>
 */
public class StringHelper {

	 private StringHelper() {
	        throw new AssertionError();
	 }
	
	 /**
	  * 是否是简单的值
	  * @param str
	  * @return 
	  * */
    public static boolean isEmpty(String str) {
        return (str == null || str.trim().length() == 0);
    }
    
    public static boolean isEmpty(CharSequence str) {
        return (str == null || str.length() == 0);
    }
    
    /**
     * 判断两个String对象是否相同
     * @param actual
     * @param expected
     * */
    public static boolean isEquals(String actual, String expected) {
        return ObjectHelper.isEquals(actual, expected);
    }
    
    /**
     * 将该对象转换为String类型数据如果为
     * @param str
     * @return 如果为null则返回空字符串,如果是String对象,
     * 		则直接返回,如果是其他对象,则调用toStirng()方法并返回
     * */
    public static String nullStrToEmpty(Object str) {
        return (str == null ? "" : (str instanceof String ? (String)str : str.toString()));
    }
    
    /**
     * 将字符串的首字母大写
     * */
    public static String capitalizeFirstLetter(String str) {
        if (isEmpty(str)) {
            return str;
        }

        char c = str.charAt(0);
        return (!Character.isLetter(c) || Character.isUpperCase(c)) ? str : new StringBuilder(str.length())
                .append(Character.toUpperCase(c)).append(str.substring(1)).toString();
    }
    
    /**
	 * 字符串截取（区分半角、全角）
	 * @param str  字符串
	 * @param limit 长度
	 * @return
	 */
	public static String left(String str, int limit) {
		if (getLength(str) <= limit) {
			return str;
		}
		char[] chars = str.toCharArray();
		int charLenSum = 0;
		String result = "";
		for (int i = 0; i < chars.length; i++) {
			int charLen = isDbcCase(chars[i]) ? 1 : 2;
			if (charLenSum + charLen > limit) {
				return result;
			}
			charLenSum += charLen;
			result += chars[i];
			if (charLenSum == limit) {
				return result;
			}
		}
		return "";
	}
    
    /**
     * 使用URLEncoder编码,并用UTF-8格式
     * */
    public static String utf8Encode(String str) {
        if (!isEmpty(str) && str.getBytes().length != str.length()) {
            try {
                return URLEncoder.encode(str, "UTF-8");
            } catch (UnsupportedEncodingException e) {
                throw new RuntimeException("UnsupportedEncodingException occurred. ", e);
            }
        }
        return str;
    }
    
    /**
     * 使用URLEncoder编码,并用UTF-8格式,如果出现错误,则使用defaultReturn
     * */
    public static String utf8Encode(String str, String defultReturn) {
        if (!isEmpty(str) && str.getBytes().length != str.length()) {
            try {
                return URLEncoder.encode(str, "UTF-8");
            } catch (UnsupportedEncodingException e) {
                return defultReturn;
            }
        }
        return str;
    }
    
    /**
     * 获取html中的href路径
     * @param href
     * */
    public static String getHrefInnerHtml(String href) {
        if (isEmpty(href)) {
            return "";
        }

        String hrefReg = ".*<[\\s]*a[\\s]*.*>(.+?)<[\\s]*/a[\\s]*>.*";
        Pattern hrefPattern = Pattern.compile(hrefReg, Pattern.CASE_INSENSITIVE);
        Matcher hrefMatcher = hrefPattern.matcher(href);
        if (hrefMatcher.matches()) {
            return hrefMatcher.group(1);
        }
        return href;
    }
    
    /**
	 * Description: 		changeEspecial 				转化html的&lt;等此类字符转化为<字符
	 * @param 				str							包含特殊字符的字符	
	 * @return 				String 						转换之后的字符
	 */
	public static String changeEspecial(String str) {
		// 判断是否为空
		if (null == str|| ("").equals(str.trim())) {
			return "";
		}
		// 返回字符串
		String strReturn = str.replace("&#39;", "'");
		strReturn = strReturn.replace("&#92;", "\"");
		strReturn = strReturn.replace("&#60;", "<");
		strReturn = strReturn.replace("&#62;", ">");
		strReturn = strReturn.replace("&#95;", "_");
		strReturn = strReturn.replace("&#37;", "%");
		strReturn = strReturn.replace("&#37;", "?");
		// 返回新字符串
		return strReturn;
	}
	
	/**
	 * Description: 		changeNoEspecial 			转化html的<等此类字符转化为&lt;字符
	 * @param 				str							包含特殊字符的字符	
	 * @return 				String 						转换之后的字符
	 */
	public static String changeNoEspecial(String str) {
		// 判断是否为空
		if (null == str|| ("").equals(str.trim())) {
			return "";
		}
		// 返回字符串
		String strReturn = str.replace("'", "&#39;");
		strReturn = strReturn.replace("\"", "&#92;");
		strReturn = strReturn.replace("<", "&#60;");
		strReturn = strReturn.replace(">", "&#62;");
		strReturn = strReturn.replace("_", "&#95;");
		strReturn = strReturn.replace("%", "&#37;");
		strReturn = strReturn.replace("?", "&#37;");
		// 返回新字符串
		return strReturn;
	}
    
    
	/**
	 * 半角、全角字符判断
	 * @param c 字符
	 * @return true：半角； false：全角
	 */
	public static boolean isDbcCase(char c) {
		// 基本拉丁字母（即键盘上可见的，空格、数字、字母、符号）
		if (c >= 32 && c <= 127) {
			return true;
		}
		// 日文半角片假名和符号
		else if (c >= 65377 && c <= 65439) {
			return true;
		}
		return false;
	}
	
	/**
	 * 字符串长度取得（区分半角、全角）
	 * @param str 字符串
	 * @return 字符串长度
	 */
	public static int getLength(String str) {
		int len = 0;
		for (int i = 0; i < str.length(); i++) {
			char c = str.charAt(i);
			if (isDbcCase(c)) { // 半角
				len = len + 1;
			} else { // 全角
				len = len + 2;
			}
		}
		return len;
	}
	
	
    /**
     * 将半角的字符转换为全角
     * @param s 需要转换的字符
     * */
    public static String fullWidthToHalfWidth(String s) {
        if (isEmpty(s)) {
            return s;
        }

        char[] source = s.toCharArray();
        for (int i = 0; i < source.length; i++) {
            if (source[i] == 12288) {
                source[i] = ' ';
                // } else if (source[i] == 12290) {
                // source[i] = '.';
            } else if (source[i] >= 65281 && source[i] <= 65374) {
                source[i] = (char)(source[i] - 65248);
            } else {
                source[i] = source[i];
            }
        }
        return new String(source);
    }

    /**
     * 将全角的数据转换成半角数据
     * @param s 需要转换的数据
     * */
    public static String halfWidthToFullWidth(String s) {
        if (isEmpty(s)) {
            return s;
        }

        char[] source = s.toCharArray();
        for (int i = 0; i < source.length; i++) {
            if (source[i] == ' ') {
                source[i] = (char)12288;
                // } else if (source[i] == '.') {
                // source[i] = (char)12290;
            } else if (source[i] >= 33 && source[i] <= 126) {
                source[i] = (char)(source[i] + 65248);
            } else {
                source[i] = source[i];
            }
        }
        return new String(source);
    }
    
    /**
	 * Description: 		filterEspecial 			转化特殊字符为中文字符
	 * @param 				str						包含特殊字符的字符	
	 * @return 				String 					转换之后的字符
	 */
	public static String filterEspecial(String str) {
		// 判断是否为空
		if (null == str|| ("").equals(str.trim())) {
			return "";
		}
		// 返回字符串
		StringBuffer stringbuffer = new StringBuffer();
		int j = str.length();
		// 替换字符
		for (int i = 0; i < j; i++) {
			char c = str.charAt(i);
			switch (c) {
			case '\'': 	// '
				stringbuffer.append("‘");
				break;
			case '<': 	// <
				stringbuffer.append("〈");
				break;
			case '>': 	// >
				stringbuffer.append("〉");
				break;
			case '"': 	// "
				stringbuffer.append("“");
				break;
			case '_': 	// _
				stringbuffer.append("＿");
				break;
			case '%': 	// %
				stringbuffer.append("％");
				break;
			case '?': 	// ?
				stringbuffer.append("？");
				break;
			case '{': 	// {
				stringbuffer.append("｛");
				break;
			case '}': 	// }
				stringbuffer.append("｝");
				break;
			case '/': 	// /
				stringbuffer.append("／");
				break;
			case '\\': 	// \
				stringbuffer.append("＼");
				break;
			default:
				stringbuffer.append(c);
				break;
			}
		}
		// 返回新字符串
		return stringbuffer.toString();
	}
    
    
}
