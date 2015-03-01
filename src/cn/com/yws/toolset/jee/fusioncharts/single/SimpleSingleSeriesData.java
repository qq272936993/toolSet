package cn.com.yws.toolset.jee.fusioncharts.single;

import java.util.List;

import cn.com.yws.toolset.jee.fusioncharts.BaseData;
import cn.com.yws.toolset.jee.fusioncharts.Chart;
import cn.com.yws.toolset.jee.fusioncharts.LinkedData;
import cn.com.yws.toolset.jee.fusioncharts.RowData;


public class SimpleSingleSeriesData extends BaseData{

	private Chart chart;
	private List<RowData> data;
	private List<LinkedData> linkeddata;
	
	public Chart getChart() {
		return chart;
	}
	public void setChart(Chart chart) {
		this.chart = chart;
	}
	public List<RowData> getData() {
		return data;
	}
	public void setData(List<RowData> data) {
		this.data = data;
	}
	public List<LinkedData> getLinkeddata() {
		return linkeddata;
	}
	public void setLinkeddata(List<LinkedData> linkeddata) {
		this.linkeddata = linkeddata;
	}
	
}
