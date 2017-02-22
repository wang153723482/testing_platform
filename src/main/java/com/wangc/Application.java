package com.wangc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by wangchao on 2017/2/10.
 */

@SpringBootApplication

@RestController
public class Application {
    
    
    
    @RequestMapping(value = "/test")
    public String test(){
        System.out.println(11111);
        
        return "xxxxxxxx";
    }
    
    public static void main(String[] args){
        SpringApplication.run(Application.class,args);
    }
}
