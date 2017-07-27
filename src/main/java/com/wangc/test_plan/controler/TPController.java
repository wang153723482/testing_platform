package com.wangc.test_plan.controler;

import com.wangc.test_plan.bean.TestPlanBean;
import com.wangc.test_plan.service.TPService;
import org.beetl.core.Configuration;
import org.beetl.core.GroupTemplate;
import org.beetl.core.Template;
import org.beetl.core.resource.FileResourceLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Created by wangchao on 2017/2/10.
 */
@Controller
@RequestMapping(value = "/tp")
public class TPController {
    
    @Autowired
    TPService tpService;
    
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public String list(Model model,@ModelAttribute TestPlanBean tp){
        System.out.println("==========="+tp.getTpName());
        
        List<TestPlanBean> list = tpService.select(null);
        model.addAttribute("tp_list",list);

        return "test_plan/list";
    }
    
    @RequestMapping(value = "/add",method = RequestMethod.GET)
    public String add(Model model){
        return "test_plan/add";
    }
    
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public String add(Model model, TestPlanBean tp){
        tpService.insert(tp);
//        
//        return "/test_plan/add";
        return "redirect:/tp/list?sName=qwe";
    }

    @RequestMapping(value = "/modify",method = RequestMethod.GET)
    public String modify(Model model,@RequestParam String id){
        model.addAttribute( "tp",tpService.selectById(id));
        return "test_plan/modify";
    }

    @RequestMapping(value = "/modify",method = RequestMethod.POST)
    public String modify(Model model,@ModelAttribute TestPlanBean tp){
        System.out.println(tp);
        tpService.update(tp);
        System.out.println("====================================ok");
        return "redirect:/tp/list";
    }
    
}
