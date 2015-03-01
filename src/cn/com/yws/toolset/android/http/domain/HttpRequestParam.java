package cn.com.yws.toolset.android.http.domain;

import java.util.Map;

import android.content.Context;

/**
 * <ul>
 * 	 <li>@author 杨文松</li>
 * 	 <li>时间:2015-01-12</li>
 *   <li>描述:Http请求的参数</li>
 * </ul>
 * */
public class HttpRequestParam {

	/**
	 * 请求的Url
	 * */
	private String url;
	
	/**
	 * 等待超时的时间
	 * */
	private int connTimeOut = 5000;
	
	/**
	 * 请求等待时间
	 * */
	private Integer requestTimeOut;
	
	/**
	 * 编码方式
	 * */
	private String encoding = "UTF-8";
	
	/**
	 * 请求带的参数 Key-Value
	 * */
	private Map<String,Object> attrs;
	
	/**
	 * 请求的Header设置
	 * */
	private Map<String,Object> headers;
	/**
	 * 是否将数据写入到缓存文件夹中
	 * */
	private Boolean isCache;
	
	/**
	 * 上下文环境,该属性与isCache使用,当要使用到缓存时,
	 * 		将数据保存至sdcard或内存文件夹中.
	 * */
	private Context context;
	
	
	
	
	
	
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}

	public String getEncoding() {
		return encoding;
	}

	public void setEncoding(String encoding) {
		this.encoding = encoding;
	}
	public Map<String, Object> getHeaders() {
		return headers;
	}

	public void setHeaders(Map<String, Object> headers) {
		this.headers = headers;
	}
	public Map<String, Object> getAttrs() {
		return attrs;
	}
	public void setAttrs(Map<String, Object> attrs) {
		this.attrs = attrs;
	}
	public int getConnTimeOut() {
		return connTimeOut;
	}
	public void setConnTimeOut(int connTimeOut) {
		this.connTimeOut = connTimeOut;
	}
	public Integer getRequestTimeOut() {
		return requestTimeOut;
	}
	public void setRequestTimeOut(Integer requestTimeOut) {
		this.requestTimeOut = requestTimeOut;
	}
	public Boolean getIsCache() {
		return isCache;
	}
	public void setIsCache(Boolean isCache) {
		this.isCache = isCache;
	}
	public Context getContext() {
		return context;
	}
	public void setContext(Context context) {
		this.context = context;
	}
	
	
}
