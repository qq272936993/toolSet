package cn.com.yws.toolset.android.db.transfer;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <pre>
 * 文件名称: Transaction.java
 * 包路径: cn.com.frm.andriod.db.transfer cn.com.talkweb.sch.controller.build
 * 描述:	 
 * 内容摘要 
 *    作者: 杨文松
 *    版本: 1.0
 *    时间: 2015年1月11日
 *    邮箱: 272936993@qq.com
 * 修改历史:  
 * 修改日期           修改人员        版本	       修改内容  		说明
 * ---------------------------------------------- 
 * 
 * </pre>
 */
@Target({java.lang.annotation.ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Transaction {

}
