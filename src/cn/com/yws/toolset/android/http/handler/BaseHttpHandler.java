package cn.com.yws.toolset.android.http.handler;

import java.io.InputStream;

public interface BaseHttpHandler<T> {
	
	public T success(InputStream is);
	
	/**
	 * 构思:
	 * 	 该方法是否需要将error抛出错误,否则流程将获取不到问题
	 * */
	public void error(Throwable e);
	
}
