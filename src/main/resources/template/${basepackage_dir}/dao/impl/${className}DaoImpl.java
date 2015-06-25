<#include "/variable.include"/>
<#include "/macro.include"/>
<#include "/macro.copyright.include">
<#include "/macro.jacadoc.include"/>
<@generateCopyright "DaoImpl"/>
package ${basepackage}.dao.impl;

import cn.bidlink.framework.core.annotation.Dao;
import cn.bidlink.framework.dao.ibatis.impl.MyBatisBaseDaoImpl;
import ${basepackage}.dao.${className}Dao;
import ${basepackage}.model.${className};

<@generateDaoImplDoc/>
@Dao
public class ${className}DaoImpl extends MyBatisBaseDaoImpl<${className}, ${table.idColumn.javaType}> implements ${className}Dao {
	
}
