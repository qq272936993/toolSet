package cn.com.yws.toolset.base.common.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 文件名称: MappingEntity.java
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
@Target({java.lang.annotation.ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface MappingEntity {

	public abstract String table();
	
	public abstract String schema();
	
	public abstract String dataSource();
	
	
}
