package cn.com.yws.toolset.base.common.file;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.ResourceBundle;
import java.util.Set;

public class PropertiesUtils {

	private static PropertiesUtils properties;
	
	//隐藏构造函数
	private PropertiesUtils(){
		
	}
	
	public static synchronized PropertiesUtils getInstance(){
		if(properties == null){
			properties = new PropertiesUtils();
		}
		return properties;
	}
	
	public Map<String,String> loadBunldeAllData(String f){
		ResourceBundle bundle = ResourceBundle.getBundle(f);
		Set<String> set = bundle.keySet();
		Map<String,String> map = new HashMap<String,String>();
		for(String key : set){
			map.put(key, bundle.getString(key));
		}
		return map;
	}
	
	
	public static void main(String[] args) {
		Map<String,String> map = PropertiesUtils.getInstance().loadBunldeAllData("config");
		for(Entry<String, String> m : map.entrySet()){
			System.out.println(m.getKey()+"\t"+m.getValue());
		}
	}
	
}
