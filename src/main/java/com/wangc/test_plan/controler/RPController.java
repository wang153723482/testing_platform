package com.wangc.test_plan.controler;

import com.wangc.test_plan.bean.RunPlanBean;
import com.wangc.test_plan.service.RPService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by wangchao on 2017/3/10.
 */

@Controller
@RequestMapping("/rp")
public class RPController {
    
    @Autowired
    RPService rpService;
    
    @RequestMapping("add")
    public String add(Model model, @ModelAttribute RunPlanBean rpb){
        System.out.println(rpb);
        
        rpService.insert(rpb);
        
        
        
        return "redirect:/tp/list";
    }
    
}
