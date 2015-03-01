package cn.com.yws.toolset.android.http.handler;

import java.io.InputStream;

import android.util.Log;

/**
 * <pre>
 * 文件名称: InputStreamHandler.java
 * 包路径: cn.com.frm.andriod.http.handler cn.com.talkweb.sch.controller.build
 * 描述:	 
 * 内容摘要 
 *    作者: 杨文松
 *    版本: 1.0
 *    时间: 2015年1月12日
 *    邮箱: 272936993@qq.com
 * 修改历史:  
 * 修改日期           修改人员        版本	       修改内容  		说明
 * ---------------------------------------------- 
 * 
 * </pre>
 */
public class InputStreamHandler implements BaseHttpHandler<InputStream> {

	private final static String TAG = "InputStreamHandler";
	
	@Override
	public InputStream success(InputStream is) {
		return is;
	}

	
	@Override
	public void error(Throwable e) {
		Log.e(TAG, "---->HttpHelper InputStreamHandler error");
	}

}
