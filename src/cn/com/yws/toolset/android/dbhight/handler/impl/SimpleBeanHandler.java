/**
 * 
 */
package cn.com.yws.toolset.android.dbhight.handler.impl;

import java.lang.reflect.Field;

import cn.com.yws.toolset.android.dbhight.handler.SqlLiteBeanHandler;
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
					int columnIndex = cursor.getColumnIndex(fieldName);

					int type = cursor.getType(columnIndex);
					switch (type) {
						case Cursor.FIELD_TYPE_STRING:
							field.set(newObj, cursor.getString(columnIndex));
							continue;
						case Cursor.FIELD_TYPE_INTEGER :
							field.set(newObj, cursor.getInt(columnIndex));
							continue;
						case Cursor.FIELD_TYPE_FLOAT :
							field.set(newObj, cursor.getFloat(columnIndex));
							continue;
						case Cursor.FIELD_TYPE_BLOB :
							field.set(newObj, cursor.getBlob(columnIndex));
							continue;
						case Cursor.FIELD_TYPE_NULL :
							field.set(newObj, null);
							continue;
					}
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
