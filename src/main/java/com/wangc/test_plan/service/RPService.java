package com.wangc.test_plan.service;

import com.wangc.test_plan.bean.RunPlanBean;
import com.wangc.test_plan.mapper.RPMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by wangchao on 2017/3/10.
 */


@Service
public class RPService {
    
    @Autowired
    RPMapper rpMapper;
    
    public void insert(RunPlanBean rpb){
        // TODO: wangc@2017/3/13  插入数据库 
        rpMapper.insertSelective(rpb);
    }
    
    public List<RunPlanBean> list(String tId){
        // TODO: wangc@2017/3/14  查询 run_plan 表
        RunPlanBean rpb = new RunPlanBean();
        rpb.setTpId(tId);
        return rpMapper.select(rpb);
    }
    
    public String runlogList(){
        //todo 读取日志文件，并返回
        return null;
    }
    
}
