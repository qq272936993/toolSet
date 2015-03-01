/**
 * 
 */
package cn.com.yws.toolset.android.db;


import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import cn.com.yws.toolset.android.db.handler.SqlLiteBeanHandler;
import cn.com.yws.toolset.android.db.handler.SqlLiteListHandler;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * <pre>
 * 文件名称: MySqlite.java
 * 包路径: cn.com.frm.andriod.db
 * 描述:	 子类重写该onCreate方法
 * 			其他方法都已经书写,如果用户根据需求拓展
 * 		 建议使用谷歌API的增删改查功能,因为其提供的方法有返回值
 * 内容摘要 
 *    作者: 杨文松
 *    版本: 1.0
 *    时间: 2014年12月24日
 *    邮箱: 272936993@qq.com
 * 修改历史:  
 * 修改日期           修改人员        版本	       修改内容  		说明
 * ---------------------------------------------- 
 * 
 * </pre>
 */
public abstract class BaseSqliteHelper extends SQLiteOpenHelper{

	
	/**
	 * @param context
	 * @param name
	 * @param factory
	 * @param version
	 */
	public BaseSqliteHelper(Context context , String databaseName , int version) {
		super(context, databaseName ,null, version );
	}

	
//	/**
//	 * 子类重写onClick方法
//	 * 
//	 * */
//	@Override
//	public void onCreate(SQLiteDatabase db) {
//		db.beginTransaction();
//		try {
//			 db.execSQL(context.getResources().getString(R.string.create_user_table));
//			
//			 db.setTransactionSuccessful();
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (NotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}finally{
//			db.endTransaction();
//		}
//	}

/*	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		//onCreate(db);
	}*/
	
	
	/**
	 * 获取可读的数据库DB
	 * */
	public SQLiteDatabase getReadSQLiteDatabase(){
		 return getReadableDatabase();
	}
	
	/**
	 * 获取可写的数据库DB
	 * */
	public SQLiteDatabase getWriterSQLiteDatabase(){
		 return getWritableDatabase();
	}
	
	
	/**
	 * 通过sql方式来执行查询操作
	 * @param sql sql语句
	 * @param 条件参数
	 * */
	public Cursor queryforSql(String sql,String[] selectionArgs){
		SQLiteDatabase db = getReadSQLiteDatabase();
		Cursor cursor = db.rawQuery(sql, selectionArgs);
		return cursor;
	}
	
	
	/**
	 * 通过条件查询返回游标对象
	 * @param table	表名
	 * @param columns 映射字段
	 * @param selection	查看条件
	 * @param selectionArgs	参数
	 * @param groupBy	分组
	 * @param having	子条件
	 * @param orderBy	排序
	 * */
	public Cursor queryBackCursor(String table, String[] columns, String selection, String[] selectionArgs, String groupBy, String having, String orderBy){
		Cursor cursor = queryBackCursor(false,table, columns, selection, selectionArgs, groupBy, having, orderBy , null);
		return cursor;
	}
	
	/**
	 * 通过条件查询返回游标对象
	 * @param table	表名
	 * @param columns 映射字段
	 * @param selection	查看条件
	 * @param selectionArgs	参数
	 * @param groupBy	分组
	 * @param having	子条件
	 * @param orderBy	排序
	 * @param limit 分页
	 * */
	public Cursor queryBackCursor(String table, String[] columns, String selection, String[] selectionArgs, String groupBy, String having, String orderBy, String limit){
		Cursor cursor = queryBackCursor(false,table, columns, selection, selectionArgs, groupBy, having, orderBy, limit);
		return cursor;
	}
	
	
	/**
	 * 通过条件查询返回游标对象
	 * @param distinct 去重
	 * @param table	表名
	 * @param columns 映射字段
	 * @param selection	查看条件
	 * @param selectionArgs	参数
	 * @param groupBy	分组
	 * @param having	子条件
	 * @param orderBy	排序
	 * @param limit 分页
	 * */
	public Cursor queryBackCursor(boolean distinct, String table, String[] columns, String selection, String[] selectionArgs, String groupBy, String having, String orderBy, String limit){
		SQLiteDatabase db = getReadSQLiteDatabase();
		Cursor cursor = db.query(distinct, table, columns, selection, selectionArgs, groupBy, having, orderBy, limit);
		return cursor;
	}
	
	
	/***
	 * 通过条件查询,返回List<Bean>集合
	 * @param table	表名
	 * @param columns 映射字段
	 * @param selection	查看条件
	 * @param selectionArgs	参数
	 * @param groupBy	分组
	 * @param having	子条件
	 * @param orderBy	排序
	 * @param handler	回调接口
	 * */
	public <T>List<T> queryBackListBean(String table, String[] columns, String selection, String[] selectionArgs, String groupBy, String having, String orderBy , SqlLiteListHandler<T> handler){
		return queryBackListBean(table, columns, selection, selectionArgs, groupBy, having, orderBy , null ,handler);
	}
	
	
	/***
	 * 通过条件查询,返回List<Bean>集合
	 * @param table	表名
	 * @param columns 映射字段
	 * @param selection	查看条件
	 * @param selectionArgs	参数
	 * @param groupBy	分组
	 * @param having	子条件
	 * @param orderBy	排序
	 * @param limit 分页
	 * @param handler	回调接口
	 * */
	public <T>List<T> queryBackListBean(String table, String[] columns, String selection, String[] selectionArgs, String groupBy, String having, String orderBy, String limit,SqlLiteListHandler<T> handler){
		return queryBackListBean(false,table, columns, selection, selectionArgs, groupBy, having, orderBy, limit , handler);
	}
	
	/***
	 * 通过条件查询,返回List<Bean>集合
	 * @param distinct 去重
	 * @param table	表名
	 * @param columns 映射字段
	 * @param selection	查看条件
	 * @param selectionArgs	参数
	 * @param groupBy	分组
	 * @param having	子条件
	 * @param orderBy	排序
	 * @param limit 分页
	 * @param handler	回调接口
	 * */
	public <T>List<T> queryBackListBean(boolean distinct, String table, String[] columns, String selection, String[] selectionArgs, String groupBy, String having, String orderBy, String limit,SqlLiteListHandler<T> handler){
		Cursor cursor = queryBackCursor(distinct, table, columns, selection, selectionArgs, groupBy, having, orderBy, limit);
		try {
			return handler.handler(cursor);
		}finally{
			closeCursor(cursor);
		}
	}
	
	
	
	/***
	 * 通过条件查询,返回Bean对象
	 * @param table	表名
	 * @param columns 映射字段
	 * @param selection	查看条件
	 * @param selectionArgs	参数
	 * @param groupBy	分组
	 * @param having	子条件
	 * @param orderBy	排序
	 * @param handler	回调接口
	 * */
	public <T>T queryBackBean(String table, String[] columns, String selection, String[] selectionArgs, String groupBy, String having, String orderBy , SqlLiteBeanHandler<T> handler){
		Cursor cursor = queryBackCursor(table, columns, selection, selectionArgs, groupBy, having, orderBy);
		try {
			return handler.handler(cursor);
		}finally{
			closeCursor(cursor);
		}
	}
	
	
	/***
	 * 通过条件查询,返回Bean对象
	 * @param table	表名
	 * @param columns 映射字段
	 * @param selection	查看条件
	 * @param selectionArgs	参数
	 * @param groupBy	分组
	 * @param having	子条件
	 * @param orderBy	排序
	 * @param limit 分页
	 * @param handler	回调接口
	 * */
	public <T>T queryBackBean(String table, String[] columns, String selection, String[] selectionArgs, String groupBy, String having, String orderBy, String limit,SqlLiteBeanHandler<T> handler){
		Cursor cursor = queryBackCursor(false,table, columns, selection, selectionArgs, groupBy, having, orderBy, limit);
		try {
			return handler.handler(cursor);
		} finally{
			closeCursor(cursor);
		}
	}
	
	
	/***
	 * 通过条件查询,返回Bean对象
	 * @param distinct 去重
	 * @param table	表名
	 * @param columns 映射字段
	 * @param selection	查看条件
	 * @param selectionArgs	参数
	 * @param groupBy	分组
	 * @param having	子条件
	 * @param orderBy	排序
	 * @param limit 分页
	 * @param handler	回调接口
	 * */
	public <T>T queryBackListBean(boolean distinct, String table, String[] columns, String selection, String[] selectionArgs, String groupBy, String having, String orderBy, String limit,SqlLiteBeanHandler<T> handler){
		Cursor cursor = queryBackCursor(distinct, table, columns, selection, selectionArgs, groupBy, having, orderBy, limit);
		try {
			return handler.handler(cursor);
		}finally{
			closeCursor(cursor);
		}
	}
	
	

	
	/**
	 * 通过查询的SQL转换成封装对象
	 * @param sql SQL查询语句
	 * @param selectionArgs 查询条件参数
	 * @param 转换成的domain对象
	 * @throws Exception 
	 * */
	public <T>List<T> queryForSql2ListBean(String sql,String[] selectionArgs,T obj) throws Exception{
		Cursor cursor = queryforSql(sql, selectionArgs);
		List<T> result = new ArrayList<T>();
		
		try {
			int count = cursor.getCount();
			for(int i=0 ; i < count ; i++){
				boolean b = cursor.moveToPosition(i);
				if(b){
					T newObj = invokeToObj(obj, cursor);
					result.add(newObj);
				}
			}
		} finally{
			closeCursor(cursor);
		}
		
		
		return result;
	}
	
	
	/**
	 * 查询单条记录,只获取结果集中的第一条进行转换
	 * @param sql SQL查询语句
	 * @param selectionArgs 查询的参数
	 * @param 需要转换的对象
	 * */
	public <T>T queryForSql2Bean(String sql,String[] selectionArgs,T cls)throws Exception{
		Cursor cursor = queryforSql(sql, selectionArgs);
		try{
			if(cursor.moveToFirst())
				 return invokeToObj(cls, cursor);
		}finally{
			closeCursor(cursor);
		}
		return null;
	}

	/**
	 * @param cls
	 * @param cursor
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 */
	@SuppressWarnings("unchecked")
	private <T>T invokeToObj(T obj, Cursor cursor)
			throws InstantiationException, IllegalAccessException,
			NoSuchMethodException, InvocationTargetException {
		
		@SuppressWarnings("rawtypes")
		Class cls = obj.getClass();
		T newObj = (T) cls.newInstance();
		String[] names = cursor.getColumnNames();
		
		for(String name : names){
			try{
				Field field = cls.getDeclaredField(name);
				field.setAccessible(true);
				
				Class<?> type = field.getType();
				if(type.equals(Integer.class) || type.equals(int.class))
					field.set(newObj, cursor.getInt(cursor.getColumnIndex(name)));
				else if(type.equals(String.class))
					field.set(newObj, cursor.getString(cursor.getColumnIndex(name)));
				else if(type.equals(Double.class) || type.equals(double.class))
					field.set(newObj, cursor.getDouble(cursor.getColumnIndex(name)));
				else if(type.equals(Float.class) || type.equals(float.class))
					field.set(newObj, cursor.getFloat(cursor.getColumnIndex(name)));
				else if(type.equals(Long.class) || type.equals(long.class))
					field.set(newObj, cursor.getLong(cursor.getColumnIndex(name)));
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return newObj;
	}
	
	
	/**
	 * 执行一条SQL语句(增删改)
	 * 		注意:使用exeSQL方法
	 * @param sql SQL语句
	 * @param bindArgs 参数
	 * */
	public boolean execSQL(String sql , Object[] bindArgs){
		boolean flag = false;
		try {
			SQLiteDatabase db = getWritableDatabase();
			db.execSQL(sql, bindArgs);
			flag = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return flag;
	}
	
	/**
	 * 参数添加操作,非sql执行方式
	 * @param table
	 * @param nullColumnHack
	 * @param values
	 * @return 插入数据的行号,如果为-1则代表添加失败,否则表示添加成功
	 * */
	public long insert(String table, String nullColumnHack, ContentValues values){
		
		SQLiteDatabase db = getWritableDatabase();
		return db.insert(table, nullColumnHack, values);
	}
	
	
	/**
	 * 参数删除操作,非sql执行方式
	 * @param table
	 * @param whereClause
	 * @param whereArgs
	 * @return 返回0代表的是没有删除任何数据,返回其他的数字则表示影响了多少行数 rowcount
	 * */
	public int remove(String table, String whereClause, String[] whereArgs){
		SQLiteDatabase db = getWritableDatabase();
		return db.delete(table, whereClause, whereArgs);
	}
	
	
	/**
	 * 参数更新操作,非sql执行方式
	 * @param table
	 * @param values
	 * @param whereClause
	 * @param whereArgs
	 * @return 更新的操作影响了几行,0代表一行也没有更新成功
	 * */
	public int update(String table, ContentValues values, String whereClause, String[] whereArgs){
		SQLiteDatabase db = getWritableDatabase();
		return db.update(table, values, whereClause, whereArgs);
	}
	
	
	
	
	public void closeCursor(Cursor cursor){
		if(cursor != null && !cursor.isClosed()){
			cursor.close();
			cursor = null;
		}
	}
	
	

}
