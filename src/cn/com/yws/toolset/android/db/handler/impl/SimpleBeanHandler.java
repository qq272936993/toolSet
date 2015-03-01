/**
 * 
 */
package cn.com.yws.toolset.android.db.handler.impl;

import java.lang.reflect.Field;



import cn.com.yws.toolset.android.db.handler.SqlLiteBeanHandler;
import android.database.Cursor;

/**
 * <pre>
 * 文件名称: SimpleBeanHandler.java
 * 包路径: cn.com.yws.comm cn.com.talkweb.sch.controller.build
 * 描述:	 
 * 内容摘要 
 *    作者: 杨文松
 *    版本: 1.0
 *    时间: 2014年12月28日
 *    邮箱: 272936993@qq.com
 * 修改历史:  
 * 修改日期           修改人员        版本	       修改内容  		说明
 * ---------------------------------------------- 
 * 
 * </pre>
 */
public class SimpleBeanHandler<T> implements SqlLiteBeanHandler<T> {
	private T cls;
	
	public SimpleBeanHandler(T cls){
		this.cls = cls;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public T handler(Cursor cursor) {
		@SuppressWarnings("rawtypes")
		Class classes = cls.getClass();
		try {
			Object newObj = classes.newInstance();
			Field[] fileds = classes.getFields();
			if(cursor.moveToNext()){
				for(Field field : fileds){
					field.setAccessible(true);
					String fieldName = field.getName();
					
					Class<?> type = field.getType();
					if(type.equals(Integer.class) || type.equals(int.class))
						field.set(newObj, cursor.getInt(cursor.getColumnIndex(fieldName)));
					else if(type.equals(String.class))
						field.set(newObj, cursor.getString(cursor.getColumnIndex(fieldName)));
					else if(type.equals(Double.class) || type.equals(double.class))
						field.set(newObj, cursor.getDouble(cursor.getColumnIndex(fieldName)));
					else if(type.equals(Float.class) || type.equals(float.class))
						field.set(newObj, cursor.getFloat(cursor.getColumnIndex(fieldName)));
					else if(type.equals(Long.class) || type.equals(long.class))
						field.set(newObj, cursor.getLong(cursor.getColumnIndex(fieldName)));
				}
			}
			//关闭光标
			cursor.close();
			
			return (T) newObj;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		return null;
	}

}
