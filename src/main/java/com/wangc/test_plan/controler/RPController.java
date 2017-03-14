package com.wangc.test_plan.controler;

import com.wangc.test_plan.jmeter.GenerateJmx;
import com.wangc.test_plan.bean.RunPlanBean;
import com.wangc.test_plan.jmeter.RunJmx;
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

    @Value("${tp.jmeter.jmx.path}")
    String a;
    
    @RequestMapping("add")
    public String add(Model model, @ModelAttribute RunPlanBean rpb){
        rpb.setDefaultRampUp();
        
        System.out.println(rpb);
        
        rpService.insert(rpb);

        rpb.setTestPlanBean( tpService.selectById(rpb.gettId()) );
        GenerateJmx.generate(rpb);

        RunJmx.run(rpb.getJmxPath());
        
        return "redirect:/tp/list";
    }
    
}
