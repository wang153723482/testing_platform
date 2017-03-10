package com.wangc.comm;

import com.wangc.test_plan.bean.RunPlanBean;
import com.wangc.test_plan.bean.TestPlanBean;
import com.wangc.test_plan.controler.TPController;
import org.beetl.core.Configuration;
import org.beetl.core.GroupTemplate;
import org.beetl.core.Template;
import org.beetl.core.resource.FileResourceLoader;
import org.testng.annotations.Test;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by wangchao on 2017/3/9.
 */
public class GenerateJmx {
    
    
    public static void main(String[] args){
        new GenerateJmx().generate(null);
    }
    
    public  void generate(RunPlanBean rpb){
        try{
            String root = TPController.class.getClassLoader().getResource("").getPath()+"beetl";
            root = "D:\\workspace_HelloWorld\\testing_platform\\src\\main\\resources\\beetl";
            System.out.println(root);

            FileResourceLoader resourceLoader = new FileResourceLoader(root,"utf-8");
            Configuration cfg = Configuration.defaultConfiguration();
            GroupTemplate gt = new GroupTemplate(resourceLoader, cfg);
            Template t = gt.getTemplate("/http_request.jmx");
            
            t.binding("v_duration",rpb.getDuration());
            t.binding("v_users_num",rpb.getUsersNum());
            t.binding("v_ramp_up",rpb.getRampUp());
            t.binding("v_server_name",rpb.getTestPlanBean().getServerName());
            t.binding("v_port",rpb.getTestPlanBean().getPortNum());
            t.binding("v_prol",rpb.getTestPlanBean().getProtocol());
            t.binding("v_path",rpb.getTestPlanBean().getPath());
            
            
            String str = t.render();

            OutputStream ops = new FileOutputStream("ss.jmx") ;
            t.renderTo(ops);
            
            
            System.out.println(str);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
