package com.wangc.test_plan.jmeter;

import com.wangc.comm.Param;
import com.wangc.comm.StringUtils;
import com.wangc.test_plan.bean.RunPlanBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

/**
 * Created by wangchao on 2017/3/13.
 * 运行jmx脚本
 */
public class RunJmx {

    private static String FORMAT_1 = "ddHHmmssSSS";
    private static String JMETER_RUN_WIN = "cmd /c jmeter -n -t ${jmx} -l ${jtl} -j ${log} -e -o ${html}";

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
    private static String getCommandStr(RunPlanBean rpb) {
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
        
        String htmlPath = new StringBuilder("")
                .append(StringUtils.creAndGetDir(Param.USER_DIR + Param.HTML_PATH))
                .append(File.separator)
                .append(System.currentTimeMillis())
                .append(Param.SEPARATOR_MY)
                .append(StringUtils.getDate(FORMAT_1))
                .toString();
        rpb.setJtlPath(jtlPath);
        rpb.setLogPath(logPath);
        rpb.setHtmlPath(htmlPath+File.separator+"index.html");  // TODO: 2017/3/20 这里用相对路径，执行cmd命令时用绝对路径，需要修改 
        // TODO: wangc@2017/3/14  可能需要判断OS来执行不同的命令
        String command = JMETER_RUN_WIN.replace("${jmx}", jmxPath)
                .replace("${jtl}", Param.USER_DIR + Param.JTL_PATH+jtlPath)
                .replace("${log}", Param.USER_DIR + Param.LOG_PATH+logPath)
                .replace("${html}", Param.USER_DIR + Param.HTML_PATH+htmlPath);
        logger.info(command);
        return command;
    }

    private static String getName(String jmxPath) {
        File file = new File(jmxPath);
        return file.getName().split("\\.")[0];
    }

}
