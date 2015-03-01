package cn.com.yws.toolset.android.util;

import java.util.List;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningServiceInfo;
import android.content.Context;



/**
 * 安卓Service服务工具类
 * */
public class ServiceManagerHelper {
	
	/**
	 * 是否存在需要检查的Service正在运行
	 * @param context 上下文
	 * @param serviceName 服务名称
	 * @return true(存在) / false(不存在)
	 * */
	public static boolean hasExistServiceRunning(Context context , String serviceName){
		ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
		 List<RunningServiceInfo> serviceInfos  = activityManager.getRunningServices(100);
		 for(RunningServiceInfo info : serviceInfos){
			 if(info.service.getClassName().equals(serviceName)){
				 return true;
			 }
		 }
		 return false;
	}
	
}
