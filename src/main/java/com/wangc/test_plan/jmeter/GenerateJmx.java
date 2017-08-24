package com.wangc.test_plan.jmeter;

import com.wangc.comm.StringUtils;
import com.wangc.test_plan.bean.RunPlanBean;
import com.wangc.test_plan.bean.TestPlanBean;
import com.wangc.test_plan.controler.TPController;
import org.beetl.core.Configuration;
import org.beetl.core.GroupTemplate;
import org.beetl.core.Template;
import org.beetl.core.resource.FileResourceLoader;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

/**
 * Created by wangchao on 2017/3/9.
 */
@Component
public class GenerateJmx {

    // TODO: wangc@2017/3/13  读取配置 tp.jmeter.jmx .path
    private static String JMX_PATH = File.separator+"jmeter"+File.separator+"jmx";
    private static String JMX_NAME_PREFIX = "tp_";
    private static String JMX_NAME_SUFFIX = ".jmx";


    public static void main(String[] args) {
        new GenerateJmx().generate(null);
    }

    public static void generate(RunPlanBean rpb) {
        try {
            System.out.println(rpb.toString());

            String root = TPController.class.getClassLoader().getResource("").getPath() + "beetl";

            FileResourceLoader resourceLoader = new FileResourceLoader(root, "utf-8");
            Configuration cfg = Configuration.defaultConfiguration();
            GroupTemplate gt = new GroupTemplate(resourceLoader, cfg);
            Template t = gt.getTemplate("/http_request.jmx");

            t.binding("v_duration", rpb.getDuration());
            t.binding("v_users_num", rpb.getUsersNum());
            t.binding("v_ramp_up", rpb.getRampUp());
            TestPlanBean tpb = rpb.getTestPlanBean(); // TODO: wangc@2017/3/13  不够优雅，改成解析成map，直接binding map 
            if (null != tpb) {
                t.binding("v_server_name", tpb.getServerNameIp());
                t.binding("v_port", tpb.getPortNum());
                t.binding("v_prol", tpb.getProtocol());
                t.binding("v_path", tpb.getPath());
            } else {
                t.binding("v_server_name", "111");
                t.binding("v_port", "222");
                t.binding("v_prol", "333");
                t.binding("v_path", "444");
            }

            String jmxRoot = System.getProperty("user.dir")+JMX_PATH;//系统(项目)路径，结尾无斜杠
            //jmx在系统(项目)中的存在路径，斜杠开头，结尾无斜杠
            
            String fullPath = jmxRoot+StringUtils.creAndGetDir(jmxRoot); //返回的应该是  c:/jmx/2017/03  ，并创建这个目录
            
            //文件名，斜杠开头
            String fileName = new StringBuilder(File.separator)
                    .append(JMX_NAME_PREFIX)
                    .append(StringUtils.getDate("yyyyMMddHHmmssSSS"))
                    .append(JMX_NAME_SUFFIX)
                    .toString();

            String jmxFilePath = fullPath+fileName;
            rpb.setJmxPath(jmxFilePath);
            OutputStream ops = new FileOutputStream(jmxFilePath);
            t.renderTo(ops);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
