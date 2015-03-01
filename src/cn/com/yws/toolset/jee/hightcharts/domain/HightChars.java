package cn.com.yws.toolset.jee.hightcharts.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * 文件名称: HightChars.java
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
public class HightChars {
	
	private List<String> categories;
	private Title title;
	private Title subtitle;
	private List<SerieSub> series = new ArrayList<SerieSub>();
	
	
	public List<String> getCategories() {
		return categories;
	}
	public void setCategories(List<String> categories) {
		this.categories = categories;
	}
	public Title getTitle() {
		return title;
	}
	public void setTitle(Title title) {
		this.title = title;
	}
	public Title getSubtitle() {
		return subtitle;
	}
	public void setSubtitle(Title subtitle) {
		this.subtitle = subtitle;
	}
	public List<SerieSub> getSeries() {
		return series;
	}
	public void setSeries(List<SerieSub> series) {
		this.series = series;
	}
	
}
