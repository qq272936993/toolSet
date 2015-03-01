package cn.com.yws.toolset.base.common.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * <pre>
 * 文件名称: CollectionUtils.java
 * 包路径: cn.com.yws.toolset.base.common.util cn.com.talkweb.sch.controller.build
 * 描述:	 
 * 内容摘要 
 *    作者: 杨文松
 *    版本: 1.0
 *    时间: 2015年1月14日
 *    邮箱: 272936993@qq.com
 * 修改历史:  
 * 修改日期           修改人员        版本	       修改内容  		说明
 * ----------------------------------------------
 * 
 * </pre>
 */
public class CollectionHelper {
	
	
    /** default separator between key and value **/
    public static final String DEFAULT_KEY_AND_VALUE_SEPARATOR      = ":";
    /** default separator between key-value pairs **/
    public static final String DEFAULT_KEY_AND_VALUE_PAIR_SEPARATOR = ",";
    

	private CollectionHelper() {
		throw new AssertionError();
	}

	public static <V> boolean isEmpty(Collection<V> c) {
		return (c == null || c.size() == 0);
	}

	public static <K, V> boolean isEmpty(Map<K, V> sourceMap) {
        return (sourceMap == null || sourceMap.size() == 0);
    }
	
	public static boolean putMapNotEmptyKey(Map<String, String> map, String key, String value) {
        if (map == null || StringHelper.isEmpty(key)) {
            return false;
        }

        map.put(key, value);
        return true;
    }
	
    public static boolean putMapNotEmptyKeyAndValue(Map<String, String> map, String key, String value) {
        if (map == null || StringHelper.isEmpty(key) || StringHelper.isEmpty(value)) {
            return false;
        }

        map.put(key, value);
        return true;
    }
    
    
    public static boolean putMapNotEmptyKeyAndValue(Map<String, String> map, String key, String value,
            String defaultValue) {
        if (map == null || StringHelper.isEmpty(key)) {
            return false;
        }

        map.put(key, StringHelper.isEmpty(value) ? defaultValue : value);
        return true;
    }
    
    public static <K, V> boolean putMapNotNullKey(Map<K, V> map, K key, V value) {
        if (map == null || key == null) {
            return false;
        }

        map.put(key, value);
        return true;
    }
	
    public static <K, V> boolean putMapNotNullKeyAndValue(Map<K, V> map, K key, V value) {
        if (map == null || key == null || value == null) {
            return false;
        }

        map.put(key, value);
        return true;
    }
    
    
	/**
	 * 将List集合转换成Map结合的数据
	 * @param paramList 需要转换的集合
	 * @param paramString 按哪个字段进行分组
	 * @return {@link Map}
	 * */
	public <T>Map<String, T> convertListToMap(List<T> paramList, String paramString)
			throws Exception {
		HashMap<String,T> localHashMap = new HashMap<String , T>();
		for (int i = 0; i < paramList.size(); i++) {
			T localObject1 = paramList.get(i);
			Field field = localObject1.getClass().getField(paramString);
			field.setAccessible(true);
			Object obj = field.get(localObject1);
			
			String str2 = obj.toString();
			if ((obj instanceof Double))
				str2 = String.valueOf(((Double) obj).intValue());
			if ((obj instanceof Long))
				str2 = String.valueOf(((Long) obj).intValue());
			localHashMap.put(str2, localObject1);
		}
		return localHashMap;
	}
	
    public static <K, V> K getKeyByValue(Map<K, V> map, V value) {
        if (isEmpty(map)) {
            return null;
        }

        for (Entry<K, V> entry : map.entrySet()) {
            if (ObjectHelper.isEquals(entry.getValue(), value)) {
                return entry.getKey();
            }
        }
        return null;
    }
    
    public static Map<String, String> parseKeyAndValueToMap(String source, String keyAndValueSeparator,
            String keyAndValuePairSeparator, boolean ignoreSpace) {
        if (StringHelper.isEmpty(source)) {
            return null;
        }

        if (StringHelper.isEmpty(keyAndValueSeparator)) {
            keyAndValueSeparator = DEFAULT_KEY_AND_VALUE_SEPARATOR;
        }
        if (StringHelper.isEmpty(keyAndValuePairSeparator)) {
            keyAndValuePairSeparator = DEFAULT_KEY_AND_VALUE_PAIR_SEPARATOR;
        }
        Map<String, String> keyAndValueMap = new HashMap<String, String>();
        String[] keyAndValueArray = source.split(keyAndValuePairSeparator);
        if (keyAndValueArray == null) {
            return null;
        }

        int seperator;
        for (String valueEntity : keyAndValueArray) {
            if (!StringHelper.isEmpty(valueEntity)) {
                seperator = valueEntity.indexOf(keyAndValueSeparator);
                if (seperator != -1) {
                    if (ignoreSpace) {
                        putMapNotEmptyKey(keyAndValueMap, valueEntity.substring(0, seperator).trim(),
                                valueEntity.substring(seperator + 1).trim());
                    } else {
                        putMapNotEmptyKey(keyAndValueMap, valueEntity.substring(0, seperator),
                                valueEntity.substring(seperator + 1));
                    }
                }
            }
        }
        return keyAndValueMap;
    }

    
    public static String toSimpleJson(Map<String, String> map) {
        if (map == null || map.size() == 0) {
            return null;
        }

        StringBuilder paras = new StringBuilder();
        paras.append("{");
        Iterator<Map.Entry<String, String>> ite = map.entrySet().iterator();
        while (ite.hasNext()) {
            Map.Entry<String, String> entry = (Map.Entry<String, String>)ite.next();
            paras.append("\"").append(entry.getKey()).append("\":\"").append(entry.getValue()).append("\"");
            if (ite.hasNext()) {
                paras.append(",");
            }
        }
        paras.append("}");
        return paras.toString();
    }
    
	/**
	 * List -> Map 分组数据
	 * 
	 * @param paramList
	 *            集合数据
	 * @param paramString
	 *            键值(属性)
	 * */
	public <T>Map<String, List<T>> convertToMapByParentKey(List<T> paramList,
			String paramString) throws Exception {
		Map localHashMap = new HashMap();
		Iterator<T> localIterator = paramList.iterator();
		while (localIterator.hasNext()) {
			T item = localIterator.next();
			
			Object paramVal = null;
			if (item instanceof Map) {
				paramVal = ((Map) item).get(paramString);
			} else {
				Field field = item.getClass().getField(paramString);
				paramVal = field.get(item);
			}
			if (paramVal == null) {
				if (localHashMap.get("NULL") == null) {
					List col = new ArrayList();
					col.add(item);
					localHashMap.put("NULL", col);
				} else {
					List col = (List) localHashMap.get("NULL");
					col.add(item);
				}
			} else {
				String key = paramVal.toString();
				if(localHashMap.containsKey(key)){
					List col = (List) localHashMap.get(key);
					col.add(item);
				}else{
					List col = new ArrayList();
					col.add(item);
					localHashMap.put(key, col);
				}
			}
		}

		return localHashMap;
	}

	/**
	 * List -> Map
	 * 
	 * @param paramList
	 *            操作的集合
	 * @param paramString1
	 *            属性1,将作为Map的key值
	 * @param paramString2
	 *            属性2,将作为Map的value值
	 * @return Map<String, List<String>>
	 * */
	public <T>Map<String, List<String>> convertToSingleValueMapByParentKey(
			List<T> paramList, String paramString1, String paramString2)
			throws Exception {
		Map<String, List<String>> localHashMap = new HashMap<String, List<String>>();
		Iterator<T> localIterator = paramList.iterator();
		while (localIterator.hasNext()) {
			Object localObject1 = localIterator.next();
			
			Class clazz = localObject1.getClass();
			Field paramField1 = clazz.getField(paramString1);
			paramField1.setAccessible(true);
			Field paramField2 = clazz.getField(paramString2);
			paramField2.setAccessible(true);
			
			Object param1 = paramField1.get(localObject1);
			Object param2 = paramField2.get(localObject1);
			
			String str3 = param1 != null ? param1.toString() : null;
			String str4 = param2 != null ? param2.toString() : null;
			
			if (localHashMap.get(str3) == null) {
				List<String> localObject5 = new ArrayList<String>();
				localObject5.add(str4);
				localHashMap.put(str3, localObject5);
			} else {
				List<String> localObject5 = localHashMap.get(str3);
				localObject5.add(str4);
			}
		}
		return localHashMap;
	}

	/**
	 * 过滤List集合数据,获取paramList集合中paramString1字段,值为paramString2的所有数据
	 * 
	 * @param paramList
	 *            操作集合
	 * @param paramString1
	 *            字段或属性
	 * @param paramString2
	 *            获取的值
	 * @return List<T>
	 * */
	public <T>List<T> FilterListByColumnValue(List<T> paramList,
			String paramString1, String paramString2) throws Exception {
		ArrayList<T> localArrayList = new ArrayList<T>();
		Iterator<T> localIterator = paramList.iterator();
		while (localIterator.hasNext()) {
			T localObject1 = localIterator.next();
			Field field = localObject1.getClass().getField(paramString1);
			Object localObject3= field.get(localObject1);
			
			String str2 = localObject3.toString();
			if (str2.equals(paramString2))
				localArrayList.add(localObject1);
		}
		return localArrayList;
	}

	/**
	 * List转换成字符串 例如: [{id:1, name "1"},{id:2 , name:"2"}] 如果paramString1 --> id
	 * paramString2 --> | 则最后返回的 为 1|2
	 * 
	 * @param paramList
	 *            操作集合
	 * @param paramString1
	 *            操作字段或属性
	 * @param paramString2
	 *            分割字符串
	 * @return String
	 * */
	public <T>String getListColumnValueSumBySeparater(List<T> paramList,
			String paramString1, String paramString2) throws Exception {
		StringBuffer localStringBuffer = new StringBuffer();
		if (paramList != null)
			for (int i = 0; i < paramList.size(); i++) {
				Object localObject1 = paramList.get(i);
				Field field = localObject1.getClass().getField(paramString1);
				field.setAccessible(true);
				Object localObject2 = field.get(localObject1);
				
				String str2 = localObject2.toString();
				localStringBuffer.append(str2);
				if (i != paramList.size() - 1)
					localStringBuffer.append(paramString2);
			}
		return localStringBuffer.toString();
	}

	/**
	 * 获取一个List对象集中对象属性作为一个集合输出 例如: [{id:1, name "1"},{id:2 , name:"2"}]
	 * 如果paramString1 --> id 则最后返回的 为 ["1","2"]
	 * 
	 * @param 操作集合
	 * @param paramString
	 *            字段属性
	 * */
	public <T>List<String> getListColumnValueListBySeparater(List<T> paramList,
			String paramString) throws Exception {
		ArrayList localArrayList = new ArrayList();
		if (paramList != null)
			for (int i = 0; i < paramList.size(); i++) {
				Object localObject1 = paramList.get(i);
				
				Field field = localObject1.getClass().getField(paramString);
				Object localObject2= field.get(localObject1);
				
				String str2 = localObject2.toString();
				localArrayList.add(str2);
			}
		return localArrayList;
	}

	
	/**
	 * 将List<T>转换成List<Map<String,String>>的数据
	 * 	将对象转换成了Map数据
	 * @param paramList
	 * */
	public <T>List<Map<String, Object>> getListMapFromListVO(List<T> paramList)
			throws Exception {
		ArrayList<Map<String,Object>> localArrayList = new ArrayList<Map<String,Object>>();
		Iterator<T> localIterator = paramList.iterator();
		while (localIterator.hasNext()) {
			T localObject1 = localIterator.next();
			
			Field[] arrayOfField  = localObject1.getClass().getDeclaredFields();
			
			HashMap<String,Object> localHashMap = new HashMap<String,Object>();
			for (int i = 0; i < arrayOfField.length; i++)
				try {
					Field field = arrayOfField[i];
					field.setAccessible(true);
					String str1 = field.getName();
					Object obj = localObject1.getClass().getField(str1);
					
					localHashMap.put(arrayOfField[i].getName(),
							obj);
				} catch (Exception localException) {
				}
			localArrayList.add(localHashMap);
		}
		return localArrayList;
	}

	
	/**
	 * 合并集合
	 * @param paramList1 集合一
	 * @param paramList2 集合二
	 * @param paramString
	 * */
	public <T>List<T> mergeListFromNew(List<T> paramList1, List<T> paramList2,
			String paramString) throws Exception {
		Map localMap = convertListToMap(paramList2, paramString);
		ArrayList localArrayList = new ArrayList();
		Iterator localIterator = paramList1.iterator();
		while (localIterator.hasNext()) {
			Object localObject1 = localIterator.next();
			Field field = localObject1.getClass().getField(paramString);
			field.setAccessible(true);
			Object localObject2 = field.get(localObject1);
			
			if (localMap.get(localObject2.toString()) != null)
				localArrayList.add(localMap.get(localObject2.toString()));
			else
				localArrayList.add(localObject1);
		}
		return localArrayList;
	}

	
	/**
	 *  合并集合转换为Map
	 * **/
	public <T>Map<String, T> mergeListMapFromNew(List<T> paramList1,
			List<T> paramList2, String paramString) throws Exception {
		Map localMap = convertListToMap(paramList2, paramString);
		HashMap localHashMap = new HashMap();
		Iterator localIterator = paramList1.iterator();
		while (localIterator.hasNext()) {
			Object localObject1 = localIterator.next();
			Field field = localObject1.getClass().getField(paramString);
			field.setAccessible(true);
			Object localObject2 = field.get(localObject1);
			
			if (localMap.get(localObject2.toString()) != null)
				localHashMap.put(localObject2.toString(),
						localMap.get(localObject2.toString()));
			else
				localHashMap.put(localObject2.toString(), localObject1);
		}
		return localHashMap;
	}

}
