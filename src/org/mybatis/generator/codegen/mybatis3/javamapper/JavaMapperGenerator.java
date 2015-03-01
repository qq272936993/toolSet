/*
 *  Copyright 2009 The Apache Software Foundation
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package org.mybatis.generator.codegen.mybatis3.javamapper;

import static org.mybatis.generator.internal.util.StringUtility.stringHasValue;
import static org.mybatis.generator.internal.util.messages.Messages.getString;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.mybatis.generator.BaseMybatisObjectMapper;
import org.mybatis.generator.FullyQualiExtendJavaType;
import org.mybatis.generator.NullPrimarKey;
import org.mybatis.generator.api.CommentGenerator;
import org.mybatis.generator.api.FullyQualifiedTable;
import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.dom.java.CompilationUnit;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.Interface;
import org.mybatis.generator.api.dom.java.JavaVisibility;
import org.mybatis.generator.codegen.AbstractGenerator;
import org.mybatis.generator.codegen.AbstractJavaClientGenerator;
import org.mybatis.generator.codegen.AbstractXmlGenerator;
import org.mybatis.generator.codegen.mybatis3.IntrospectedTableMyBatis3Impl;
import org.mybatis.generator.codegen.mybatis3.javamapper.elements.AbstractJavaMapperMethodGenerator;
import org.mybatis.generator.codegen.mybatis3.javamapper.elements.CountByExampleMethodGenerator;
import org.mybatis.generator.codegen.mybatis3.javamapper.elements.DeleteByExampleMethodGenerator;
import org.mybatis.generator.codegen.mybatis3.javamapper.elements.DeleteByPrimaryKeyMethodGenerator;
import org.mybatis.generator.codegen.mybatis3.javamapper.elements.InsertMethodGenerator;
import org.mybatis.generator.codegen.mybatis3.javamapper.elements.InsertSelectiveMethodGenerator;
import org.mybatis.generator.codegen.mybatis3.javamapper.elements.SelectByExampleWithBLOBsMethodGenerator;
import org.mybatis.generator.codegen.mybatis3.javamapper.elements.SelectByExampleWithoutBLOBsMethodGenerator;
import org.mybatis.generator.codegen.mybatis3.javamapper.elements.SelectByPrimaryKeyMethodGenerator;
import org.mybatis.generator.codegen.mybatis3.javamapper.elements.UpdateByExampleSelectiveMethodGenerator;
import org.mybatis.generator.codegen.mybatis3.javamapper.elements.UpdateByExampleWithBLOBsMethodGenerator;
import org.mybatis.generator.codegen.mybatis3.javamapper.elements.UpdateByExampleWithoutBLOBsMethodGenerator;
import org.mybatis.generator.codegen.mybatis3.javamapper.elements.UpdateByPrimaryKeySelectiveMethodGenerator;
import org.mybatis.generator.codegen.mybatis3.javamapper.elements.UpdateByPrimaryKeyWithBLOBsMethodGenerator;
import org.mybatis.generator.codegen.mybatis3.javamapper.elements.UpdateByPrimaryKeyWithoutBLOBsMethodGenerator;
import org.mybatis.generator.codegen.mybatis3.xmlmapper.XMLMapperGenerator;
import org.mybatis.generator.config.PropertyRegistry;

/**
 * @author Jeff Butler
 * 
 */
public class JavaMapperGenerator extends AbstractJavaClientGenerator {

	/**
     * 
     */
	public JavaMapperGenerator() {
		super(true);
	}

	public JavaMapperGenerator(boolean requiresMatchedXMLGenerator) {
		super(requiresMatchedXMLGenerator);
	}

	@Override
	public List<CompilationUnit> getCompilationUnits() {
		progressCallback.startTask(getString("Progress.17", //$NON-NLS-1$
				introspectedTable.getFullyQualifiedTable().toString()));
		CommentGenerator commentGenerator = context.getCommentGenerator();

		FullyQualifiedJavaType type = new FullyQualifiedJavaType(introspectedTable.getMyBatis3JavaMapperType());
		Interface interfaze = new Interface(type);
		interfaze.setVisibility(JavaVisibility.PUBLIC);
		commentGenerator.addJavaFileComment(interfaze);

		String rootInterface = introspectedTable.getTableConfigurationProperty(PropertyRegistry.ANY_ROOT_INTERFACE);
		if (!stringHasValue(rootInterface)) {
			rootInterface = context.getJavaClientGeneratorConfiguration().getProperty(
					PropertyRegistry.ANY_ROOT_INTERFACE);
		}

		if (stringHasValue(rootInterface)) {
			FullyQualifiedJavaType fqjt = new FullyQualifiedJavaType(rootInterface);
			interfaze.addSuperInterface(fqjt);
			interfaze.addImportedType(fqjt);
		}
		
		//yws update start
		FullyQualifiedJavaType fullyQualifiedJavaType = new FullyQualifiedJavaType(BaseMybatisObjectMapper.class.getCanonicalName());
		FullyQualiExtendJavaType extendJavaType = new FullyQualiExtendJavaType();
		Map<String,FullyQualifiedJavaType> namePackageMap = AbstractGenerator.MODEL_NAMEPACKAGE_MAP;
		
		extendJavaType.setExtendClassName(BaseMybatisObjectMapper.class.getName());
		extendJavaType.setExtendPackage("org.mybatis.generator");
		List<FullyQualiExtendJavaType> genericsTypes = new ArrayList<FullyQualiExtendJavaType>();
		IntrospectedTableMyBatis3Impl introspectedTableMyBatis3Impl = (IntrospectedTableMyBatis3Impl) introspectedTable;
		FullyQualifiedTable fullyQualifiedTable = introspectedTableMyBatis3Impl.getFullyQualifiedTable();
		String domainName = fullyQualifiedTable.getDomainObjectName();
		String mapperName = domainName+"Example";
		
		//add model class
		FullyQualiExtendJavaType modelClassJavaType = new FullyQualiExtendJavaType();
		String packageName = namePackageMap.get(domainName).getPackageName();
		modelClassJavaType.setExtendPackage(packageName);
		modelClassJavaType.setExtendClassName(domainName);
		genericsTypes.add(modelClassJavaType);
		interfaze.addImportedType(new FullyQualifiedJavaType(packageName +"."+ domainName));
		
		//add Example class
		FullyQualiExtendJavaType exampleClassJavaType = new FullyQualiExtendJavaType();
		packageName = namePackageMap.get(mapperName).getPackageName();
		exampleClassJavaType.setExtendPackage(packageName);
		exampleClassJavaType.setExtendClassName(mapperName);
		genericsTypes.add(exampleClassJavaType);
		interfaze.addImportedType(new FullyQualifiedJavaType(packageName+"."+mapperName));
//		interfaze.addImportedType(importedType);
		//add primary key class
		FullyQualiExtendJavaType primaryKeyClassJavaType = new FullyQualiExtendJavaType();
		List<IntrospectedColumn> list =introspectedTableMyBatis3Impl.getPrimaryKeyColumns();
		primaryKeyClassJavaType.setExtendClassName(list != null && !list.isEmpty()? list.get(0)
				.getFullyQualifiedJavaType().getBaseQualifiedName() : NullPrimarKey.class.getName());
		genericsTypes.add(primaryKeyClassJavaType);
		
		
		//这里是添加接口的方法
		interfaze.addImportedType(fullyQualifiedJavaType);
		//这里添加泛型的类
		interfaze.setGenericsTypes(genericsTypes);
		interfaze.addSuperInterface(new FullyQualifiedJavaType(BaseMybatisObjectMapper.class.getCanonicalName()));
		//yws update end
		/*
		addCountByExampleMethod(interfaze);
		addDeleteByExampleMethod(interfaze);
		addDeleteByPrimaryKeyMethod(interfaze);
		addInsertMethod(interfaze);
		addInsertSelectiveMethod(interfaze);
		addSelectByExampleWithBLOBsMethod(interfaze);
		addSelectByExampleWithoutBLOBsMethod(interfaze);
		addSelectByPrimaryKeyMethod(interfaze);
		addUpdateByExampleSelectiveMethod(interfaze);
		addUpdateByExampleWithBLOBsMethod(interfaze);
		addUpdateByExampleWithoutBLOBsMethod(interfaze);
		addUpdateByPrimaryKeySelectiveMethod(interfaze);
		addUpdateByPrimaryKeyWithBLOBsMethod(interfaze);
		addUpdateByPrimaryKeyWithoutBLOBsMethod(interfaze);
		*/
		List<CompilationUnit> answer = new ArrayList<CompilationUnit>();
		if (context.getPlugins().clientGenerated(interfaze, null, introspectedTable)) {
			answer.add(interfaze);
		}

		List<CompilationUnit> extraCompilationUnits = getExtraCompilationUnits();
		if (extraCompilationUnits != null) {
			answer.addAll(extraCompilationUnits);
		}

		return answer;
	}

	protected void addCountByExampleMethod(Interface interfaze) {
		if (introspectedTable.getRules().generateCountByExample()) {
			AbstractJavaMapperMethodGenerator methodGenerator = new CountByExampleMethodGenerator();
			initializeAndExecuteGenerator(methodGenerator, interfaze);
		}
	}

	protected void addDeleteByExampleMethod(Interface interfaze) {
		if (introspectedTable.getRules().generateDeleteByExample()) {
			AbstractJavaMapperMethodGenerator methodGenerator = new DeleteByExampleMethodGenerator();
			initializeAndExecuteGenerator(methodGenerator, interfaze);
		}
	}

	protected void addDeleteByPrimaryKeyMethod(Interface interfaze) {
		if (introspectedTable.getRules().generateDeleteByPrimaryKey()) {
			AbstractJavaMapperMethodGenerator methodGenerator = new DeleteByPrimaryKeyMethodGenerator();
			initializeAndExecuteGenerator(methodGenerator, interfaze);
		}
	}

	protected void addInsertMethod(Interface interfaze) {
		if (introspectedTable.getRules().generateInsert()) {
			AbstractJavaMapperMethodGenerator methodGenerator = new InsertMethodGenerator();
			initializeAndExecuteGenerator(methodGenerator, interfaze);
		}
	}

	protected void addInsertSelectiveMethod(Interface interfaze) {
		if (introspectedTable.getRules().generateInsertSelective()) {
			AbstractJavaMapperMethodGenerator methodGenerator = new InsertSelectiveMethodGenerator();
			initializeAndExecuteGenerator(methodGenerator, interfaze);
		}
	}

	protected void addSelectByExampleWithBLOBsMethod(Interface interfaze) {
		if (introspectedTable.getRules().generateSelectByExampleWithBLOBs()) {
			AbstractJavaMapperMethodGenerator methodGenerator = new SelectByExampleWithBLOBsMethodGenerator();
			initializeAndExecuteGenerator(methodGenerator, interfaze);
		}
	}

	protected void addSelectByExampleWithoutBLOBsMethod(Interface interfaze) {
		if (introspectedTable.getRules().generateSelectByExampleWithoutBLOBs()) {
			AbstractJavaMapperMethodGenerator methodGenerator = new SelectByExampleWithoutBLOBsMethodGenerator();
			initializeAndExecuteGenerator(methodGenerator, interfaze);
		}
	}

	protected void addSelectByPrimaryKeyMethod(Interface interfaze) {
		if (introspectedTable.getRules().generateSelectByPrimaryKey()) {
			AbstractJavaMapperMethodGenerator methodGenerator = new SelectByPrimaryKeyMethodGenerator();
			initializeAndExecuteGenerator(methodGenerator, interfaze);
		}
	}

	protected void addUpdateByExampleSelectiveMethod(Interface interfaze) {
		if (introspectedTable.getRules().generateUpdateByExampleSelective()) {
			AbstractJavaMapperMethodGenerator methodGenerator = new UpdateByExampleSelectiveMethodGenerator();
			initializeAndExecuteGenerator(methodGenerator, interfaze);
		}
	}

	protected void addUpdateByExampleWithBLOBsMethod(Interface interfaze) {
		if (introspectedTable.getRules().generateUpdateByExampleWithBLOBs()) {
			AbstractJavaMapperMethodGenerator methodGenerator = new UpdateByExampleWithBLOBsMethodGenerator();
			initializeAndExecuteGenerator(methodGenerator, interfaze);
		}
	}

	protected void addUpdateByExampleWithoutBLOBsMethod(Interface interfaze) {
		if (introspectedTable.getRules().generateUpdateByExampleWithoutBLOBs()) {
			AbstractJavaMapperMethodGenerator methodGenerator = new UpdateByExampleWithoutBLOBsMethodGenerator();
			initializeAndExecuteGenerator(methodGenerator, interfaze);
		}
	}

	protected void addUpdateByPrimaryKeySelectiveMethod(Interface interfaze) {
		if (introspectedTable.getRules().generateUpdateByPrimaryKeySelective()) {
			AbstractJavaMapperMethodGenerator methodGenerator = new UpdateByPrimaryKeySelectiveMethodGenerator();
			initializeAndExecuteGenerator(methodGenerator, interfaze);
		}
	}

	protected void addUpdateByPrimaryKeyWithBLOBsMethod(Interface interfaze) {
		if (introspectedTable.getRules().generateUpdateByPrimaryKeyWithBLOBs()) {
			AbstractJavaMapperMethodGenerator methodGenerator = new UpdateByPrimaryKeyWithBLOBsMethodGenerator();
			initializeAndExecuteGenerator(methodGenerator, interfaze);
		}
	}

	protected void addUpdateByPrimaryKeyWithoutBLOBsMethod(Interface interfaze) {
		if (introspectedTable.getRules().generateUpdateByPrimaryKeyWithoutBLOBs()) {
			AbstractJavaMapperMethodGenerator methodGenerator = new UpdateByPrimaryKeyWithoutBLOBsMethodGenerator();
			initializeAndExecuteGenerator(methodGenerator, interfaze);
		}
	}

	protected void initializeAndExecuteGenerator(AbstractJavaMapperMethodGenerator methodGenerator, Interface interfaze) {
		methodGenerator.setContext(context);
		methodGenerator.setIntrospectedTable(introspectedTable);
		methodGenerator.setProgressCallback(progressCallback);
		methodGenerator.setWarnings(warnings);
		methodGenerator.addInterfaceElements(interfaze);
	}

	public List<CompilationUnit> getExtraCompilationUnits() {
		return null;
	}

	@Override
	public AbstractXmlGenerator getMatchedXMLGenerator() {
		return new XMLMapperGenerator();
	}
}
