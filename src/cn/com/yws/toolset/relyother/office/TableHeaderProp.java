package cn.com.yws.toolset.relyother.office;

import java.util.ArrayList;
import java.util.List;

/**
 * <pre>
 * 文件名称: TableHeaderProp.java
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
public class TableHeaderProp {
	
	private int containRow;
	private boolean bold = false;
	private boolean isItalic = false;
	private boolean isUseTreeCfg = false;
	private String fontName;
	private int totalCol;
	private int headerRows;
	private List<String> colCodeList = new ArrayList<String>();
	private List<TableMergeRegion> hearderList = new ArrayList<TableMergeRegion>();
	private List<List<TableHeaderColumn>> headerColumnList = new ArrayList<List<TableHeaderColumn>>();
	
	 public void addMerginRegion(String paramString, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5)
	  {
	    TableMergeRegion localTableMergeRegion = new TableMergeRegion(paramString, paramInt1, paramInt2, paramInt3, paramInt4, paramInt5);
	    this.hearderList.add(localTableMergeRegion);
	  }

	  public void addSubMergion(TableMergeRegion paramTableMergeRegion1, TableMergeRegion paramTableMergeRegion2)
	  {
	    paramTableMergeRegion1.addSubRegion(paramTableMergeRegion2);
	  }


	public int getContainRow() {
		return containRow;
	}
	public void setContainRow(int containRow) {
		this.containRow = containRow;
	}
	public boolean isBold() {
		return bold;
	}
	public void setBold(boolean bold) {
		this.bold = bold;
	}
	public boolean isItalic() {
		return isItalic;
	}
	public void setItalic(boolean isItalic) {
		this.isItalic = isItalic;
	}
	public boolean isUseTreeCfg() {
		return isUseTreeCfg;
	}
	public void setUseTreeCfg(boolean isUseTreeCfg) {
		this.isUseTreeCfg = isUseTreeCfg;
	}
	public String getFontName() {
		return fontName;
	}
	public void setFontName(String fontName) {
		this.fontName = fontName;
	}
	public int getTotalCol() {
		return totalCol;
	}
	public void setTotalCol(int totalCol) {
		this.totalCol = totalCol;
	}
	public int getHeaderRows() {
		return headerRows;
	}
	public void setHeaderRows(int headerRows) {
		this.headerRows = headerRows;
	}
	public List<String> getColCodeList() {
		return colCodeList;
	}
	public void setColCodeList(List<String> colCodeList) {
		this.colCodeList = colCodeList;
	}
	public List<TableMergeRegion> getHearderList() {
		return hearderList;
	}
	public void setHearderList(List<TableMergeRegion> hearderList) {
		this.hearderList = hearderList;
	}
	public List<List<TableHeaderColumn>> getHeaderColumnList() {
		return headerColumnList;
	}
	public void setHeaderColumnList(List<List<TableHeaderColumn>> headerColumnList) {
		this.headerColumnList = headerColumnList;
	}
	
	
	
}
