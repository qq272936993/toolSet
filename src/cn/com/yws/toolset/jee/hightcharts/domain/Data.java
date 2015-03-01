package cn.com.yws.toolset.jee.hightcharts.domain;

import java.util.HashMap;
import java.util.Map;

/**
 * 文件名称: Data.java
 * 包路径: cn.com.yws.hightchar.vo
 * 描述:
 * 内容摘要 
 *    作者: 杨文松
 *    版本: 1.0
 *    时间: 2013-12-9
 *    邮箱: yangwensong@talkweb.com.cn
 * 修改历史:  
 * 修改日期           修改人员        版本	       修改内容  		说明
 * ---------------------------------------------- 
 * 
 */
public class Data {
	
	private String id;		//id号
	private String name;	//名称,是指定dataLabel的
	private String color;	//显示颜色
	private Float y;		//The y value of the point.
	private Float x;		//The x value of the point.
	private Boolean sliced;	//是否切片
	
	private Map<String,String> promptMsgs = new HashMap<String, String>();	//提示信息

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public Float getY() {
		return y;
	}
	public void setY(Float y) {
		this.y = y;
	}
	public Boolean getSliced() {
		return sliced;
	}
	public void setSliced(Boolean sliced) {
		this.sliced = sliced;
	}
	public Map<String, String> getPromptMsgs() {
		return promptMsgs;
	}
	public void setPromptMsgs(Map<String, String> promptMsgs) {
		this.promptMsgs = promptMsgs;
	}
	public Float getX() {
		return x;
	}
	public void setX(Float x) {
		this.x = x;
	}
	
	
}
