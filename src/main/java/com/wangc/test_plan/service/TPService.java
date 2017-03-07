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

//    @Autowired
//    TestPlanBean tp;
    
    public void insert(TestPlanBean tp){
        tpMapper.insert(tp);
    }
    
    public List<TestPlanBean> select(TestPlanBean tp){
        return tpMapper.select(tp);
    }
    
    public List<TestPlanBean> selectAll() {
        TestPlanBean tp = new TestPlanBean();
        return tpMapper.select(tp);
    }

    public List<TestPlanBean> selectById(String id){
        TestPlanBean tp = new TestPlanBean();
        tp.setId(id);
        return tpMapper.select(tp);
    }

    public void update(TestPlanBean tp){
        tpMapper.update(tp);
    }
    
}
