package wmm.project;

import java.io.File;

import cn.org.rapid_framework.generator.Generator;
import cn.org.rapid_framework.generator.GeneratorFacade;
import cn.org.rapid_framework.generator.GeneratorProperties;

/**
 * @description 从数据库生成代码
 * @author <a href="wangmm_1992@163.com">阿蒙</a>
 * @date 2013-8-25 上午9:52:32
 */
public class GenCodeFromDB {

	public static void main(String[] args) throws Exception {
		GenCodeFromDB db=new GenCodeFromDB();

//		GeneratorConstants.DATABASE_TYPE;
		
		GeneratorFacade g = new GeneratorFacade();
 		g.deleteOutRootDir(); // 删除生成器的输出目录
		// g.generateByTable("table_name","template");
		// //通过数据库表生成文件,template为模板的根目录
 		 Generator generator = new Generator();
 		 generator.setTemplateRootDir(new File(db.getClass().getResource("/").getFile().toString()+"/template"));
 		 generator.setOutRootDir(GeneratorProperties.getProperty("outRoot"));
 		 g.setGenerator(generator);
		g.generateByTable("bms_role");
 		// g.generateByAllTable();

//		g.generateByAllTable("template"); // 自动搜索数据库中的所有表并生成文件,template为模板的根目录
		// g.generateByClass(Blog.class,"template_clazz");
		// g.deleteByTable("table_name", "template"); //删除生成的文件
	}
}
