package cn.com.yws.toolset.base.common.sql.jdbc;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.beans.PropertyVetoException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.ResourceBundle;


import sun.org.mozilla.javascript.internal.ObjArray;

import com.mchange.v2.c3p0.ComboPooledDataSource;



/**
 * <pre>
 * 文件名称: JdbcBase.java
 * 包路径: cn.com.frm.sql.jdbc cn.com.talkweb.sch.controller.build
 * 描述:	 
 * 		需要依赖出c3p0.jar包
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
public class JdbcBase {
	
	public static ComboPooledDataSource cpds = null;
	
	
//	public static Connection getConnection(String driver, String url, String username, String password){
//		Connection conn = null;
//		try {
//			Class.forName(driver);
//		
//			conn = DriverManager.getConnection(url, username, password);
//		} catch (ClassNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return conn;
//	}
	
	public static Connection getC3p0Connection(String driver, String url, String username, String password) throws Exception{
		if(cpds == null){
			synchronized (JdbcBase.class) {
				if(cpds == null){
					cpds = new ComboPooledDataSource();
					cpds.setDriverClass( driver ); //loads the jdbc driver            
					cpds.setJdbcUrl( url );
					cpds.setUser(username);                                  
					cpds.setPassword(password);
				}
			}
		}
		
		return cpds.getConnection();
	}
	
	/**
	 * 直接通过C3p0获取Connection对象
	 * 注意:
	 * 	   在classpath下要有c3p0-config.xml或者c3p0.properties 文件才行
	 * @throws SQLException 
	 * */
	public static Connection getC3p0Connection(String c3p0Name) throws SQLException{
		if(cpds == null){
			synchronized (JdbcBase.class) {
				if(cpds == null){
					cpds =c3p0Name != null && !"".equals(c3p0Name) ?
						 new ComboPooledDataSource(c3p0Name) : new ComboPooledDataSource();
				}
			}
		}
	    return cpds.getConnection();
	}
	
	/**
	 * 
	 * 直接指定properties配置文件名称,读取配置
	 * @param proName 配置文件
	 * @throws Exception 
	 * */
	public static Connection readPropertiseGetConnection(String proName) throws Exception{
		ResourceBundle bundle = ResourceBundle.getBundle(proName);
		return getC3p0Connection(
							 bundle.getString("jdbc.driver"),
							 bundle.getString("jdbc.url"),
							 bundle.getString("jdbc.username"),
							 bundle.getString("jdbc.password")
		);
	}
	
	
	/**
	 * @TODO 通过sql语句查询出多条集合
	 * 
	 * @param conn 数据库连接
	 * @param sql 查询sql语句
	 * @param param 查询参数
	 * */
	public static List<Map<String,Object>> queryForList(Connection conn ,String sql,Object[] params) throws SQLException{
		PreparedStatement ps = conn.prepareStatement(sql);
		if(ps.getParameterMetaData().getParameterCount() != params.length)
			throw new IllegalArgumentException("错误传参,参数个数不一致..!");
		for(int i=0; params != null && i < params.length; i++)
			ps.setObject(i+1,  params[i]);
		ResultSet rs = ps.executeQuery();
		
		List<Map<String,Object>> result = new ArrayList<Map<String,Object>>();
		while(rs.next()){
			Map<String,Object> items = new HashMap<String,Object>();
			//获取每一行记录的对象
			ResultSetMetaData metaData = rs.getMetaData();
			//总共有多少列
			int columnCount = metaData.getColumnCount();
			for(int i =1; i <= columnCount; i++){
				String columnName = metaData.getColumnLabel(i);
				//int type_id = metaData.getColumnType(i);	//获取类型的编号
				Object obj = rs.getObject(columnName);
				items.put(columnName, obj);
			}
			result.add(items);
		}
		
		return result;
	}
	
	/**
	 * @TODO 将查询出的结果集放入Map集合中
	 * 
	 * @param conn 数据库连接对象
	 * @param sql 执行的sql语句
	 * @param params 注入的参数
	 * @throws SQLException
	 * */
	public static List<Map<String,Object>> queryForList(Connection conn ,String sql,List<Object> params) throws SQLException{
		PreparedStatement ps = conn.prepareStatement(sql);
		if(ps.getParameterMetaData().getParameterCount() != params.size())
			throw new IllegalArgumentException("错误传参,参数个数不一致..!");
		
		for(int i=0; params != null && i < params.size(); i++)
			ps.setObject(i+1,  params.get(i));
		
		ResultSet rs = ps.executeQuery();
		
		List<Map<String,Object>> result = new ArrayList<Map<String,Object>>();
		while(rs.next()){
			Map<String,Object> items = new HashMap<String,Object>();
			//获取每一行记录的对象
			ResultSetMetaData metaData = rs.getMetaData();
			//总共有多少列
			int columnCount = metaData.getColumnCount();
			for(int i =1; i <= columnCount; i++){
				String columnName = metaData.getColumnLabel(i);
				//int type_id = metaData.getColumnType(i);	//获取类型的编号
				Object obj = rs.getObject(columnName);
				items.put(columnName, obj);
			}
			result.add(items);
		}
		
		return result;
	}
	
	/**
	 * @TODO 查询数据库转换结果集对象
	 * 
	 * @param conn 数据库连接
	 * @param t_cls 需要转换的对象
	 * @param sql 执行的sql语句
	 * @param params 注入参数
	 * @throws Exception
	 * */
	public static <T>List<T> queryForListBean(Connection conn,Class t_cls,String sql,List<Object> params) throws Exception {
		PreparedStatement ps = conn.prepareStatement(sql);
		if(ps.getParameterMetaData().getParameterCount() != params.size())
			throw new IllegalArgumentException("错误传参,参数个数不一致..!");

		for(int i=0; params != null && i < params.size(); i++)
			ps.setObject(i+1,  params.get(i));
		
		ResultSet rs = ps.executeQuery();
		List<T> result = new ArrayList<T>();
		while(rs.next()){	//遍历结果集
			Object obj = t_cls.newInstance();	//示例化对象
			Field[] fields = t_cls.getDeclaredFields();
			for (Field field : fields) {
				field.setAccessible(true);
				field.set(obj, rs.getObject(field.getName()));
			}
			
			result.add((T)obj);
		}
		
		return result;
	}
	
	/**
	 * 执行sql语句
	 * @param conn 数据库连接
	 * @param sql sql语句
	 * @param paramArgs 注入参数
	 * */
	public static boolean excutSQL(Connection conn,String sql,Object[] paramArgs) throws SQLException{
		PreparedStatement ps = conn.prepareStatement(sql);
		for(int i=0; paramArgs != null && i < paramArgs.length; i++)
			ps.setObject(i+1,  paramArgs[i]);
		
		 return ps.execute();
	}
	
	
	
	/**
	 * 关闭各种连接
	 * @param rs 数据结果集
	 * @param ps Statement子类对象
	 * @param conn 数据库连接
	 * */
	public static void colse(ResultSet rs ,PreparedStatement ps, Connection conn){
		if(rs != null){
			try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			rs = null;
		}
		
		if(ps != null){
			try {
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			ps = null;
		}
		
		if(conn != null){
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	
	
	/**
	 * 提交事物
	 * @param conn
	 * */
	public static void commit(Connection conn){
		try {
			conn.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 开启事务
	 * @param conn
	 * */
	public static void openTransaction(Connection conn){
		try {
			//关闭了自动提交.就相当于start transaction
			if(conn != null)
				conn.setAutoCommit(false);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 回滚事务
	 * @param conn
	 * */
	public static void rollback(Connection conn){
		try {
			if(conn != null)
				conn.rollback();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	
	
	
	//====================================================== 测试操作 =================================================
	public void updateTest() throws Exception{
		//更新操作
		Connection conn = readPropertiseGetConnection("jdbc");
		try {
			openTransaction(conn);//开启事物
			excutSQL(conn, "update news set priority =?", new Object[]{"5"});
			excutSQL(conn, "update news set priority =? where id=?", new Object[]{"2" , 3});
			commit(conn);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			colse(null, null, conn);
		}
	}
	
	
//	@Test
	public void queryTest() throws Exception{
		Connection conn = readPropertiseGetConnection("jdbc");
		//查询操作,查询操作不需要提供事物
		List<Map<String,Object>> records = queryForList(conn, "select * from news", new Object[]{});
		for(Map<String,Object> record : records){
			for(Entry<String, Object> entry : record.entrySet()){
				System.out.print(entry.getKey() +":" + entry.getValue());
			}
			System.out.println();
		}
	}
	
	
	
	
	
	
	
	
}
