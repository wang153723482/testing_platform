package com.wangc.comm;

import java.io.File;

/**
 * Created by wangchao on 2017/3/9.
 */
public class Param {

    // TODO: wangc@2017/3/14  读取配置 tp.jmeter.jtl.path
    public static String JTL_PATH = File.separator + "jmeter" + File.separator + "jtl";
    public static String LOG_PATH = File.separator + "jmeter" + File.separator + "log";
    public static String HTML_PATH = File.separator + "jmeter" + File.separator + "html";
    public static String JMX_PATH = File.separator + "jmeter" + File.separator + "jmx";
    public static String DATA_PATH = File.separator + "jmeter" + File.separator + "data";
    public static String JTL_SUFFIX = ".jtl";
    public static String LOG_SUFFIX = ".log";
    public static String SEPARATOR_MY = "_";
    public static String USER_DIR = System.getProperty("user.dir");
    public static String UPLOAD_DIR= "upload";
//    public static String UPLOAD_JMX_PATH = File.separator+UPLOAD_DIR+File.separator+"jmx";
    
    


}
