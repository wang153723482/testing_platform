package com.wangc;

import com.wangc.comm.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.io.File;

/**
 * Created by wangchao on 2017/3/15.
 */

@Configuration
public class StaticResourceConfiguration extends WebMvcConfigurerAdapter {
    
    private final String FILE_PREX = "file://";
    private static String os = System.getProperty("os.name");

    private static final Logger logger = LoggerFactory.getLogger(StaticResourceConfiguration.class);


    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // TODO: wangc@2017/3/15  这里可能需要根据OS不同配置不同的路径，win：file后有3个/，linux：file后有2个/，因为linux的目录本身就是/开头 


        String separator = "/";
        if( os.contains("Linux") ){//如果是linux    // TODO: wangc@2017/3/15  app_init 中判断OS 
            separator = "";
        }
        //win:   file:///c:dir_name
        //linux:  file:///home/wangc/dir_name
//        String reportResLocations = FILE_PREX+separator+ Param.USER_DIR+Param.HTML_PATH;
        //将所有的/report/请求至html报告的本地路径
//        registry.addResourceHandler("/report/**").addResourceLocations(reportResLocations);

        String reportResLocations = FILE_PREX+separator+ Param.USER_DIR+Param.HTML_PATH+File.separator;
        
//        reportResLocations = "file:///D:/workspace_HelloWorld/testing_platform/jmeter/html/";
        registry.addResourceHandler("/report/**").addResourceLocations(reportResLocations);
                              //file:///D:/workspace_HelloWorld/testing_platform/jmeter/html
        super.addResourceHandlers(registry);
    }
}
