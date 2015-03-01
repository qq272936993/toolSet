package cn.com.yws.toolset.base.common.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;


/**
 * <pre>
 * @author 杨文松
 * 时间 : 2015-01-12
 * 描述:
 * 	   流操作工具类
 * </pre>
 * */
public class StreamHelper {
	
	
	/**
	 * 读取流里面的数据,并转换成String
	 * @param is 
	 * */
	public static String readStream2String(InputStream is){
		ByteArrayOutputStream baos = null;
		try {
			baos = readStream2ByteArrayOutputStream(is);

			String ini = baos.toString();
			if(ini.contains("GBk") || ini.contains("gbk"))
				 return baos.toString("GBK");
			else if(ini.contains("ISO8859-1") || ini.contains("iso8859-1")){
				 return baos.toString("ISO8859-1");
			}else{
				return baos.toString();	//在安卓下默认的编码集为UTF-8
			}
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			if(baos != null){
				try {
					baos.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				baos = null;
			}
		}
		
		return null;
	}
	
	
	
	/**
	 * 读取流中的数据,并转换成byte字节数据
	 * @param is
	 * */
	public static byte[] readStream2Bytes(InputStream is){
		ByteArrayOutputStream baos = readStream2ByteArrayOutputStream(is);
		return baos.toByteArray();
	}
	
	
	
	
	public static ByteArrayOutputStream readStream2ByteArrayOutputStream(InputStream is){
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		byte[] buf = new byte[1024];
		try {
			int len = 0;
			while( (len = is.read(buf)) != -1){
				baos.write(buf, 0, len);
			}
			is.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return baos;
	}
	
	
	
}
