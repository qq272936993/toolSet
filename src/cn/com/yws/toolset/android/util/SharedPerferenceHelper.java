/**
 * 
 */
package cn.com.yws.toolset.android.util;

import java.util.Map;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

/**
 * <pre>
 * 文件名称: SharedPerferenceUtils.java
 * 包路径: cn.com.yws.comm cn.com.talkweb.sch.controller.build
 * 描述:	 
 * 		Shared Preference是一种简单的、轻量级的名称/值对(NVP)机制,用于保存原始
 * 	应用程序数据,最常见的应用程序首项(当存储UI状态、用户首选项或者应用程序设置时)
 * 
 * 		
 * 		提示:
 * 			创建自己的activity来控制用户首选项被认为是一种不好的做法.将使用Preference Screen类,用标准的设置屏幕替换该Activity
 * 
 * 内容摘要 
 *    作者: 杨文松
 *    版本: 1.0
 *    时间: 2014年12月22日
 *    邮箱: 272936993@qq.com
 * 修改历史:  
 * 修改日期           修改人员        版本	       修改内容  		说明
 * ---------------------------------------------- 
 * 
 * </pre>
 */
public class SharedPerferenceHelper {

	private Context context;
	
	public SharedPerferenceHelper(Context context){
		this.context = context;
	}
	
	
	/**
	 * 书写共享参数
	 * @param name 文件名称
	 * @param 保存的参数	支持Boolean\Float\Integer\Long\String
	 * */
	public boolean writerVals(String name,Map<String,Object> params){
		boolean flag = false; 
		if(params != null && !params.isEmpty()){
			 SharedPreferences sharedPreferences = context.getSharedPreferences(name, Context.MODE_PRIVATE);
			 Editor editor = sharedPreferences.edit();
			 
			 //遍历参数:保存至editor中,使用Class类获取该数据为什么类型.然后调用不同的方法.
			 for(Map.Entry<String, Object> entry : params.entrySet()){
				 String key = entry.getKey();
				 Object val = entry.getValue();
				 if(val.getClass().equals(Boolean.class) || val.getClass().equals(boolean.class))
					 editor.putBoolean(key, (Boolean)val);
				 else if(val.getClass().equals(Float.class) || val.getClass().equals(float.class))
					 editor.putFloat(key, (Float)val);
				 else if(val.getClass().equals(Integer.class) || val.getClass().equals(int.class))
					 editor.putInt(key, (Integer)val);
				 else if(val.getClass().equals(Long.class) || val.getClass().equals(long.class))
					 editor.putLong(key, (Long)val);
				 else if(val.getClass().equals(String.class) )
					 editor.putString(key, (String)val);
				 
			 }
			 
			 flag = editor.commit();		//如果不调用editor的话,文件或者数据将不会修改.
		 }
		return flag;
	}
	
	/***
	 * 读取共享参数
	 * @param name 文件名称
	 * @return Map<String,?>
	 * */
	public Map<String , ?> readVals(String name){
		Map<String , ?> map = null;
	
		SharedPreferences sharedPreferences = context.getSharedPreferences(name, Context.MODE_PRIVATE);
		map = sharedPreferences.getAll();
		
		return map;
	}
	
	
}
