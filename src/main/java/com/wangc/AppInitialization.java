package com.wangc;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by wangchao on 2017/7/26.
 */

@Component
public class AppInitialization implements ApplicationRunner {

    private final String sqlCreateTestPlan = "CREATE TABLE `test_plan` (\n" + "  `id` INTEGER PRIMARY KEY     ,\n" + "  `tp_name` text ,\n" + "  `url` text ,\n" + "  `description` text ,\n" + "  `generater` text ,\n" + "  `protocol` text ,\n" + "  `server_name_ip` text ,\n" + "  `port_num` text ,\n" + "  `path` text ,\n" + "  `default_vu` INTEGER ,\n" + "  `default_duration` INTEGER ,\n" + "  `jmx_save_path` text ,\n" + "  `create_time` text ,\n"+ "  `csv_data_xpath` text \n" + ") ";
    private final String sqlCreateRunPlan = "CREATE TABLE `run_plan` (\n" + "  `id` INTEGER PRIMARY KEY ,\n" + "  `tp_id` INTEGER ,\n" + "  `duration` INTEGER ,\n" + "  `users_num` INTEGER ,\n" + "  `ramp_up` INTEGER ,\n" + "  `jmx_path` TEXT ,\n" + "  `jtl_path` TEXT ,\n" + "  `log_path` TEXT ,\n" + "  `html_path` TEXT ,\n" + "  `create_time` TEXT \n" + ")";
    private Connection conn = null;
    Statement stmt = null;
    private final String dbName = "testing_platform.db";

    static{
//        System.out.println("========1");
    }

    @Override
    public void run(ApplicationArguments applicationArguments) throws Exception {
        checkJmeterHome();//检测jmeter运行环境
        
        checkDB();//检测数据库 
        
        checkOS();//检测操作系统类型 todo
        
    }
    
    //检测操作系统类型，并将结果保存到全局变量中
    private void checkOS(){
        
    }
    
    
    //检测jmeter运行环境，尝试运行jmeter -v ，返回内容中包含 The Apache Software Foundation 即可。
    public void checkJmeterHome(){
        
    }
    
    
    private void checkDB() throws SQLException {
        if(!haveDB()){
            createDB();
            createTables();
            close();
        }
    }
    
    //检测是否有sqlite数据库,如果没有返回false
    private boolean haveDB(){
        File f = new File(dbName);
        return f.exists();
    }
    
    private void createDB() throws SQLException {
        conn = DriverManager.getConnection("jdbc:sqlite:"+dbName);
    }
    
    private void createTables() throws SQLException {
        stmt = conn.createStatement();
        stmt.execute(sqlCreateTestPlan);
        stmt.execute(sqlCreateRunPlan);
    }
    
    private void close() throws SQLException {
        stmt.close();
        conn.close();
    }
}
