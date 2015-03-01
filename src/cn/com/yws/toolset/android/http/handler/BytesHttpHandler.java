package cn.com.yws.toolset.android.http.handler;

import java.io.InputStream;

import cn.com.yws.toolset.base.common.util.StreamHelper;
import android.util.Log;


public class BytesHttpHandler implements BaseHttpHandler<byte[]>{

	private final static String TAG = "BytesHttpHandler";
	
	@Override
	public byte[] success(InputStream is) {
		return StreamHelper.readStream2Bytes(is);
	}

	@Override
	public void error(Throwable e) {
		if(e != null)
			e.printStackTrace();
		Log.d(TAG, "----->BytesHttpHandler  error..");
	}
	
	
	
}
