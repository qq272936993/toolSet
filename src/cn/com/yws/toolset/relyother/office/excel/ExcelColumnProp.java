package cn.com.yws.toolset.relyother.office.excel;

/**
 * <pre>
 * 文件名称: ExcelColumnProp.java
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
public class ExcelColumnProp {
	
	private String columnName;
	private String columnCode;
	private String columnType;
	private boolean isNeedMerge;
	
	
	public String getColumnName() {
		return columnName;
	}
	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}
	public String getColumnCode() {
		return columnCode;
	}
	public void setColumnCode(String columnCode) {
		this.columnCode = columnCode;
	}
	public String getColumnType() {
		return columnType;
	}
	public void setColumnType(String columnType) {
		this.columnType = columnType;
	}
	public boolean isNeedMerge() {
		return isNeedMerge;
	}
	public void setNeedMerge(boolean isNeedMerge) {
		this.isNeedMerge = isNeedMerge;
	}
	
	
}
