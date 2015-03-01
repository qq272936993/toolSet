package cn.com.yws.toolset.base.common.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 文件名称: MappingField.java
 * 包路径: cn.com.frm.base.annotation
 * 描述:
 * 内容摘要 
 *    作者: 杨文松
 *    版本: 1.0
 *    时间: 2014年1月2日
 *    邮箱: yangwensong@talkweb.com.cn
 * 修改历史:  
 * 修改日期           修改人员        版本	       修改内容  		说明
 * ---------------------------------------------- 
 * 
 */
@Target({java.lang.annotation.ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface MappingField {
	  public abstract String property();

	  public abstract String field();

	  public abstract String primary();

	  public abstract String increment();

	  public abstract String sequenceName();

	  public abstract boolean required();

	  public abstract String datatype();
	  
}
