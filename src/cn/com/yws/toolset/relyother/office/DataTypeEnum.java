package cn.com.yws.toolset.relyother.office;

import java.io.Serializable;

/**
 * <pre>
 * 文件名称: DataTypeEnum.java
 * 包路径: cn.com.frm.base.office
 * 描述:
 * 内容摘要 
 *    作者: 杨文松
 *    版本: 1.0
 *    时间: 2014年9月11日
 *    邮箱: 272936993@qq.com
 * 修改历史:  
 * 修改日期           修改人员        版本	       修改内容  		说明
 * ---------------------------------------------- 
 * 
 * </pre>
 */
public class DataTypeEnum implements Serializable{

	private String dataType;
	private String name;
	private String cnName;
	private String indicatorId;
	private String codeSetId;
	private String isPrimary;
	private String isNull;
	private String dataPercise;
	private String dataLength;
	
	
	public String getDataType() {
		return dataType;
	}
	public void setDataType(String dataType) {
		this.dataType = dataType;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCnName() {
		return cnName;
	}
	public void setCnName(String cnName) {
		this.cnName = cnName;
	}
	public String getIndicatorId() {
		return indicatorId;
	}
	public void setIndicatorId(String indicatorId) {
		this.indicatorId = indicatorId;
	}
	public String getCodeSetId() {
		return codeSetId;
	}
	public void setCodeSetId(String codeSetId) {
		this.codeSetId = codeSetId;
	}
	public String getIsPrimary() {
		return isPrimary;
	}
	public void setIsPrimary(String isPrimary) {
		this.isPrimary = isPrimary;
	}
	public String getIsNull() {
		return isNull;
	}
	public void setIsNull(String isNull) {
		this.isNull = isNull;
	}
	public String getDataPercise() {
		return dataPercise;
	}
	public void setDataPercise(String dataPercise) {
		this.dataPercise = dataPercise;
	}
	public String getDataLength() {
		return dataLength;
	}
	public void setDataLength(String dataLength) {
		this.dataLength = dataLength;
	}
	
	
	
	
	
	
}
