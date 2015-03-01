package cn.com.yws.toolset.base.common.file;

import java.util.UUID;

/**
 * <pre>
 * 文件名称: UploadUtils.java
 * 包路径: cn.com.frm.base.comm.file cn.com.talkweb.sch.controller.build
 * 描述:	 
 * 内容摘要 
 *    作者: 杨文松
 *    版本: 1.0
 *    时间: 2015年1月5日
 *    邮箱: 272936993@qq.com
 * 修改历史:  
 * 修改日期           修改人员        版本	       修改内容  		说明
 * ---------------------------------------------- 
 * 
 * </pre>
 */
public class UploadCommHelper {
	
	public static String createUUIDFileNme(String fileName){
		String name = UUID.randomUUID().toString().replaceAll("-", "") ;
		return name + "_" + fileName;
	}
	
	public static String getRandomFileDirs(String fileName, int level){
		if(level < 1)
			throw new RuntimeException("this level don't little than 1");
		
		int hashCode = fileName.hashCode();
		String path = "";
		for(int i=0; i < level ; i++){
			int d = hashCode & 0xf;
			path +="/" + d;
			hashCode = hashCode >> 4;
		}
		
		return path;
	}
	
}
