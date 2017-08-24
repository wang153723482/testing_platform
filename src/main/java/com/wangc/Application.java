package com.wangc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.net.UnknownHostException;

/**
 * Created by wangchao on 2017/2/10.
 */

@SpringBootApplication

@Controller
public class Application {

    private static final Logger logger = LoggerFactory.getLogger(Application.class);
    
    @RequestMapping(value = "/",method = RequestMethod.GET)
    public String test(){
        return "index";
    }
    
    public static void main(String[] args) throws UnknownHostException {
        SpringApplication.run(Application.class,args);
    }
}
