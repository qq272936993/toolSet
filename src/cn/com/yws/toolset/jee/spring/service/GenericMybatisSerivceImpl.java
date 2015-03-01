package cn.com.yws.toolset.jee.spring.service;

import java.util.List;

import org.mybatis.generator.BaseMybatisObjectMapper;

/**
 * <pre>
 * 文件名称: GenericSerivceImpl.java
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
public abstract class GenericMybatisSerivceImpl<M , E ,I> implements  GenericMybatisService<M , E ,I>{

	public abstract BaseMybatisObjectMapper<M, E, I> getGenericMapper();
	
	
	@Override
	public int countByExample(E example) {
		return this.getGenericMapper().countByExample(example);
	}

	@Override
	public int deleteByExample(E example) {
		return this.getGenericMapper().deleteByExample(example);
	}

	@Override
	public int deleteByPrimaryKey(I id) {
		return this.getGenericMapper().deleteByPrimaryKey(id);
	}

	@Override
	public int insert(M record) {
		return this.getGenericMapper().insert(record);
	}

	@Override
	public int insertSelective(M record) {
		return this.getGenericMapper().insertSelective(record);
	}

	@Override
	public List<M> selectByExample(E example) {
		return this.getGenericMapper().selectByExample(example);
	}

	@Override
	public M selectByPrimaryKey(I id) {
		return this.getGenericMapper().selectByPrimaryKey(id);
	}

	@Override
	public int updateByExampleSelective(M record, E example) {
		return this.getGenericMapper().updateByExampleSelective(record, example);
	}

	@Override
	public int updateByExample(M record, E example) {
		return this.getGenericMapper().updateByExample(record, example);
	}

	@Override
	public int updateByPrimaryKeySelective(M record) {
		return this.getGenericMapper().updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(M record) {
		return this.getGenericMapper().updateByPrimaryKey(record);
	}
	
}
