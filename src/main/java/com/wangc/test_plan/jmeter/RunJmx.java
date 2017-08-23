package com.wangc.test_plan.jmeter;

import com.wangc.comm.Param;
import com.wangc.comm.StringUtils;
import com.wangc.test_plan.bean.RunPlanBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

/**
 * Created by wangchao on 2017/3/13.
 * 运行jmx脚本
 */
public class RunJmx {

    private static String FORMAT_1 = "ddHHmmssSSS";
    private static String JMETER_COMMOND = "jmeter -n -t ${jmx} -l ${jtl} -j ${log} -e -o ${html}";

    private static String[] JMETER_RUN_COMMOND = new String[]{"","",""};
    private static String LINUX_COMMOND = "/bin/sh";
    private static String LINUX_COMMOND_PARAM = "-C";
    private static String WIN_COMMOND = "cmd";
    private static String WIN_COMMOND_PARAM = "/c";

    private static String os = System.getProperty("os.name");

    private static final Logger logger = LoggerFactory.getLogger(RunJmx.class);


    public static void run(RunPlanBean rpb) {
        String jmxPath = Param.USER_DIR +rpb.getTestPlanBean().getJmxSavePath();
        // TODO: wangc@2017/6/6  路径混乱，jtl、log、html的路径，都是给机器看的，使用随机或uuid即可。 
        String jtlPath = new StringBuilder("")
                .append(StringUtils.creAndGetDir(Param.USER_DIR + Param.JTL_PATH))
                .append(File.separator)
                .append(System.currentTimeMillis())
                .append(Param.SEPARATOR_MY)
                .append(StringUtils.getDate(FORMAT_1))
                .append(Param.JTL_SUFFIX).toString();

        String logPath = new StringBuilder("")
                .append(StringUtils.creAndGetDir(Param.USER_DIR + Param.LOG_PATH))
                .append(File.separator)
                .append(System.currentTimeMillis())
                .append(Param.SEPARATOR_MY)
                .append(StringUtils.getDate(FORMAT_1))
                .append(Param.LOG_SUFFIX)
                .toString();

        System.out.println("======"+System.currentTimeMillis());
        String htmlPath = new StringBuilder("")
                .append(StringUtils.creAndGetDir(Param.USER_DIR + Param.HTML_PATH))
                .append(File.separator)
                .append(System.currentTimeMillis())
                .append(Param.SEPARATOR_MY)
                .append(StringUtils.getDate(FORMAT_1))
                .toString();
        rpb.setJtlPath(jtlPath);
        rpb.setLogPath(logPath);
        rpb.setHtmlPath(htmlPath+File.separator+"index.html");
        String myCmd = JMETER_COMMOND.replace("${jmx}", jmxPath)
                .replace("${jtl}", Param.USER_DIR + Param.JTL_PATH+jtlPath)
                .replace("${log}", Param.USER_DIR + Param.LOG_PATH+logPath)
                .replace("${html}", Param.USER_DIR + Param.HTML_PATH+htmlPath);


        Runtime runtime = Runtime.getRuntime();
        try {
            // TODO: wangc@2017/3/14  执行exec方法改成异步 

            if( os.contains("Linux") ){  //linux  todo 从初始化中读取
                runtime.exec(getCommandStrLinux(myCmd));
            }else if(os.contains("Windows")){
                runtime.exec(getCommandStrWin(myCmd));
            }

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error!");
        }
    }

    private static String getCommandStrLinux(String myCmd){
        myCmd = System.getenv("JMETER_HOME")+File.separator+"bin"+File.separator+myCmd;
        logger.info(myCmd );
        return myCmd;
    }

    private static String[] getCommandStrWin(String myCmd) {
        JMETER_RUN_COMMOND[0] = WIN_COMMOND;
        JMETER_RUN_COMMOND[1] = WIN_COMMOND_PARAM;

        JMETER_RUN_COMMOND[2] = myCmd;
        logger.info(Arrays.toString(JMETER_RUN_COMMOND) );
        return JMETER_RUN_COMMOND;
    }

    private static String getName(String jmxPath) {
        File file = new File(jmxPath);
        return file.getName().split("\\.")[0];
    }


    public static void main(String[] args){
        System.out.println( System.getenv("JAVA_HOME") );
        System.out.println( System.getenv("JMETER_HOME") );
        System.out.println( System.getenv("PATH") );

        /*Map<String, String> map = System.getenv();
        for(Iterator<String> itr = map.keySet().iterator();itr.hasNext();){
            String key = itr.next();
            System.out.println(key + "=" + map.get(key));
        }*/
        
        
        if(true){
            return ;
        }
        
        //[cmd, /c, 
        String a = "cmd.exe /c  jmeter -n -t D:\\workspace_HelloWorld\\testing_platform\\jmeter\\jmx\\2017\\08\\tp_20170817173811my0.jmx -l D:\\workspace_HelloWorld\\testing_platform\\jmeter\\jtl\\2017\\08\\1502962711107_17173831107.jtl -j D:\\workspace_HelloWorld\\testing_platform\\jmeter\\log\\2017\\08\\1502962711107_17173831107.log -e -o D:\\workspace_HelloWorld\\testing_platform\\jmeter\\html\\2017\\08\\1502962711108_17173831108";
        a = "/usr/local/apache-jmeter-3.2/bin/jmeter -n -t /home/wangchao/testing_platform/jmeter/jmx/2017/08/tp_20170823121605my0.jmx -l /home/wangchao/testing_platform/jmeter/jtl/2017/08/1503467216724_23134656724.jtl -j /home/wangchao/testing_platform/jmeter/log/2017/08/1503467216724_23134656724.log ";
        
        String[] aa = new String[]{"/bin/sh","-c",a};
        Runtime runtime = Runtime.getRuntime();
        try {
            // TODO: wangc@2017/3/14  执行exec方法改成异步 
            runtime.exec(a);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error!");
        }
    }

}
