<#include "/variable.include"/>
<#include "/macro.include"/>
<#include "/macro.copyright.include">
<#include "/macro.jacadoc.include"/>
<@generateCopyright ""/>
package ${basepackage}.model;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

<#include "/imports.java.include"/>

<@generateModelDoc/>
public class ${className} extends BaseModel {
	<#list table.columns as column>
	
	/**
     * @描述:${column.remarks}     
     * @字段:${column.sqlName} ${column.sqlTypeName}(${column.size})  
     */	
	<#if (!column.nullable)>
	@NotBlank(message = "${column.remarks}不能为空！")
	<#if column.javaType == "java.lang.String">
	@Length(min = 1, max = ${column.size}, message = "${column.remarks}长度在 {min}与{max}之间 ！")
	</#if>
	</#if>
	private ${column.javaType} ${column.columnNameLower};
	<#if column.isDateTimeColumn>
	
	/** 非数据库字段,查询时使用 */
	private ${column.javaType} ${column.columnNameLower}Begin;
	
	/** 非数据库字段,查询时使用 */
	private ${column.javaType} ${column.columnNameLower}End;
	</#if> 
	</#list>
	
	<@generateConstructor className/>
	<@generateJavaColumns/>
	<@generateJavaOneToMany/>
	<@generateJavaManyToOne/>
	
}


