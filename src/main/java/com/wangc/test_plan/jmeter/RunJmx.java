package com.wangc.test_plan.jmeter;

import com.wangc.comm.Param;
import com.wangc.comm.StringUtils;
import com.wangc.test_plan.bean.GlobalConfig;
import com.wangc.test_plan.bean.RunPlanBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.Arrays;

/**
 * Created by wangchao on 2017/3/13.
 * 运行jmx脚本
 */
public class RunJmx {

    private static String FORMAT_1 = "ddHHmmssSSS";
    private static String JMETER_COMMOND = "jmeter -n -t ${jmx} -l ${jtl} -j ${log} -e -o ${html}";

    private static String[] JMETER_RUN_COMMOND = new String[]{"", "", ""};
    private static String WIN_COMMOND = "cmd";
    private static String WIN_COMMOND_PARAM = "/c";

    private static final Logger logger = LoggerFactory.getLogger(RunJmx.class);

    public static void run(RunPlanBean rpb) {
        String jmxPath = Param.USER_DIR + rpb.getTestPlanBean().getJmxSavePath();
        // TODO: wangc@2017/6/6  路径混乱，jtl、log、html的路径，都是给机器看的，使用随机或uuid即可。 
        String jtlPath =
                StringUtils.creAndGetDir(Param.USER_DIR + Param.JTL_PATH) +
                        File.separator +
                        System.currentTimeMillis() +
                        Param.SEPARATOR_MY +
                        StringUtils.getDate(FORMAT_1) +
                        Param.JTL_SUFFIX;

        String logPath =
                StringUtils.creAndGetDir(Param.USER_DIR + Param.LOG_PATH) +
                        File.separator +
                        System.currentTimeMillis() +
                        Param.SEPARATOR_MY +
                        StringUtils.getDate(FORMAT_1) +
                        Param.LOG_SUFFIX;

        String htmlPath =
                StringUtils.creAndGetDir(Param.USER_DIR + Param.HTML_PATH) +
                        File.separator +
                        System.currentTimeMillis() +
                        Param.SEPARATOR_MY +
                        StringUtils.getDate(FORMAT_1);
        rpb.setJtlPath(jtlPath);
        rpb.setLogPath(logPath);
        rpb.setHtmlPath(htmlPath + File.separator + "index.html");
        String myCmd = JMETER_COMMOND.replace("${jmx}", jmxPath)
                        .replace("${jtl}", Param.USER_DIR + Param.JTL_PATH + jtlPath)
                        .replace("${log}", Param.USER_DIR + Param.LOG_PATH + logPath)
                        .replace("${html}", Param.USER_DIR + Param.HTML_PATH + htmlPath);

        logger.info(myCmd);
        Runtime runtime = Runtime.getRuntime();
        try {
            // TODO: wangc@2017/3/14  执行exec方法改成异步 
            if (GlobalConfig.getInstance().getOsFlag()) {
                runtime.exec(getCommandStrWin(myCmd));
            } else {
                runtime.exec(getCommandStrLinux(myCmd));
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error!");
        }
    }


    private static String getCommandStrLinux(String myCmd) {
        myCmd = GlobalConfig.getInstance().getJmeterHome() +
                File.separator +
                "bin" +
                File.separator +
                myCmd;
        logger.info(myCmd);
        return myCmd;
    }


    private static String[] getCommandStrWin(String myCmd) {
        JMETER_RUN_COMMOND[0] = WIN_COMMOND;
        JMETER_RUN_COMMOND[1] = WIN_COMMOND_PARAM;

        JMETER_RUN_COMMOND[2] = myCmd;
        logger.info(Arrays.toString(JMETER_RUN_COMMOND) );
        return JMETER_RUN_COMMOND;
    }

}
