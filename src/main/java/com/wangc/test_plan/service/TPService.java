package com.wangc.test_plan.service;

import com.wangc.test_plan.bean.TestPlanBean;
import com.wangc.test_plan.mapper.TPMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.lang.Math.sqrt;

/**
 * Created by wangchao on 2017/2/10.
 */

@Service
public class TPService {
    
    @Autowired
    TPMapper tpMapper;
    
    
    public void insert(TestPlanBean tp){
        tpMapper.insert(tp);
    }
    
    public List<TestPlanBean> select(TestPlanBean tp){
        
        return tpMapper.select(tp);
        
    }
}
