package org.mybatis.generator;

import java.util.List;

import org.mybatis.generator.api.CommentGenerator;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.java.Field;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.JavaVisibility;
import org.mybatis.generator.api.dom.java.Method;
import org.mybatis.generator.api.dom.java.Parameter;
import org.mybatis.generator.api.dom.java.TopLevelClass;
import org.mybatis.generator.api.dom.xml.Attribute;
import org.mybatis.generator.api.dom.xml.Document;
import org.mybatis.generator.api.dom.xml.TextElement;
import org.mybatis.generator.api.dom.xml.XmlElement;

import cn.com.yws.toolset.base.common.domain.Page;

/**
 * <pre>
 * 文件名称: MysqlPaginationPalugin.java
 * 包路径: org.mybatis.generator
 * 描述:
 * 内容摘要 
 *    作者: 杨文松
 *    版本: 1.0
 *    时间: 2014年9月25日
 *    邮箱: 272936993@qq.com
 * 修改历史:  
 * 修改日期           修改人员        版本	       修改内容  		说明
 * ---------------------------------------------- 
 * 
 * </pre>
 */
public class MysqlPaginationPalugin extends PluginAdapter{

	 @Override  
	    public boolean modelExampleClassGenerated(TopLevelClass topLevelClass,  
	            IntrospectedTable introspectedTable) {  
	        // add field, getter, setter for limit clause  
	        addPage(topLevelClass, introspectedTable, "page");  
	        addFilterFields(topLevelClass, introspectedTable, "filterFields");
	        return super.modelExampleClassGenerated(topLevelClass,  
	                introspectedTable);  
	    }  
	  
	    @Override  
	    public boolean sqlMapDocumentGenerated(Document document,  
	            IntrospectedTable introspectedTable) {  
	        XmlElement parentElement = document.getRootElement();  

	        XmlElement paginationLimitElement = new XmlElement("sql");
	       paginationLimitElement.addAttribute(new Attribute("id","MysqlDialectLimit"));
	       
	       XmlElement limitPagin = new XmlElement("if");
	       limitPagin.addAttribute(new Attribute("test", "page != null"));
	       limitPagin.addElement(new TextElement("limit #{page.begin},#{page.size}"));
	       paginationLimitElement.addElement(limitPagin);
	       parentElement.addElement(paginationLimitElement);
	       
	       //add baseColumn
	       parentElement.addElement(SQLPubliceMethods.addBaseColumnSql(introspectedTable));
	        
	       return super.sqlMapDocumentGenerated(document, introspectedTable);  
	    }
	    
	    
	   
	  
	    @Override  
	    public boolean sqlMapSelectByExampleWithoutBLOBsElementGenerated(  
	            XmlElement element, IntrospectedTable introspectedTable) {  
	  
	        XmlElement isNotNullElement = new XmlElement("include"); //$NON-NLS-1$     
	        isNotNullElement.addAttribute(new Attribute("refid", "MysqlDialectLimit"));  
	        element.getElements().add(isNotNullElement);  
	  
	        return super.sqlMapUpdateByExampleWithoutBLOBsElementGenerated(element,  
	                introspectedTable);  
	    }  
	  
	    /** 
	     * @param topLevelClass 
	     * @param introspectedTable 
	     * @param name 
	     */  
	    private void addPage(TopLevelClass topLevelClass,  
	            IntrospectedTable introspectedTable, String name) {  
	        topLevelClass.addImportedType(new FullyQualifiedJavaType(  
	                Page.class.getCanonicalName()));  
	        CommentGenerator commentGenerator = context.getCommentGenerator();  
	        Field field = new Field();  
	        field.setVisibility(JavaVisibility.PROTECTED);  
	        field.setType(new FullyQualifiedJavaType( Page.class.getCanonicalName()));  
	        field.setName(name);  
	        commentGenerator.addFieldComment(field, introspectedTable);  
	        topLevelClass.addField(field);  
	        char c = name.charAt(0);  
	        String camel = Character.toUpperCase(c) + name.substring(1);  
	        Method method = new Method();  
	        method.setVisibility(JavaVisibility.PUBLIC);  
	        method.setName("set" + camel);  
	        method.addParameter(new Parameter(new FullyQualifiedJavaType(  
	                Page.class.getCanonicalName()), name));  
	        method.addBodyLine("this." + name + "=" + name + ";");  
	        commentGenerator.addGeneralMethodComment(method, introspectedTable);  
	        topLevelClass.addMethod(method);  
	        method = new Method();  
	        method.setVisibility(JavaVisibility.PUBLIC);  
	        method.setReturnType(new FullyQualifiedJavaType(  
	                Page.class.getCanonicalName()));  
	        method.setName("get" + camel);  
	        method.addBodyLine("return " + name + ";");  
	        commentGenerator.addGeneralMethodComment(method, introspectedTable);  
	        topLevelClass.addMethod(method);  
    }  
	
	    /**
	     * 过滤字段
	     * */
	    public void addFilterFields(TopLevelClass topLevelClass,  
	            IntrospectedTable introspectedTable, String name){
	    	CommentGenerator commentGenerator = context.getCommentGenerator(); 
	    	Field field = new Field();  
	    	field.setVisibility(JavaVisibility.PROTECTED);
	    	field.setType(new FullyQualifiedJavaType("java.lang.String"));  
	    	field.setName(name);  
	    	commentGenerator.addFieldComment(field, introspectedTable);  
	    	commentGenerator.addFieldComment(field, introspectedTable);  
	        topLevelClass.addField(field);  
	        char c = name.charAt(0);  
	        String camel = Character.toUpperCase(c) + name.substring(1);  
	        Method method = new Method();  
	        method.setVisibility(JavaVisibility.PUBLIC);  
	        method.setName("set" + camel);
	        
	        method.addParameter(new Parameter(new FullyQualifiedJavaType(  
	                "java.lang.String"), name));  
	        method.addBodyLine("this." + name + "=" + name + ";");  
	        commentGenerator.addGeneralMethodComment(method, introspectedTable);  
	        topLevelClass.addMethod(method);  
	        method = new Method();  
	        method.setVisibility(JavaVisibility.PUBLIC);  
	        method.setReturnType(new FullyQualifiedJavaType(  
	                "java.lang.String"));  
	        method.setName("get" + camel);  
	        method.addBodyLine("return " + name + ";");  
	        commentGenerator.addGeneralMethodComment(method, introspectedTable);  
	        topLevelClass.addMethod(method);
	    }
	    
	    
	
	/** 
	* 描述: TODO
	* @param warnings
	* @return    
	*/
	@Override
	public boolean validate(List<String> warnings) {
		return true;
	}
	
	
	
}
