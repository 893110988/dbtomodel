<#include "/variable.include"/>
<#include "/macro.include"/>
<#include "/macro.copyright.include">
<#include "/macro.jacadoc.include"/>
<@generateCopyright "Service"/>
package ${basepackage}.service;

import java.util.List;

import ${basepackage}.model.${className};

public interface ${className}Service {
	
	/**
	 * 保存.
	 * 
	 * @author 	: <a href="mailto:admin@ebnew.com">SYSTEM</a>  
	 * @param ${classNameFirstLower}
	 *            需要的操作实体
	 * @return 返回主键
	 */
	${table.idColumn.javaType} save(${className} ${classNameFirstLower});
	
	/**
	 * 删除.
	 *
	 * @author 	: <a href="mailto:admin@ebnew.com">SYSTEM</a>  
	 * @param ${classNameFirstLower}
	 *            需要的操作实体
	 * @return 影响的行数            
	 */
	int delete(${className} ${classNameFirstLower});

	/**
	 * 修改.
	 *
	 * @author 	: <a href="mailto:admin@ebnew.com">SYSTEM</a>  
	 * @param ${classNameFirstLower}
	 *            需要的操作实体
	 * @return 影响的行数            
	 */
	int update(${className} ${classNameFirstLower});

	/**
	 * 主键查询.
	 * 
	 * @author 	: <a href="mailto:admin@ebnew.com">SYSTEM</a>  
	 * @param ${classNameFirstLower}
	 *            需要的操作实体
	 * @return 查询结果
	 */
	${className} get(${className} ${classNameFirstLower});

	/**
	 * 按条件查询.
	 * 
	 * @author 	: <a href="mailto:admin@ebnew.com">SYSTEM</a>  
	 * @param condition
	 *            对象查询条件
	 * @return 查询结果集条数
	 */
	int findCountByCondition(${className} condition);
	
	/**
	 * 按条件查询(分页).
	 * 
	 * @author 	: <a href="mailto:admin@ebnew.com">SYSTEM</a>  
	 * @param condition
	 *            对象查询条件
	 * @param start
	 *            开始条数
	 * @param limit
	 *            显示条数
	 * @return 查询结果集
	 */
	java.util.List<${className}> findByCondition(${className} condition, int start, int limit);
	
}
