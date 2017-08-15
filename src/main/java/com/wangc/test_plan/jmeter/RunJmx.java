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
        Runtime runtime = Runtime.getRuntime();
        try {
            // TODO: wangc@2017/3/14  执行exec方法改成异步 
            runtime.exec(getCommandStr(rpb));
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error!");
        }
    }

    /* jtlPath/logPaht/htmlPath 都是基于项目的相对路径 */
    private static String[] getCommandStr(RunPlanBean rpb) {
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
        // TODO: wangc@2017/3/14  可能需要判断OS来执行不同的命令
        
        if( os.contains("Linux") ){  //linux  todo 从初始化中读取
            JMETER_RUN_COMMOND[0] = LINUX_COMMOND;
            JMETER_RUN_COMMOND[1] = LINUX_COMMOND_PARAM;
        }else if(os.contains("Windows")){
            JMETER_RUN_COMMOND[0] = WIN_COMMOND;
            JMETER_RUN_COMMOND[1] = WIN_COMMOND_PARAM;
        }

        String myCmd = JMETER_COMMOND.replace("${jmx}", jmxPath)
                .replace("${jtl}", Param.USER_DIR + Param.JTL_PATH+jtlPath)
                .replace("${log}", Param.USER_DIR + Param.LOG_PATH+logPath)
                .replace("${html}", Param.USER_DIR + Param.HTML_PATH+htmlPath);

        JMETER_RUN_COMMOND[2] = myCmd;
        logger.info(Arrays.toString(JMETER_RUN_COMMOND) );
        return JMETER_RUN_COMMOND;
    }

    private static String getName(String jmxPath) {
        File file = new File(jmxPath);
        return file.getName().split("\\.")[0];
    }

}
