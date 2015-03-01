/**
 * 
 */
package cn.com.yws.toolset.android.dbhight.handler;

import java.util.List;

import android.database.Cursor;

/**
 * <pre>
 * 文件名称: SqlLiteHandler.java
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
public interface SqlLiteListHandler<T>{
	
	public  List<T> handler(Cursor cursor);
	
}
