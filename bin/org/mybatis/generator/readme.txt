作者:杨文松
书写时间:2014-12-10
说明文档:
	在书写mybatis中的Service类时,如果要用当前jar包已经写好的baseService类,则需要做如下操作:
		1.在service接口中继承GenericService,同时添加上泛型<Model,Example,ID类型>
			eg:
				public interface ImageLibService extends GenericService<ImagesLib, ImagesLibExample, Long>{

				}
			
		2.在service的实现类中继承GenericSerivceImpl,同时添加上泛型<Model,Example,ID类型>,同时实现其自身的接口
			eg:
				public class ImageLibServiceImpl extends GenericSerivceImpl<ImagesLib, ImagesLibExample, Long>
					implements ImageLibService{	
				}
		3.实现GenericSerivceImpl中的抽象方法getGenericMapper
			eg:
				@Autowired
				private BaseImagesLibMapper baseImagesLibMapper;
				
				/** 
				* 描述: TODO
				* @return    
				*/
				@Override
				public BaseMybatisObjectMapper<ImagesLib, ImagesLibExample, Long> getGenericMapper() {
					return baseImagesLibMapper;
				}
				
				
				
版本修改:
时间					问题													修改方案
------------------------------------------------------------------------------------------------------------------------------------------------------------
2014-11-08		1.在org.mybatis.generator.api.dom.java.Interface		自己创建了一个BaseMybatisObjectMapper.java文件,让所有自动生成的Mapper.java文件继承该接口,则子类不用再
				类中定义了List<FullyQualiExtendJavaType> 				其对应的接口方法.
				genericsTypes属性,同时生成get/set方法,该属性用于保存
				Class所有的包和类名
				
				2.在org.mybatis.generator.api.IntrospectedTable
				类中为系统生成的Mapper类和Mapper xml文件,的名称前面加上
				Base拼接名称,用于区分系统生成的文件与用户自己创建的文件.
				
				3.在org.mybatis.generator.codegen.mybatis3.javamapper
				.JavaMapperGenerator类中,添加一个BaseMybatisObjectMapper
				为每个生成的接口生成一个继承类
				
				4.创建了MysqlPaginationPalugin\DB2PaginationPalugin\OraclePaginationPlugin
				类,同时为要创建的Example添加了Page分页对象和filterFields字段过滤属性.
				
				5.修改org.mybatis.generator.codegen.mybatis3.xmlmapper.elements
				.AbstractXmlElementGenerator中的getBaseColumnListElement
				方法,为xml中添加judgeShowColum,在judgeShowColums中判断是否有
				filterFields属性,如果有,则用户用于过滤数据库字段.如果没有,则过去所有的字段.
				
				--------------------------------------------------------------------------------------------------------------------------------------------
				

2014-12-12		在生成view表的时候,由于生成的视图没有primary key,			将代码提取出来:introspectedTableMyBatis3Impl.getPrimaryKeyColumns();
				所以在org.mybatis.generator.codegen.mybatis3.			修改如下:
				javamapper.JavaMapperGenerator中出现了空指针				List<IntrospectedColumn> list = introspectedTableMyBatis3Impl.getPrimaryKeyColumns();
				异常.(126行)												将primaryKeyClassJavaType.setExtendClassName();设置的参数改为
																		list != null && !list.isEmpty()? list.get(0)
																	.getFullyQualifiedJavaType().getBaseQualifiedName() : null;即可解决该方法.
					
				

2014-12-12		由上面问题产生的连锁效应:由于生成的Mapper类需要传递泛型			在Mybatis-generate中,添加一个对象NullPrimaryKey用于标识其没有主键
				<M,E,I>:M : model E: Example I:PRIMARY KEY			后期还需修改生成的sql对应的xml的方法,以及Mapper生成的方法,例如屏蔽掉getXXXXByPrimaryKey(id);
				由于private key为null时,传入的I在泛型中不能为null,
				所以,生成的Mapper文件会报错.
				--------------------------------------------------------------------------------------------------------------------------------------------




		