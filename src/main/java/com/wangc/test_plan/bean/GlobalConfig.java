package com.wangc.test_plan.bean;

import java.io.*;

public class GlobalConfig {
    private static GlobalConfig GLOBAL_CONFIG = new GlobalConfig(); 
    
    private String jmeterHome;//jmeter的安装目录
    private boolean osFlag;//os类型  true:win  false:linux
    private String userDir;//当前程序所在的目录
    
    private final String jmeterHomeConfigFilePath = "JMETER_HOME.config";  
    
    //在初始化的时候做这些，这样程序中可以直接使用上面的变量了。
    GlobalConfig(){
        setUserDir();
        setOsIsWin();
        setJmeterHome();
    }

    public static GlobalConfig getInstance() {
        return GLOBAL_CONFIG;
    }
    
    private void setUserDir(){
        this.userDir = System.getProperty("user.dir"); 
    }
    
    public String getUserDir(){
        return this.userDir;
    }

    private void setOsIsWin(){
        String os = System.getProperty("os.name");
        if( os.contains("Linux") ){
            this.osFlag = false;
        }else if(os.contains("Windows")){
            this.osFlag = true;
        }else{
            throw new RuntimeException("获取操作系统类型异常。");
        }
    }

    public boolean getOsFlag(){
        return this.osFlag;
    }


    //读取配置文件 JMETER_HOME.config 配置文件中只有一行数据，即jmeter的安装目录。
    //因为安装目录可能包含空格，所以无法放到启动命令参数中。
    private void setJmeterHome(){
        File file = new File(userDir+File.separator+jmeterHomeConfigFilePath);
        BufferedReader reader = null;
        try {
            System.out.println("以行为单位读取文件内容，一次读一整行：");
            reader = new BufferedReader(new FileReader(file));
            this.jmeterHome = reader.readLine();
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                }
            }
        }
    }
    public String getJmeterHome(){
        return this.jmeterHome;
    }



}
