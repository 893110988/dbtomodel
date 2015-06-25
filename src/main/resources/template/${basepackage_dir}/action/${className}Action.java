<#include "/variable.include"/>
<#include "/macro.include"/>
<#include "/macro.copyright.include">
<#include "/macro.jacadoc.include"/>
<@generateCopyright "Action"/>
package ${basepackage}.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;

import ${basepackage}.utils.PageHelper;
import ${basepackage}.utils.Pager;
import cn.bidlink.framework.core.model.ResponseEntity;
import ${basepackage}.model.${className};
import ${basepackage}.service.${className}Service;

<@generateActionDoc/>
@Controller
@RequestMapping(value = "/${sqlName}")
public class ${className}Action {
	
	@Autowired
    private ${className}Service ${classNameFirstLower}Service;

    @RequestMapping(value = "save",method=RequestMethod.POST)
    public @ResponseBody ResponseEntity  save(${className} ${classNameFirstLower}) {
        ${classNameFirstLower}Service.save(${classNameFirstLower});
        return new ResponseEntity(201,"创建成功");
    }
    
    @RequestMapping(value = "update",method=RequestMethod.POST)
    public @ResponseBody ResponseEntity  update(${className} ${classNameFirstLower}) {
    	${classNameFirstLower}Service.update(${classNameFirstLower});
    	return new ResponseEntity(201,"更新成功");
    }
    
    @RequestMapping(value = "remove",method=RequestMethod.POST)
    public @ResponseBody ResponseEntity  remove(${className} ${classNameFirstLower}) {
    	${classNameFirstLower}Service.delete(${classNameFirstLower});
    	return new ResponseEntity(202,"删除成功");
    }

    @RequestMapping(value = "list",method=RequestMethod.POST)
    public  @ResponseBody Pager<${className} > list(${className} ${classNameFirstLower},HttpServletRequest request) {
    	PageHelper pageHelper = new PageHelper(request);
    	List<${className}> result = ${classNameFirstLower}Service.findByCondition(${classNameFirstLower},pageHelper.getStart(),pageHelper.getLimit());
    	int count = ${classNameFirstLower}Service.findCountByCondition(${classNameFirstLower});
    	Pager<${className}> pager = new Pager<${className}>().setResults(count).setRows(result);		
        return pager; 
    }
    
    @RequestMapping(value = "get",method=RequestMethod.GET)
    public ${className} get(${table.idColumn.javaType} id) {
    	${className} ${classNameFirstLower} = ${classNameFirstLower}Service.get(new ${className}(id));
        return ${classNameFirstLower};
    }
}
