package cn.com.yws.toolset.android.domain;

import java.util.HashMap;

public class ViewHolderMap{
	
	private HashMap<String,Object> result = new HashMap<String,Object>();
	
	
	public void put(String key , Object view){
		result.put(key, view);
	}

	
	@SuppressWarnings("unchecked")
	public <T>T get(String key){
		return (T) result.get(key);
	}
	
}
