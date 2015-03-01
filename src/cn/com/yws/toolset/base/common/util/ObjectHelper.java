package cn.com.yws.toolset.base.common.util;


/**
 * <pre>
 * 文件名称: ObjectUtils.java
 * 包路径: cn.com.yws.toolset.base.common.util cn.com.talkweb.sch.controller.build
 * 描述:	 数组工具类
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
public class ObjectHelper {

	   private ObjectHelper() {
	        throw new AssertionError();
	   }
	   
	   
	   /**
	    * 判断对象是否相同, 相同(true)/不相同(false)
	    * @param actual
	    * @param expected
	    * @return boolean
	    * */
	   public static boolean isEquals(Object actual, Object expected) {
		   return actual == expected || (actual == null ? expected == null : actual.equals(expected));
	   }
	   
	   
	   /**
	    * 将null值转换为空字符串,如果不是null,直接转换为String或者调用toString()方法
	    * @param str
	    * @return String
	    * */
	   public static String nullStrToEmpty(Object str){
		   return (str == null ? "" : (str instanceof String ? (String)str : str.toString()));
	   }
	   
	   
	   /**
	    * 将long的数组转换成Long对象的数组
	    * @param source
	    * @return Long[]
	    * */
	   public static Long[] transformLongArray(long[] source){
		   Long[] destin = new Long[source.length];
		   for(int i = 0; i < source.length; i++){
			   destin[i] = source[i];
		   }
		   return destin;
	   }
	   
	   
	    /**
	     * 将Long对象的数组转换成long的数组
	     * 
	     * @param source
	     * @return
	     */
	    public static long[] transformLongArray(Long[] source) {
	        long[] destin = new long[source.length];
	        for (int i = 0; i < source.length; i++) {
	            destin[i] = source[i];
	        }
	        return destin;
	    }
	
	   
	    /**
	     * 将int类型的数组转换成Integer类型的数组
	     * 
	     * @param source
	     * @return
	     */
	    public static Integer[] transformIntArray(int[] source) {
	        Integer[] destin = new Integer[source.length];
	        for (int i = 0; i < source.length; i++) {
	            destin[i] = source[i];
	        }
	        return destin;
	    }
	
	    /**
	     * 将Integer类型的数组转换成int类型的数组
	     * 
	     * @param source
	     * @return
	     */
	    public static int[] transformIntArray(Integer[] source) {
	        int[] destin = new int[source.length];
	        for (int i = 0; i < source.length; i++) {
	            destin[i] = source[i];
	        }
	        return destin;
	    }
	
	    
	    /***
	     * 比较两个对象
	     * */
	    @SuppressWarnings({"unchecked", "rawtypes"})
	    public static <V> int compare(V v1, V v2) {
	        return v1 == null ? (v2 == null ? 0 : -1) : (v2 == null ? 1 : ((Comparable)v1).compareTo(v2));
	    }
}
