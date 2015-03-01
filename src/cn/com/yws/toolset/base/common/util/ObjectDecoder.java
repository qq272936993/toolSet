package cn.com.yws.toolset.base.common.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;


/**
 * 对象和二进制数据之间的转换
 * */
public class ObjectDecoder {

	
	/**
	 * 将对象转换为二进制数据
	 * @param object
	 * */
	public static byte[] encode(Object object) throws Exception {
		ByteArrayOutputStream byteArray = new ByteArrayOutputStream();
		ObjectOutputStream output = new ObjectOutputStream(byteArray);
		output.writeObject(object);
		output.flush();
		output.close();
		return byteArray.toByteArray(); 
	}
    
	/**
	 * 将二进制数据解析成对象
	 * @param bytes
	 * */
	public static Object decode(byte[] bytes) throws Exception {
		ObjectInputStream objectIn = new ObjectInputStream(new ByteArrayInputStream(bytes));
		Object resultObject = objectIn.readObject();
		objectIn.close();
		return resultObject;
	}
}
