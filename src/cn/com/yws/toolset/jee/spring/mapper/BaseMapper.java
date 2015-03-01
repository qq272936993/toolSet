package cn.com.yws.toolset.jee.spring.mapper;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.lob.LobHandler;

import cn.com.yws.toolset.base.common.util.ReflectHelper;


/**
 * <pre>
 * 文件名称: BaseMapper.java
 * 包路径: cn.com.frm.spring.mapper
 * 描述:	使用Spring jdbc功能书写Mapper功能
 * 			只需继承当前类,同时传入与数据库映射的对象
 * 		依赖的jar包:
 * 			spring-jdbc
 * 
 * 内容摘要 
 *    作者: 杨文松
 *    版本: 1.0
 *    时间: 2014年7月9日
 *    邮箱: 272936993@qq.com
 * 修改历史:  
 * 修改日期           修改人员        版本	       修改内容  		说明
 * ---------------------------------------------- 
 * 
 * </pre>
 */
public class BaseMapper<T> implements RowMapper<T>{

	public List<String> binaryStreamReaderList = new ArrayList<String>();
	



	/** 
	* 描述: TODO
	* @param rowSet
	* @param index
	* @return T
	* @throws SQLException    
	*/
	@Override
	public T mapRow(ResultSet rowSet, int index) throws SQLException {
		Object _T = null; 
		try {
			List<Class> classList = ReflectHelper.getGenericsClass(this.getClass());
			Class cls = classList.get(0);
			Field[] fields = cls.getDeclaredFields();
			_T = cls.newInstance();
			for(Field field : fields){
				field.setAccessible(true);
				String fileName = field.getName();
				
				 Object val = rowSet.getObject(fileName);
				 field.set(_T, val);
			}
		}catch (Exception e) {
			e.printStackTrace();
		} 
		
		return (T) _T;
	}

	
	
}
