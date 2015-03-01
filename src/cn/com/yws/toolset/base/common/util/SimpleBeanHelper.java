package cn.com.yws.toolset.base.common.util;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;


/**
 * <pre>
 * 文件名称: SimpleBeanUtil.java
 * 包路径: cn.com.frm.base.bean
 * 描述:
 * 内容摘要 
 *    作者: 杨文松
 *    版本: 1.0
 *    时间: 2014-7-18
 *    邮箱: 272936993@qq.com
 * 修改历史:  
 * 修改日期           修改人员        版本	       修改内容  		说明
 * ---------------------------------------------- 
 * 
 * </pre>
 */
public class SimpleBeanHelper {
	
	/**
	 * 简单数据转换为bean
	 * @param obj
	 * @param param
	 * @throws Exception 
	 * */
	public static <T>void Map2Bean(T obj,Map<String,Object> param) throws Exception{
		if(param != null){
			Field[] fields = obj.getClass().getDeclaredFields();
			for(Field field : fields){
				String name = field.getName();
				field.set(obj, param.get(name));
			}
			
		}
	}
	
	public static <T>Map<String, Object> Bean2Map(T obj) throws Exception{
		Map<String, Object> paramMap = new HashMap<String,Object>();
		if(obj != null){
			Field[] fields =obj.getClass().getDeclaredFields();
			for(Field field : fields){
				Object val = field.get(obj);
				paramMap.put(field.getName(), val);
			}
		}
		
		return paramMap;
	}
	
	
	
}


	