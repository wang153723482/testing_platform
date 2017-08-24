[下载](https://github.com/wang153723482/testing_platform/releases)

### 环境
1. windows 7 或 ubuntu 16.04-10
2. 安装jdk 1.8，并配置环境变量。
3. 安装jmeter 3.2。对于windows操作系统，必须配置环境变量；对于linux操作系统，需要新建配置文件 `JMETER_HOME.config` ，填入jmeter安装目录，例如`/usr/local/apache-jmeter-3.2`，跟jar文件放在同一目录下。

### 运行 
    
    java -jar testing_platform-0.1-SNAPSHOT.jar
     
如果在本机运行，浏览器访问 http://本机ip:20302

如果在远程服务器上运行，则需要将ip改为对应服务器ip即可。


指定端口运行

    java -jar testing_platform-0.1-SNAPSHOT.jar --server.port=9999

### 原理
上传/新建jmx脚本，通过java执行 `jmeter -n -t xxx.jmx -o -p dir` 来运行脚本并生成html报告。

web框架基于springboot，数据库使用了轻量级的SQLite。


        
        
        
