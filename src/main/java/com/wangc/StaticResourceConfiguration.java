package com.wangc;

import com.wangc.comm.Param;
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
    
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        super.addResourceHandlers(registry);
        // TODO: wangc@2017/3/15  这里可能需要根据OS不同配置不同的路径，win：file后有3个/，linux：file后有2个/，因为linux的目录本身就是/开头 
        
        String separator = "/";
        if(false){//如果是linux    // TODO: wangc@2017/3/15  判断OS 
            separator = "";
        }
        //win:   file:///c:dir_name
        //linux:  file:///home/wangc/dir_name
        String reportResLocations = FILE_PREX+separator+ Param.USER_DIR+Param.HTML_PATH;
        //将所有的/report/请求至html报告的本地路径
        registry.addResourceHandler("/report/**").addResourceLocations(reportResLocations);
    }
}
