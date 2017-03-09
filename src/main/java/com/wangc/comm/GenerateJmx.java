package com.wangc.comm;

import com.wangc.test_plan.bean.TestPlanBean;
import com.wangc.test_plan.controler.TPController;
import org.beetl.core.Configuration;
import org.beetl.core.GroupTemplate;
import org.beetl.core.Template;
import org.beetl.core.resource.FileResourceLoader;

/**
 * Created by wangchao on 2017/3/9.
 */
public class GenerateJmx {
    public void generate(TestPlanBean tpb){
        try{
            String root = TPController.class.getClassLoader().getResource("").getPath()+"beetl";
            System.out.println(root);

            FileResourceLoader resourceLoader = new FileResourceLoader(root,"utf-8");
            Configuration cfg = Configuration.defaultConfiguration();
            GroupTemplate gt = new GroupTemplate(resourceLoader, cfg);
            Template t = gt.getTemplate("/http_request.bl");
            
            t.binding("name","wangchao");
            
            String str = t.render();
            System.out.println(str);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
