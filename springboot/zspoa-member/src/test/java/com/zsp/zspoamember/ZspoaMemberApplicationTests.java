package com.zsp.zspoamember;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.po.TableFill;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;

@SpringBootTest
class ZspoaMemberApplicationTests {

    @Test
    void contextLoads() {

        //        代码自动生成器对象
        AutoGenerator autoGenerator = new AutoGenerator();
//        配置策略

//        1.全局配置  GlobalConfig 导入的包名generator.config
        GlobalConfig gc = new GlobalConfig();
//        获取当前项目目录
        String projectPath = System.getProperty("user.dir");
//        设置代码生成器自动生成路径
        gc.setOutputDir(projectPath+"/src/main/java");
//        设置作者名字
        gc.setAuthor("zsp");
//        自动生成后是否打开文件夹
        gc.setOpen(false);
//        是否覆盖原本的文件
        gc.setFileOverride(false);
//        去掉service的前缀
        gc.setServiceName("%sService");
//        设置日期类型
        gc.setDateType(DateType.ONLY_DATE);
//          设置生成swagger
//        gc.setSwagger2(true);
//        把全局配置丢进代码生成器中
        autoGenerator.setGlobalConfig(gc);

//        2.设置数据源 就是数据库配置
        DataSourceConfig dataSourceConfig =new DataSourceConfig();
        dataSourceConfig.setUrl("jdbc:mysql://localhost:3306/zspoa_member?serverTimezone=Asia/Shanghai");
        dataSourceConfig.setDbType(DbType.MYSQL);
        dataSourceConfig.setDriverName("com.mysql.cj.jdbc.Driver");
        dataSourceConfig.setUsername("root");
        dataSourceConfig.setPassword("123456");
        autoGenerator.setDataSource(dataSourceConfig);
//        3.包的配置
        PackageConfig pc = new PackageConfig();
//        生成的模块名字
//        pc.setModuleName("user");
//        生成在什么文件夹内
        pc.setParent("com.zsp.zspoamember");
//        各层的名字
        pc.setEntity("entity");
        pc.setMapper("mapper");
        pc.setService("service");
        pc.setController("controller");
        autoGenerator.setPackageInfo(pc);
//        4.策略：映射对应的表
        StrategyConfig strategy = new StrategyConfig();
        strategy.setInclude("member","member_activity"); //要映射的表名，要弄几个表出来，上面这是四个
        strategy.setNaming(NamingStrategy.underline_to_camel); //开启包的命名规则,下划线转驼峰
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);//列也支持下划线转驼峰
        strategy.setEntityLombokModel(true);//开启lombok
        strategy.setLogicDeleteFieldName("status");
//        自动填充策略:设置自动更新操作时间
        TableFill userCreate = new TableFill("user_create", FieldFill.INSERT);
        TableFill userModified = new TableFill("user_modified", FieldFill.INSERT_UPDATE);
//        把自动填充策略放进去
        ArrayList<TableFill>tableFill = new ArrayList<>();
        tableFill.add(userCreate);
        tableFill.add(userModified);
        strategy.setTableFillList(tableFill);
//        乐观锁
        strategy.setVersionFieldName("version");
//        开启restful风格
        strategy.setRestControllerStyle(true);
//        开启url下划线命名类似于 localhost:8080/user_1234_2312
        strategy.setControllerMappingHyphenStyle(true);
//        放入策略
        autoGenerator.setStrategy(strategy);
        //        执行代码生成器
        autoGenerator.execute();
    }


}
