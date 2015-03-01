package cn.com.yws.toolset.relyother.office.excel;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * <pre>
 * 文件名称: ExcelSheetProp.java
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
public class ExcelSheetProp {
	
	@Deprecated
	private String[] headerName;
	@Deprecated
	private String[] columnName;

	private List<Map<String,String>> columnList;
	private List<ExcelColumnProp> columnPropList = new ArrayList<ExcelColumnProp>();
	private String fileext = ExcelBaseOper.TYPE_EXCEL2003;
	
	@Deprecated
	private String[] columnType;
	private String sheetName;
	private int startRow = 2;
	private int startCol = 1;
	private Integer tableId;
	
	

	public String[] getColumnName() {
		return columnName;
	}

	public void setColumnName(String[] columnName) {
		this.columnName = columnName;
	}
	public String getFileext() {
		return fileext;
	}

	public void setFileext(String fileext) {
		this.fileext = fileext;
	}

	public List<Map<String, String>> getColumnList() {
		return columnList;
	}

	public String[] getHeaderName() {
		return headerName;
	}

	public void setHeaderName(String[] headerName) {
		this.headerName = headerName;
	}

	public String[] getColumnType() {
		return columnType;
	}

	public void setColumnType(String[] columnType) {
		this.columnType = columnType;
	}

	public void setColumnList(List<Map<String, String>> columnList) {
		this.columnList = columnList;
	}

	public List<ExcelColumnProp> getColumnPropList() {
		return columnPropList;
	}

	public void setColumnPropList(List<ExcelColumnProp> columnPropList) {
		this.columnPropList = columnPropList;
	}

	public String getSheetName() {
		return sheetName;
	}

	public void setSheetName(String sheetName) {
		this.sheetName = sheetName;
	}

	public int getStartRow() {
		return startRow;
	}

	public void setStartRow(int startRow) {
		this.startRow = startRow;
	}

	public int getStartCol() {
		return startCol;
	}

	public void setStartCol(int startCol) {
		this.startCol = startCol;
	}

	public Integer getTableId() {
		return tableId;
	}

	public void setTableId(Integer tableId) {
		this.tableId = tableId;
	}
	
	
	
}
