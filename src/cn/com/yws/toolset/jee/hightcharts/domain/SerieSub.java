package cn.com.yws.toolset.jee.hightcharts.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * 文件名称: SerieSub.java
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
public class SerieSub {
	
	private String name;	//该系列作为显示在图例中的名称,工具提示默认为“”
	private String type;	//默认line
	private Integer xAxis;	//它指的是该轴的索引x轴阵列中，其中0是第一个。默认为0。默认为0。
	private Integer yAxis;
	private Boolean showInLegend;	//是否显示图例说明
	private Integer size;	//大小,这个一般指定长宽一样的
	private Integer center[];	//在图片中的坐标
	private List<Data> data = new ArrayList<Data>();
	private DataLabels dataLabels;
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Integer getxAxis() {
		return xAxis;
	}
	public void setxAxis(Integer xAxis) {
		this.xAxis = xAxis;
	}
	public Integer getyAxis() {
		return yAxis;
	}
	public void setyAxis(Integer yAxis) {
		this.yAxis = yAxis;
	}
	public List<Data> getData() {
		return data;
	}
	public void setData(List<Data> data) {
		this.data = data;
	}
	public Boolean getShowInLegend() {
		return showInLegend;
	}
	public void setShowInLegend(Boolean showInLegend) {
		this.showInLegend = showInLegend;
	}
	public Integer getSize() {
		return size;
	}
	public void setSize(Integer size) {
		this.size = size;
	}
	public Integer[] getCenter() {
		return center;
	}
	public void setCenter(Integer[] center) {
		this.center = center;
	}
	public DataLabels getDataLabels() {
		return dataLabels;
	}
	public void setDataLabels(DataLabels dataLabels) {
		this.dataLabels = dataLabels;
	}
	
	
}
