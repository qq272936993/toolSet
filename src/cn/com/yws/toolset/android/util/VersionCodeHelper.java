package cn.com.yws.toolset.android.util;/**
 * Created by Thinkpad on 2015/1/29.
 */

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

/**
 * <pre>
 * 文件名称:
 * 包路径:
 * 描述:  应用版本帮助类
 * 内容摘要
 *    作者: 杨文松
 *    版本: 1.0
 *    时间:
 *    邮箱: 272936993@qq.com
 * 修改历史:
 * 修改日期           修改人员        版本	       修改内容  		说明
 * ----------------------------------------------
 *
 * </pre>
 */
public class VersionCodeHelper {
    private PackageManager pm;
    private PackageInfo packageInfo;
    private int currentVersionCode;
    private String currentVersionName;
    
    
    /**
     * 构造函数
     * @param context 上下文对象
     * */
    public VersionCodeHelper(Context context) {
        pm = context.getPackageManager();
        try {
             packageInfo = pm.getPackageInfo(context.getPackageName(), 0);
             currentVersionCode = packageInfo.versionCode;
             currentVersionName = packageInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            //不可达
        }
    }
    
    
    /***
     * 比较当前版本和服务器中的版本是否一致:
     * @param serverVersionCode 服务器的版本
     * @return true(版本一致)/false(版本不一致)
     * */
    public boolean compareIsNewestVersion(int serverVersionCode){
        if(currentVersionCode != serverVersionCode){
            return false;
        }
        return true;
    }

    
    
    
    
    //===================================
    public PackageInfo getPackageInfo() {
        return packageInfo;
    }

    public void setPackageInfo(PackageInfo packageInfo) {
        this.packageInfo = packageInfo;
    }

    public int getCurrentVersionCode() {
        return currentVersionCode;
    }

    public void setCurrentVersionCode(int currentVersionCode) {
        this.currentVersionCode = currentVersionCode;
    }

    public String getCurrentVersionName() {
        return currentVersionName;
    }

    public void setCurrentVersionName(String currentVersionName) {
        this.currentVersionName = currentVersionName;
    }
}
