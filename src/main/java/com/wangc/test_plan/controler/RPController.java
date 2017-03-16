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
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    
    @RequestMapping(value = "add",method = RequestMethod.POST)
    public String add(Model model, @ModelAttribute RunPlanBean rpb){
        rpb.setDefaultRampUp();
        
        System.out.println(rpb);
        
        rpb.setTestPlanBean( tpService.selectById(rpb.getTpId()) );
        GenerateJmx.generate(rpb);

        RunJmx.run(rpb);

        rpService.insert(rpb);
        
        return "redirect:/tp/list";
    }

    /**
     * 根据test_plan id 查询当前计划的所有执行日志 
     * @param tpId test_plan.id
     * @return
     */
    @RequestMapping(value = "list",method = RequestMethod.GET)
    public String list(Model model,@RequestParam String tpId){
        List<RunPlanBean> list = rpService.list(tpId);
        model.addAttribute("rp_list",list);
        return "/run_plan/list";
    }
    
}
