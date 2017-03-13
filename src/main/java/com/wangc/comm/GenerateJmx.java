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
    
    public static void generate(RunPlanBean rpb){
        try{
            System.out.println("==222=====");
            System.out.println(rpb.toString());
            
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
            TestPlanBean tpb = rpb.getTestPlanBean(); // TODO: wangc@2017/3/13  不够优雅，改成解析成map，直接binding map 
            if(null!=tpb){
                System.out.println("===3===");
                t.binding("v_server_name",tpb.getServerNameIp());
                t.binding("v_port",tpb.getPortNum());
                t.binding("v_prol",tpb.getProtocol());
                t.binding("v_path",tpb.getPath());
            }else{
                t.binding("v_server_name","111");
                t.binding("v_port","222");
                t.binding("v_prol","333");
                t.binding("v_path","444");
            }
            
            // TODO: wangc@2017/3/13  改成更优雅的存放目录结构 
            // TODO: wangc@2017/3/13  改成自动判断目标目录是否存在 
            String path = "ss"+System.currentTimeMillis()+".jmx";
            
            OutputStream ops = new FileOutputStream(path) ;
            t.renderTo(ops);
            
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
