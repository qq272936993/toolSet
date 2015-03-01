package cn.com.yws.toolset.base.common.file;

import java.util.HashMap;
import java.util.Map;



public class PropertiesCache {
	
	private static PropertiesCache propertiesCache;
	private static Map<String,Map<String,String>> values = new HashMap<String, Map<String,String>>();
	
	private PropertiesCache(){
		
	}
	
	public static synchronized PropertiesCache getInstance(){
		if(propertiesCache == null){
			propertiesCache = new PropertiesCache();
		}
		return propertiesCache;
	}
	
	/**
	 * 获取Properties文件的值,当已经加载过该文件后,会直接在内存中获取
	 * @param key	键
	 * @param proName	文件名
	 * */
	public String getValue(String key,String proName){
		if(values.containsKey(proName)){
			Map<String,String> fileValsMap = values.get(proName);
			if(fileValsMap == null || fileValsMap.isEmpty()){
				fileValsMap = PropertiesUtils.getInstance().loadBunldeAllData(proName);
				values.put(proName, fileValsMap);
				return fileValsMap.get(key);
			}else
				return fileValsMap.get(key);
			
		}else{
			Map<String,String> fileValsMap = PropertiesUtils.getInstance().loadBunldeAllData(proName);
			values.put(proName, fileValsMap);
			return fileValsMap.get(key);
		}
	}
	
	
}
