<#include "/variable.include"/>
<#include "/macro.include"/>
<#include "/macro.copyright.include">
<#include "/macro.jacadoc.include"/>
<@generateCopyright "Dao"/>
package ${basepackage}.dao;

import cn.bidlink.framework.dao.ibatis.MyBatisBaseDao;
import ${basepackage}.model.${className};

<@generateDaoDoc/>
public interface ${className}Dao extends MyBatisBaseDao<${className}, ${table.idColumn.javaType}> {
	
}
