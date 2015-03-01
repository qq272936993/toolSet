package cn.com.yws.toolset.android.http;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;
import java.util.Map.Entry;

import cn.com.yws.toolset.android.http.domain.HttpRequestParam;
import cn.com.yws.toolset.android.http.handler.BaseHttpHandler;
import cn.com.yws.toolset.android.http.handler.BytesHttpHandler;
import android.content.Context;
import android.os.Environment;


/***
 *	Http请求帮助类:
 *		思路:
 * 		1.传参的方式   ------ 在url后面拼接
 * 		2.设置连接等待时间	---- 默认为5000
 * 		3.请求头参数		---- 
 * 		4.回调函数(数据回调和文件格式回调)		----
 * 		5.解析的编码
 * */ 
public class SimpleHttpHelper {

	
	
	private final static int FIRST_PARAM_INDEX = 0;
	public final static String GET_METHOD ="GET";
	public final static String POST_METHOD = "POST";
	
	
	public static <T>T Get(HttpRequestParam param,BaseHttpHandler<T> handler) throws IOException{
		Map<String,Object> attrs = param.getAttrs();
		URL url = new URL(param.getUrl()+  ((attrs != null && attrs.size() >0) ? "?"+buildUrlParams(param) : "" ));
		//获取一个Http连接对象
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		//设置超时时间
		if(param.getRequestTimeOut() != null) conn.setConnectTimeout(param.getRequestTimeOut());
		conn.setConnectTimeout(param.getConnTimeOut());
		//设置请求头信息
		setHeaderProperties(param, conn);
		//设置请求方式
		conn.setRequestMethod(GET_METHOD);
		//获取请求码
		int code = conn.getResponseCode();
		//如果成功
		if(code == 200)
			return handler.success(conn.getInputStream());
		else{
			handler.error(null);
		}
		return null;
	}
	
	public static <T>T Post(HttpRequestParam param,BaseHttpHandler<T> handler){
		String urlStr = param.getUrl();
		if(urlStr == null || "".equals(urlStr))
			throw new RuntimeException("请求的资源路径不能为空");
		HttpURLConnection conn = null;
		OutputStream os = null;
		try {
			URL url = new URL(urlStr);
			//获取一个Http连接对象
			conn = (HttpURLConnection) url.openConnection();
			//设置请求方式
			conn.setRequestMethod(POST_METHOD);
			//设置超时时间
			if(param.getRequestTimeOut() != null) conn.setConnectTimeout(param.getRequestTimeOut());
			conn.setConnectTimeout(param.getConnTimeOut());
			//设置post提交的form表单头信息
			String paramsStr = buildUrlParams(param);
			conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			conn.setRequestProperty("Content-Length", paramsStr.length()+"");
			
			//Post提交的方式,实际上是浏览器写给服务器端的
			conn.setDoOutput(true);
			os = conn.getOutputStream();
			os.write(paramsStr.getBytes());
			
			//获取请求码
			int code = conn.getResponseCode();
			//如果成功
			if(code == 200)
				return handler.success(conn.getInputStream());
			else{
				handler.error(null);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(os != null){
				try {
					os.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				os = null;
			}
			
			if(conn != null){
				conn.disconnect();
				conn = null;
			}
		}
		return null;
	}
	
	
	/**
	 * 下载各种二进制文件,并缓存保存文件,由于是各种文件,
	 * 		所有返回二进制数据
	 * 如果sdcard
	 * @param param 
	 * @throws Exception 
	 * */
	public static byte[] downloadCacheFile(HttpRequestParam param) throws Exception{
		String urlStr = param.getUrl()+"?"+buildUrlParams(param);
		String filePath = urlStr.replaceAll("/", "");
		//需要判断缓存并优先获取缓存数据
		if(param.getIsCache()){
			Context context = param.getContext();
			if(context != null){
				//判断sdcard存储卡是否是插入状态
				if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
					File cacheDir = context.getCacheDir();
					File downFile = new File(cacheDir , filePath);
					//文件已经存在,获取缓存文件
					if(downFile.exists()){
						//如果文件存在
						FileInputStream fis = new FileInputStream(downFile);
						ByteArrayOutputStream baos = new ByteArrayOutputStream();
						byte[] buf = new byte[1024];
						int len = -1;
						while((len = fis.read(buf)) != -1){
							baos.write(buf, 0, len);
						}
						fis.close();
						
						return baos.toByteArray();
					}else{
						//找不到缓存文件,下载网络文件
						byte[] bytes = Get(param, new BytesHttpHandler());
						//将文件写入到sdCard缓存文件中保存
						FileOutputStream fos = new FileOutputStream(downFile ,true);
						fos.write(bytes);
						fos.close();
						
						return bytes;
					}
				}else{
					//没有插入sdcard则直接获取网络上的图片,不进行存储
					return Get(param, new BytesHttpHandler());
				}
			}else
				throw new RuntimeException("缓存文件需要获取上下文Contenxt信息...!");
		}else{
			//不需要判断缓存,直接获取
			return Get(param, new BytesHttpHandler());
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	/**
	 * 构建GET请求的 url请求参数
	 * @param param 所有请求参数
	 * */
	private static String buildUrlParams(HttpRequestParam param){
		Map<String,Object> attrs = param.getAttrs();
		String url = "";
		if(attrs != null){
			int index = 0;
			for(Entry<String, Object> entry : attrs.entrySet()){
				url += index == FIRST_PARAM_INDEX ? entry.getKey()+"="+entry.getValue() :
							"&"+entry.getKey()+"="+entry.getValue();
				index++;
			}
		}
		
		return url;
	}
	
	/**
	 * 用于设置请求头信息(GET 和 HEAD 的方式)
	 * @param param 请求参数
	 * @param conn http连接
	 * */
	private static void setHeaderProperties(HttpRequestParam param, HttpURLConnection conn){
		Map<String,Object> headers = param.getHeaders();
		if(headers != null && conn != null){
			for(Entry<String, Object> header : headers.entrySet()){
				conn.setRequestProperty(header.getKey(), header.getValue().toString());
			}
		}
	}
	
	
}
