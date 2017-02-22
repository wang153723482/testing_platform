package com.wangc.test_plan.controler;

import com.wangc.test_plan.bean.TestPlanBean;
import com.wangc.test_plan.service.TPService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
    public String list(Model model,@RequestParam String sName,@ModelAttribute TestPlanBean tp){
        System.out.println("========"+sName);
        System.out.println("==========="+tp.getTpName());
        
        
        List list = tpService.select(null);
        model.addAttribute("tp_list",list);
//        
        return "/test_plan/list";
    }
    
    @RequestMapping(value = "/add",method = RequestMethod.GET)
    public String add(Model model){
        
        return "/test_plan/add";
    }
    
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public String add(Model model, TestPlanBean tp){

        tpService.insert(tp);
        System.out.println(tp.getTpName());
//        
//        return "/test_plan/add";
        return "redirect:/tp/list?sName=qwe";
    }
    
    
    
}
