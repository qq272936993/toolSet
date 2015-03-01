/*
 *  Copyright 2010 The MyBatis Team
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
package org.mybatis.generator.codegen.mybatis3.javamapper.elements.sqlprovider;

import java.util.List;

import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.dom.java.Method;
import org.mybatis.generator.api.dom.java.TopLevelClass;

/**
 * 
 * @author Jeff Butler
 * 
 */
public class ProviderUpdateByExampleWithBLOBsMethodGenerator extends ProviderUpdateByExampleWithoutBLOBsMethodGenerator {

	public ProviderUpdateByExampleWithBLOBsMethodGenerator() {
		super();
	}

	@Override
	public String getMethodName() {
		return introspectedTable.getUpdateByExampleWithBLOBsStatementId();
	}

	@Override
	public List<IntrospectedColumn> getColumns() {
		return introspectedTable.getAllColumns();
	}

	@Override
	public boolean callPlugins(Method method, TopLevelClass topLevelClass) {
		return context.getPlugins().providerUpdateByExampleWithBLOBsMethodGenerated(method, topLevelClass,
				introspectedTable);
	}
}
