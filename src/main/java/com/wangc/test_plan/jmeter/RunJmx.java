package com.wangc.test_plan.jmeter;

import com.wangc.comm.StringUtils;

import java.io.File;

/**
 * Created by wangchao on 2017/3/13.
 * 运行jmx脚本
 */
public class RunJmx {

    // TODO: wangc@2017/3/14  读取配置 tp.jmeter.jtl.path
    private static String JTL_PATH = File.separator + "jmeter" + File.separator + "jtl";
    private static String LOG_PATH = File.separator + "jmeter" + File.separator + "log";
    private static String JTL_SUFFIX = ".jtl";
    private static String LOG_SUFFIX = ".log";
    private static String SEPARATOR_MY = "_";
    private static String USER_DIR = System.getProperty("user.dir");
    private static String FORMAT_1 = "ddHHmmssSSS";
    private static String JMETER_RUN_WIN = "cmd /c jmeter -n -t ${jmx} -l ${jtl} -j ${log}";


    public static void run(String jmxPath) {
        Runtime runtime = Runtime.getRuntime();
        try {
            runtime.exec(getCommandStr(jmxPath));
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error!");
        }

    }

    private static String getCommandStr(String jmxPath) {
        String jmxName = getName(jmxPath);
        String jtlPath = new StringBuilder(StringUtils.creAndGetDir(USER_DIR + JTL_PATH))
                .append(File.separator)
                .append(jmxName)
                .append(SEPARATOR_MY)
                .append(StringUtils.getDate(FORMAT_1))
                .append(JTL_SUFFIX)
                .toString();
        String logPaht = new StringBuilder(StringUtils.creAndGetDir(USER_DIR + LOG_PATH))
                .append(File.separator)
                .append(jmxName)
                .append(SEPARATOR_MY)
                .append(StringUtils.getDate(FORMAT_1))
                .append(LOG_SUFFIX)
                .toString();
        // TODO: wangc@2017/3/14  可能需要判断OS来执行不同的命令 
        return JMETER_RUN_WIN.replace("${jmx}", jmxPath).replace("${jtl}", jtlPath).replace("${log}", logPaht);
    }

    private static String getName(String jmxPath) {
        File file = new File(jmxPath);
        return file.getName().split("\\.")[0];
    }

    public static void main(String[] args) {
        System.out.println(getCommandStr("z://a.jmx"));
    }
}
