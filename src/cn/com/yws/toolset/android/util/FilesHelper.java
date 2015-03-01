/**
 * 
 */
package cn.com.yws.toolset.android.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import android.content.Context;
import android.net.Uri;
import android.os.Environment;
import android.util.Log;

/**
 * <pre>
 * 文件名称: SavingFilesHelper.java
 * 包路径: cn.com.yws.comm cn.com.talkweb.sch.controller.build
 * 描述:	 
 * 		Choose Internal or External Storage	选择内部存储或者外部存储
 * Internal Storage 内部存储
 * <ul>	
 * 		<li>It's always available.</li>
 * 		<li>Files saved here are accessible by only your app by default.</li>
 * 		<li>
 * 			When the user uninstalls your app, the system removes all your app's files from internal storage.
 * 			当你卸载app应用程序的时候,系统会移除你所有的app文件
 * 		</li>
 * </ul>
 * External Storage 外部存储
 * <ul>	
 * 		<li>It's not always available, because the user can mount the external storage as USB storage and in some cases remove it from the device</li>
 * 		<li>
 * 			It's world-readable, so files saved here may be read outside of your control.
 * 		</li>
 * 		<li>
 * 			When the user uninstalls your app, the system removes your app's files from here only if you save them in the directory from getExternalFilesDir().
 * 			当你卸载你的app应用的时候,系统移除你的app文件仅仅会从删除getExternalFilesDir()下的文件
 * 		</li>
 * </ul>
 * 
 * Obtain Permissions for External Storage 外部存储需要开启的权限配置:
 * 		 <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />
 * 		 <uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE" />
 * 
 * 内容摘要 
 *    作者: 杨文松
 *    版本: 1.0
 *    时间: 2014年12月23日
 *    邮箱: 272936993@qq.com
 * 修改历史:  
 * 修改日期           修改人员        版本	       修改内容  		说明
 * ---------------------------------------------- 
 * 
 * </pre>
 */
public class FilesHelper {
	
	private final static String TAG ="SavingFilesHelper";
	
	
	/**
	 * 保存文件
	 *  openFileOutput: 保存为内部存储文件
	 *  @param context 上下文
	 *  @param fileName 文件名称
	 *  @param contents 文件内容
	 * */
	public static void saveFile(Context context , String fileName,byte[] contents){
		FileOutputStream outputStream = null;
	
		try {
			outputStream = context.openFileOutput(fileName, Context.MODE_PRIVATE);
			outputStream.write(contents);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Log.e("TAG", "--->文件没有找到");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Log.e(TAG, "--->IO流操作文件失败.");
		}finally{
			if(outputStream != null){
				try {
					outputStream.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				outputStream = null;
			}
		}
		
	}
	
	/**
	 * 获取缓存文件
	 * @param context 上下文
	 * @param url 路径
	 * */
	public static File getTempFile(Context context, String url) {
	    File file = null;
	    try {
	        String fileName = Uri.parse(url).getLastPathSegment();
	        file = File.createTempFile(fileName, null, context.getCacheDir());
	    }catch (IOException e) {
	        // Error while creating file
	    }
	    return file;
	}
		
	
	
	
	//============================================================================================================================
	//外部存储模块
	
	/**
	 * 校验是否是可写外部存储
	 * */
	public static boolean isExternalStorageWritable() {
	    String state = Environment.getExternalStorageState();
	    if (Environment.MEDIA_MOUNTED.equals(state)) {
	        return true;
	    }
	    return false;
	}
	
	/**
	 * 校验是否是可读外部存储
	 * */
	public static boolean isExternalStorageReadable() {
	    String state = Environment.getExternalStorageState();
	    if (Environment.MEDIA_MOUNTED.equals(state) ||
	        Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
	        return true;
	    }
	    return false;
	}
	
	/**
	 * 获取外部存储的文件
	 *    文件路径/mnt/sdcard/?type?/fileName
	 * @param fileName 文件名称
	 * @param dir_type 文件类型 Environment 的常量
	 * @return {@link File}
	 * */
	public static File getStorageDir(String fileName , String dir_type) {
	   
	    File file = new File(Environment.getExternalStoragePublicDirectory(
	    		dir_type), fileName);
	    if (!file.mkdirs()) {
	        Log.e(TAG, "--->文件创建是失败!");
	    }
	    return file;
	}
	
	/**
	 * 获取文件路径   /mnt/sdcard/Android/package/files/?type?/文件名
	 * 	@param context
	 *  @param dir_type 文件类型
	 *  @param 文件名
	 *  @return {@link File}
	 * */
	public static File getStorageDir(Context context, String dir_type, String albumName) {
	    File file = new File(context.getExternalFilesDir(
	    		dir_type), albumName);
	    System.out.println("---->"+ file.getPath());
	    if (!file.mkdirs()) {
	    	 Log.e(TAG, "--->文件创建失败!");
	    }
	    return file;
	}
	
	
	
	
	
	
	
}
