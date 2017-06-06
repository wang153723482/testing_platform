package com.wangc.test_plan.controler;

import com.wangc.comm.StringUtils;
import com.wangc.test_plan.bean.TestPlanBean;
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


    /*
    * 添加一个执行计划。
    * 先判断该执行计划中是否已经生成了jmx，如果生成则执行 runJmx() ，否则，先生成jmx，然后再 runJmx()
    * */
    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String add(Model model, @ModelAttribute RunPlanBean rpb) {
        rpb.setDefaultRampUp();

        System.out.println(rpb);

        TestPlanBean tpb = tpService.selectById(rpb.getTpId());
        rpb.setTestPlanBean(tpb);
        rpb.setJmxPath(tpb.getJmxSavePath());

        if (!StringUtils.isEmpty(tpb.getJmxSavePath())) {
            GenerateJmx.generate(rpb);
        }
        RunJmx.run(rpb);

        rpService.insert(rpb);

        return "redirect:/tp/list";
    }

    /**
     * 根据test_plan id 查询当前计划的所有执行日志
     *
     * @param tpId test_plan.id
     * @return
     */
    @RequestMapping(value = "list", method = RequestMethod.GET)
    public String list(Model model, @RequestParam String tpId) {
        List<RunPlanBean> list = rpService.list(tpId);
        model.addAttribute("rp_list", list);
        return "/run_plan/list";
    }

}
