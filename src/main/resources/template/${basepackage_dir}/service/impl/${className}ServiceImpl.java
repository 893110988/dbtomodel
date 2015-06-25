<#include "/variable.include"/>
<#include "/macro.include"/>
<#include "/macro.copyright.include">
<#include "/macro.jacadoc.include"/>
<@generateCopyright "ServiceImpl"/>
package ${basepackage}.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.bidlink.framework.core.utils.validator.ValidatorUtils;
import ${basepackage}.dao.${className}Dao;
import ${basepackage}.model.${className};
import ${basepackage}.service.${className}Service;

<@generateServiceImplDoc/>
@Service
public class ${className}ServiceImpl implements ${className}Service {
	
	@Autowired
	private ${className}Dao ${classNameFirstLower}Dao;

	@Override
	public ${table.idColumn.javaType} save(${className} ${classNameFirstLower}) {
		ValidatorUtils.verifyAll(${classNameFirstLower}, null, true);
		
		${classNameFirstLower}Dao.save(${classNameFirstLower});
		return ${classNameFirstLower}.getId();
	}

	@Override
	public int delete(${className} ${classNameFirstLower}) {
		ValidatorUtils.verifyProperty(${classNameFirstLower}, "id", null, true);
		
		return ${classNameFirstLower}Dao.deleteExp(${classNameFirstLower}.getId(), ${className}.class);
	}

	@Override
	public int update(${className} ${classNameFirstLower}) {
		ValidatorUtils.verifyAll(${classNameFirstLower}, null, true);
		
		return ${classNameFirstLower}Dao.updateExp(${classNameFirstLower});
	}

	@Override
	public ${className} get(${className} ${classNameFirstLower}) {
 		ValidatorUtils.verifyProperty(${classNameFirstLower}, "id", null, true);
 		
		return ${classNameFirstLower}Dao.findByPK(${classNameFirstLower}.getId(), ${className}.class);
	}

	@Override
	public int findCountByCondition(${className} condition) {
		return ${classNameFirstLower}Dao.getTotalCount(condition == null ? new ${className}() : condition);
	}

	@Override
	public List<${className}> findByCondition(${className} condition, int start, int limit) {
		List<${className}> result = ${classNameFirstLower}Dao.findByCondition(condition == null ? new ${className}() : condition, start, limit);
		return result != null ? result : new ArrayList<${className}>();
	}
	
}
