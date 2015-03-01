package cn.com.yws.toolset.relyother.office.excel;

import java.util.ArrayList;
import java.util.List;

/**
 * <pre>
 * 文件名称: ExcelMergeRegion.java
 * 包路径: cn.com.frm.base.office.excel
 * 描述:
 * 内容摘要 
 *    作者: 杨文松
 *    版本: 1.0
 *    时间: 2014年9月10日
 *    邮箱: 272936993@qq.com
 * 修改历史:  
 * 修改日期           修改人员        版本	       修改内容  		说明
 * ---------------------------------------------- 
 * 
 * </pre>
 */
public class ExcelMergeRegion {

	private String name;
	private int length;
	private int colLength;
	private int colHeight;
	private String foreGroundColor;
	
	private List<ExcelMergeRegion> subRegions = new ArrayList<ExcelMergeRegion>();

	public ExcelMergeRegion()
	{
		
	}
	
	public ExcelMergeRegion(String name, int length,int colLength , int colHeight){
		this.name = name;
		this.length = length;
		this.colLength = colLength;
		this.colHeight = colHeight;
	}
	
	public void addSubRegion(ExcelMergeRegion excelMergeRegion){
		this.subRegions.add(excelMergeRegion);
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public int getColLength() {
		return colLength;
	}

	public void setColLength(int colLength) {
		this.colLength = colLength;
	}

	public int getColHeight() {
		return colHeight;
	}

	public void setColHeight(int colHeight) {
		this.colHeight = colHeight;
	}

	public String getForeGroundColor() {
		return foreGroundColor;
	}

	public void setForeGroundColor(String foreGroundColor) {
		this.foreGroundColor = foreGroundColor;
	}

	public List<ExcelMergeRegion> getSubRegions() {
		return subRegions;
	}

	public void setSubRegions(List<ExcelMergeRegion> subRegions) {
		this.subRegions = subRegions;
	}


	
	
}
