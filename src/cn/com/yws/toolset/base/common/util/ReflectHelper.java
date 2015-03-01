package cn.com.yws.toolset.base.common.util;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import cn.com.yws.toolset.base.common.store.Constants;



public class ReflectHelper {
	
	
	
	/**
	 * 将数组集合对象,转换为其他类型的数组对象
	 * String[] s = new Stirng[]{"23","24","25"};
	 * 	eg: stringArraysTransform2Other(s,new Long[s.length]);
	 * @see <p>说明:转化的toArray必须有String参数的构造方法</p>
	 * 
	 * @param arrays 数据集
	 * @param toArray要转换的新数组对象
	 **/
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static <T>T[] stringArraysTransform2Other(String[] arrays,T[] toArray) throws Exception{
		Class c = toArray.getClass();
		Class classType = c.getComponentType();
		Constructor<T> constructor = classType.getConstructor(String.class);
		for(int i=0; i< arrays.length ; i++){
			toArray[i] = constructor.newInstance(arrays[i]);
		}
		return toArray;
	}
	
	/**
	 * 将String类型的数据装换成其他类型的数据
	 * 	注意:前提条件是该类型提供String类型的构造方法.
	 *  例如:String ="123",转换成整型 需要有new Integer(String ..);
	 * 	@param String
	 *  @param Class
	 * @throws NoSuchMethodException 
	 * @throws SecurityException 
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws IllegalArgumentException 
	 * */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static <T>T String2Other(String val, T toClassObj) throws SecurityException, NoSuchMethodException, IllegalArgumentException, InstantiationException, IllegalAccessException, InvocationTargetException{
		Class c = toClassObj.getClass();
		Constructor<T> constructor = c.getConstructor(String.class);
		return constructor.newInstance(val);
	}
	
	
	/**
	 * 内容合并:
	 * 		将数据集中的内容合成一个数据
	 * @param contentList 内容集
	 * */
	public static String Objects2String(List<?> contentList,String splitStr){
		StringBuffer buffer = new StringBuffer();
		for(int i=0 ;i< contentList.size() ;i++){
			buffer.append(contentList.get(i).toString());
			if(i != contentList.size() -1)buffer.append(splitStr);
		}
		return buffer.toString();
	}
	
	

	
	/**
	 * 获取泛型中的类型
	 * 	 例如: List<String>   获取出的类型为 [String.class]
	 * 说明:
	 * 		需要获取泛型的,必须通过继承等方式重新构建一个新类,传递泛型的类型
	 * 		
	 * 可查考
	 * @throws ClassNotFoundException 
	 * 		@see BaseMapper
	 * */
	@SuppressWarnings("rawtypes")
	public static List<Class> getGenericsClass(Class thisClass) throws ClassNotFoundException{
		//返回表示此 Class 所表示的实体（类、接口、基本类型或 void）的直接超类的 Type。
		Type genType = thisClass.getGenericSuperclass();
		List<Class> list= new ArrayList<Class>();
        if (!(genType instanceof ParameterizedType)) { 
           list.add(Object.class);
           return list;  
        }  
        
        //返回表示此类型实际类型参数的Type对象的数组
        Type[] params = ((ParameterizedType) genType).getActualTypeArguments();  
        for(Type type : params){
        	list.add(
        			type2Class(type)	
        	);
        }
        return list;
	}

	
	/**
	 * Type类型转换为Class类型
	 * */
	public static Class<?> type2Class(Type type) throws ClassNotFoundException{
		String clsStr = type.toString();
		String classStrPath = clsStr.substring(clsStr.indexOf(" ")+1 , clsStr.length());
		if(int.class.getName().equals(classStrPath))
			return int.class;
		else if(Constants.BYTE_NAME.equals(classStrPath))
			return byte.class;
		else if(Constants.SHORT_NAME.equals(classStrPath))
			return short.class;
		else if(Constants.LONG_NAME.equals(classStrPath))
			return long.class;
		else if(Constants.FLOAT_NAME.equals(classStrPath))
			return float.class;
		else if(Constants.DOUBLE_NAME.endsWith(classStrPath))
			return double.class;
		else if(Constants.CHAR_NAME.equals(classStrPath))
			return char.class;
		else if(Constants.BOOLEAN_NAME.equals(classStrPath))
			return boolean.class;
			
		return Class.forName(classStrPath);
	}
	
	
	
	
}
