package cn.com.yws.toolset.jee.hightcharts.domain;

/**
 * 文件名称: DataLabels.java
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
public class DataLabels{
	
	private Boolean enabled;
	private Integer distance;
	private String align;
	private String color;
	private String overflow;
	private String style;
	
	public Boolean getEnabled() {
		return enabled;
	}
	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}
	public Integer getDistance() {
		return distance;
	}
	public void setDistance(Integer distance) {
		this.distance = distance;
	}
	public String getAlign() {
		return align;
	}
	public void setAlign(String align) {
		this.align = align;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public String getOverflow() {
		return overflow;
	}
	public void setOverflow(String overflow) {
		this.overflow = overflow;
	}
	public String getStyle() {
		return style;
	}
	public void setStyle(String style) {
		this.style = style;
	}
	
}
