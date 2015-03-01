package cn.com.yws.toolset.android.dbhight.factory;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import android.database.sqlite.SQLiteDatabase;

/**
 * <pre>
 * 文件名称: AutoTransactionFactory.java
 * 包路径: cn.com.frm.andriod.db.factory cn.com.talkweb.sch.controller.build
 * 描述:	 
 * 		自动事物代理工厂类
 * 原理:
 * 		使用动态代理创建相关的服务.当方法调用前执行开启事务,执行完成后标记
 * 事物执行成功,最后关闭事物,关闭流
 *  
 * 
 * 内容摘要 
 *    作者: 杨文松
 *    版本: 1.0
 *    时间: 2015年1月6日
 *    邮箱: 272936993@qq.com
 * 修改历史:  
 * 修改日期           修改人员        版本	       修改内容  		说明
 * ---------------------------------------------- 
 * 
 * </pre>
 */
public class AutoTransactionFactory {
	
	private final static String TAG = "AutoTransactionFactory";
//	private SQLiteDatabase db;
//	public AutoTransactionFactory(SQLiteDatabase db){
//		this.db = db;
//	}
	
	private AutoTransactionFactory(){
		throw new RuntimeException("don't new this object!");
	}
	
	
	public static Object createAutoTransaction(final SQLiteDatabase db,Class<?> clazz) throws Exception{
		Class<?>[] classes = clazz.getInterfaces();
		if(classes == null || classes.length < 1){
			throw new RuntimeException(clazz+"类需要有相关的接口");
		}
		
		final Object obj = clazz.newInstance();
		return Proxy.newProxyInstance(AutoTransactionFactory.class.getClassLoader(), classes, 
				new InvocationHandler() {
					
					@Override
					public Object invoke(Object proxy, Method method, Object[] args)
							throws Throwable {
//						boolean isTransactio = method.isAnnotationPresent(Transaction.class);
//						Object backVal = null;
						//如果包含了Transaction标签则执行事物
//						if(isTransactio){
//							db.beginTransaction();
//							
//							try {
//								backVal = method.invoke(obj, args);
//								db.setTransactionSuccessful();
//							} catch (Exception e) {
//								e.printStackTrace();
//								Log.d(TAG, "---->"+e.getMessage());
//							}finally{
//								db.endTransaction();
//							}
//						}else
//							backVal = method.invoke(obj, args);	//没有则直接执行
//						
//						db.close();
//						return backVal;
						return null;
					}
				});
	}
	
	
	
	
}
