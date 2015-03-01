package cn.com.yws.toolset.android.http.handler;

import java.io.InputStream;

import cn.com.yws.toolset.base.common.util.StreamHelper;
import android.util.Log;


public class StringHttpHandler implements BaseHttpHandler<String> {
	
	private final static String TAG ="StringHttpHandler";
	
	@Override
	public String success(InputStream is) {
		return StreamHelper.readStream2String(is);
	}

	@Override
	public void error(Throwable e) {
		//暂时空着,看日后处理
		Log.d(TAG, "----->StringHttpHandler  error..");
	}
	
}
