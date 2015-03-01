package cn.com.yws.toolset.relyother.office;

import java.util.ArrayList;
import java.util.List;

/**
 * <pre>
 * 文件名称: TableMergeRegion.java
 * 包路径: cn.com.frm.base.office
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
public class TableMergeRegion {

	private String name;
	private int length;
	private int colLength;
	private int colHeigth;
	private String foreGroundColor;
	private int startCol;
	private int startRow;
	private List<TableMergeRegion> subRegions = new ArrayList<TableMergeRegion>();

	
	public TableMergeRegion(String name, int length, int colLength,
			int colHeigth, int startCol, int startRow) {
		super();
		this.name = name;
		this.length = length;
		this.colLength = colLength;
		this.colHeigth = colHeigth;
		this.startCol = startCol;
		this.startRow = startRow;
	}

	public TableMergeRegion() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public void addSubRegion(TableMergeRegion paramTableMergegion){
		this.subRegions.add(paramTableMergegion);
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

	public int getColHeigth() {
		return colHeigth;
	}

	public void setColHeigth(int colHeigth) {
		this.colHeigth = colHeigth;
	}

	public String getForeGroundColor() {
		return foreGroundColor;
	}

	public void setForeGroundColor(String foreGroundColor) {
		this.foreGroundColor = foreGroundColor;
	}

	public int getStartCol() {
		return startCol;
	}

	public void setStartCol(int startCol) {
		this.startCol = startCol;
	}

	public int getStartRow() {
		return startRow;
	}

	public void setStartRow(int startRow) {
		this.startRow = startRow;
	}

	public List<TableMergeRegion> getSubRegions() {
		return subRegions;
	}

	public void setSubRegions(List<TableMergeRegion> subRegions) {
		this.subRegions = subRegions;
	}
	
}
