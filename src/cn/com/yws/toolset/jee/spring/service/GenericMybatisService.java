package cn.com.yws.toolset.jee.spring.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

/**
 * <pre>
 * 文件名称: GenericService.java
 * 包路径: cn.com.frm.base.service
 * 描述:
 * 内容摘要 
 *    作者: 杨文松
 *    版本: 1.0
 *    时间: 2014年9月26日
 *    邮箱: 272936993@qq.com
 * 修改历史:  
 * 修改日期           修改人员        版本	       修改内容  		说明
 * ---------------------------------------------- 
 * 
 * </pre>
 */
public abstract interface GenericMybatisService<M , E ,I> {

	  /**
     * 根据条件查询记录总数
     */
    int countByExample(E example);

    /**
     * 根据条件删除记录
     */
    int deleteByExample(E example);

    /**
     * 根据主键删除记录
     */
    int deleteByPrimaryKey(I id);

    /**
     * 保存记录,不管记录里面的属性是否为空
     */
    int insert(M record);

    /**
     * 保存属性不为空的记录
     */
    int insertSelective(M record);

    /**
     * 根据条件查询记录集
     */
    List<M> selectByExample(E example);

    /**
     * 根据主键查询记录
     */
    M selectByPrimaryKey(I id);

    /**
     * 根据条件更新属性不为空的记录
     */
    int updateByExampleSelective(@Param("record") M record, @Param("example") E example);

    /**
     * 根据条件更新记录
     */
    int updateByExample(@Param("record") M record, @Param("example") E example);

    /**
     * 根据主键更新属性不为空的记录
     */
    int updateByPrimaryKeySelective(M record);

    /**
     * 根据主键更新记录
     */
    int updateByPrimaryKey(M record);
	
}
