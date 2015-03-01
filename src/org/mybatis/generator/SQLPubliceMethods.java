package org.mybatis.generator;

import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.dom.xml.Attribute;
import org.mybatis.generator.api.dom.xml.TextElement;
import org.mybatis.generator.api.dom.xml.XmlElement;

/**
 * <pre>
 * 文件名称: SQLPubliceMethods.java
 * 包路径: org.mybatis.generator
 * 描述: mysql\Oracle\DB2等数据库的公共方法类
 * 内容摘要 
 *    作者: 杨文松
 *    版本: 1.0
 *    时间: 2014年10月22日
 *    邮箱: 272936993@qq.com
 * 修改历史:  
 * 修改日期           修改人员        版本	       修改内容  		说明
 * ---------------------------------------------- 
 * 
 * </pre>
 */
public class SQLPubliceMethods {
	
	 /**
     * 新添加的方法
     * */
    public static XmlElement addBaseColumnSql(IntrospectedTable introspectedTable){
    	XmlElement baseColumnElement = new XmlElement("sql");
    	baseColumnElement.addAttribute(new Attribute("id", "judgeShowColums"));
    	
    	XmlElement yifAnswerFilter = new XmlElement("if");
		Attribute yTestAttr = new Attribute("test", "filterFields != null");
		yifAnswerFilter.addAttribute(yTestAttr);
		yifAnswerFilter.addElement(new TextElement(" ${filterFields} ") );
		baseColumnElement.addElement(yifAnswerFilter);
		
		XmlElement answer = new XmlElement("include"); //$NON-NLS-1$
		answer.addAttribute(new Attribute("refid", //$NON-NLS-1$
				introspectedTable.getBaseColumnListId()));
		
		XmlElement nifAnswerFilter = new XmlElement("if");
		Attribute nTestAttr = new Attribute("test", "filterFields == null");
		nifAnswerFilter.addAttribute(nTestAttr);
		nifAnswerFilter.addElement(answer);
		baseColumnElement.addElement(nifAnswerFilter);
		
		return  baseColumnElement;
    }
	
}
