package com.wangc.test_plan.controler;

import com.wangc.comm.GenerateJmx;
import com.wangc.test_plan.bean.RunPlanBean;
import com.wangc.test_plan.service.RPService;
import com.wangc.test_plan.service.TPService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
    @Autowired
    TPService tpService;

    @Value("${tp.resultJmxPath}")
    String a;
    
    @RequestMapping("add")
    public String add(Model model, @ModelAttribute RunPlanBean rpb){
        rpb.setDefaultDuration();

        rpb.setTestPlanBean( tpService.selectById(rpb.gettId()) );
        GenerateJmx.generate(rpb);
        
        System.out.println(rpb);
        
        rpService.insert(rpb);
        return "redirect:/tp/list";
    }
    
}
