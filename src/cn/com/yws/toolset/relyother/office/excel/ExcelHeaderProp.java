package cn.com.yws.toolset.relyother.office.excel;

import java.util.ArrayList;
import java.util.List;

/**
 * <pre>
 * 文件名称: ExcelHeaderProp.java
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
public class ExcelHeaderProp{
	
	private int containRow;
	private boolean bold = false;
	private boolean isItalic = false;
	private boolean isUseTreeCfg = false;
	private String fontName;
	private int totalCol;
	private int headerRows;
	private List<String> colCodeList = new ArrayList<String>();
	private List<ExcelMergeRegion> hearderList = new ArrayList<ExcelMergeRegion>();
	private List<List<ExcelHeaderColumn>> headerColumnList = new ArrayList<List<ExcelHeaderColumn>>();
	

	  public ExcelMergeRegion addMerginRegion(String paramString, int paramInt1, int paramInt2, int paramInt3)
	  {
	    ExcelMergeRegion localExcelMergeRegion = new ExcelMergeRegion(paramString, paramInt1, paramInt2, paramInt3);
	    return localExcelMergeRegion;
	  }

	  public void addSubMergion(ExcelMergeRegion paramExcelMergeRegion1, ExcelMergeRegion paramExcelMergeRegion2)
	  {
	    paramExcelMergeRegion1.addSubRegion(paramExcelMergeRegion2);
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

	public List<ExcelMergeRegion> getHearderList() {
		return hearderList;
	}

	public void setHearderList(List<ExcelMergeRegion> hearderList) {
		this.hearderList = hearderList;
	}

	public List<List<ExcelHeaderColumn>> getHeaderColumnList() {
		return headerColumnList;
	}

	public void setHeaderColumnList(List<List<ExcelHeaderColumn>> headerColumnList) {
		this.headerColumnList = headerColumnList;
	}
	
	
	
	
}
