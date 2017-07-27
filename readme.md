[下载](https://github.com/wang153723482/testing_platform/releases)

### 环境
1. windows 7 或 ubuntu 16.04-10
2. 安装jdk 1.8，并配置环境变量。（即，在命令提示符/终端中输入`java -version`能返回版本信息）
3. 安装jmeter 3.2，并配置环境变量。（即，在命令提示符/终端中输入`jmeter -v`能返回版本信息）

### 运行 
    
    java -jar testing_platform-0.1-SNAPSHOT.jar

### 原理
上传/新建jmx脚本，通过执行 `jmeter -n -t xxx.jmx -o -p dir` 来运行脚本并生成html报告

web框架基于springboot，数据库使用了轻量级的SQLite。


        
        
        
