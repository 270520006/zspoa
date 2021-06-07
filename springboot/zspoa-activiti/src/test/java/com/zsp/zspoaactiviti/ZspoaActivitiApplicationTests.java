package com.zsp.zspoaactiviti;

//import com.baomidou.mybatisplus.annotation.DbType;
//import com.baomidou.mybatisplus.annotation.FieldFill;
//import com.baomidou.mybatisplus.generator.AutoGenerator;
//import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
//import com.baomidou.mybatisplus.generator.config.GlobalConfig;
//import com.baomidou.mybatisplus.generator.config.PackageConfig;
//import com.baomidou.mybatisplus.generator.config.StrategyConfig;
//import com.baomidou.mybatisplus.generator.config.po.TableFill;
//import com.baomidou.mybatisplus.generator.config.rules.DateType;
//import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.task.Task;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class ZspoaActivitiApplicationTests {
	@Autowired
	StringRedisTemplate stringRedisTemplate;

	@Test
	void testRedis(){
		ValueOperations<String, String> ops = stringRedisTemplate.opsForValue();
		ops.set("hello","I am zsp");
		String hello =ops.get("hello");
		System.out.println(hello);
	}
	@Test
	void testCreateTable() {
//        使用xml生成activity工作流
		Logger logger = LoggerFactory.getLogger(ZspoaActivitiApplicationTests.class);
//        获取activity提供的工具类
//        获取这个默认engine时就会自动创建数据库表
		ProcessEngine engine = ProcessEngines.getDefaultProcessEngine();

		System.out.println(engine);

	}
	// 1、部署流程
	@Test
	public void createActivityTask(){
		//获取默认的流程引擎
		ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();

		processEngine.getRepositoryService()//得到库服务
				.createDeployment()//创建部署
				.name("生病请假")
				.category("鹏哥")
				.addClasspathResource("bpm/rest.bpmn20.xml") //导入流程图  !!!!一定要加bpmn20
				.addClasspathResource("bpm/rest.bpmn20.png")   //导入流程文件!!!!一定要加bpmn20
				.deploy();   //开始部署
	}
	//2、启用创建的流程图进行审批
	@Test
	public void testStartProcessInstance(){
		ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
		//act_re_procdef表里的id，如果没生成，去看上一步，真是哔了狗
		processEngine.getRuntimeService()
				.startProcessInstanceById("myProcess_1:2:17504");

	}

	//3、请假人发出请假申请
	@Test
	public void testAsk(){
//        获取activity7的引擎
		ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
//    查看act_ru_task表，然后把id给上，发起请假申请
		processEngine.getTaskService()
				.complete("7502");

	}
	//    4、老师查看请假要求
	@Test
	public void queryTask(){
//          获得流引擎
		ProcessEngine engine = ProcessEngines.getDefaultProcessEngine();
//    创造搜索，下面的teacher是我们在创建bpmn的时候写的assignee值
		List<Task> student = engine.getTaskService().createTaskQuery()
//				.taskAssignee("student")  //根据审批人查询
				.taskOwner("zsp")  //根据任务拥有者进行查询
				.list();
		for (Task task : student) {
			System.out.println(task);
		}
	}

	//5、班主任审批
	@Test
	public void testFinishTask_manager(){
//        获取流引擎
		ProcessEngine engine = ProcessEngines.getDefaultProcessEngine();
//    这里的id是上面查询生成的请假申请id
		engine.getTaskService()
				.complete("12502");


	}
	@Test
	public void testSetOwner(){
		ProcessEngine engine = ProcessEngines.getDefaultProcessEngine();
		List<Task> teacher = engine.getTaskService()
				.createTaskQuery()
				.taskAssignee("policeman")
				.list();
		for (Task task : teacher) {
			System.out.println(task);
			System.out.println(task.getProcessInstanceId());
		}
		String id = teacher.get(0).getId();
		engine.getTaskService().complete(id);
	}
//	@Test
//	public void testGetCode(){
//	//        代码自动生成器对象
//	AutoGenerator autoGenerator = new AutoGenerator();
////        配置策略
//
//	//        1.全局配置  GlobalConfig 导入的包名generator.config
//	GlobalConfig gc = new GlobalConfig();
//	//        获取当前项目目录
//	String projectPath = System.getProperty("user.dir");
////        设置代码生成器自动生成路径
//        gc.setOutputDir(projectPath+"/src/main/java");
////        设置作者名字
//        gc.setAuthor("zsp");
////        自动生成后是否打开文件夹
//        gc.setOpen(false);
////        是否覆盖原本的文件
//        gc.setFileOverride(false);
////        去掉service的前缀
//        gc.setServiceName("%sService");
////        设置日期类型
//        gc.setDateType(DateType.ONLY_DATE);
////          设置生成swagger
////        gc.setSwagger2(true);
////        把全局配置丢进代码生成器中
//        autoGenerator.setGlobalConfig(gc);
//
//	//        2.设置数据源 就是数据库配置
//	DataSourceConfig dataSourceConfig =new DataSourceConfig();
//        dataSourceConfig.setUrl("jdbc:mysql://localhost:3306/zspoa_activiti?serverTimezone=Asia/Shanghai");
//        dataSourceConfig.setDbType(DbType.MYSQL);
//        dataSourceConfig.setDriverName("com.mysql.cj.jdbc.Driver");
//        dataSourceConfig.setUsername("root");
//        dataSourceConfig.setPassword("123456");
//        autoGenerator.setDataSource(dataSourceConfig);
//	//        3.包的配置
//	PackageConfig pc = new PackageConfig();
////        生成的模块名字
////        pc.setModuleName("user");
////        生成在什么文件夹内
//        pc.setParent("com.zsp.zspoaactiviti");
////        各层的名字
//        pc.setEntity("entity");
//        pc.setMapper("mapper");
//        pc.setService("service");
//        pc.setController("controller");
//        autoGenerator.setPackageInfo(pc);
//	//        4.策略：映射对应的表
//	StrategyConfig strategy = new StrategyConfig();
////	如果数据库表过多可以使用SELECT table_name FROM information_schema.tables WHERE table_schema='表名'
//        strategy.setInclude("act_ge_bytearray","act_hi_actinst","act_hi_procinst","act_hi_taskinst","act_re_deployment","act_ru_task"); //要映射的表名，要弄几个表出来，上面这是四个
//        strategy.setNaming(NamingStrategy.underline_to_camel); //开启包的命名规则,下划线转驼峰
//        strategy.setColumnNaming(NamingStrategy.underline_to_camel);//列也支持下划线转驼峰
//        strategy.setEntityLombokModel(true);//开启lombok
//        strategy.setLogicDeleteFieldName("status");
//	//        自动填充策略:设置自动更新操作时间
//	TableFill userCreate = new TableFill("user_create", FieldFill.INSERT);
//	TableFill userModified = new TableFill("user_modified", FieldFill.INSERT_UPDATE);
//	//        把自动填充策略放进去
//	ArrayList<TableFill>tableFill = new ArrayList<>();
//        tableFill.add(userCreate);
//        tableFill.add(userModified);
//        strategy.setTableFillList(tableFill);
////        乐观锁
//        strategy.setVersionFieldName("version");
////        开启restful风格
//        strategy.setRestControllerStyle(true);
////        开启url下划线命名类似于 localhost:8080/user_1234_2312
//        strategy.setControllerMappingHyphenStyle(true);
////        放入策略
//        autoGenerator.setStrategy(strategy);
//	//        执行代码生成器
//        autoGenerator.execute();
//}

}
