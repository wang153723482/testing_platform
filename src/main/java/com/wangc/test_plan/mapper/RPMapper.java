package com.wangc.test_plan.mapper;

import com.wangc.test_plan.bean.RunPlanBean;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Created by wangchao on 2017/3/10.
 */
@Mapper
public interface RPMapper {
    
    
    List<RunPlanBean> select(RunPlanBean rpb);
    
    
    
    int deleteByPrimaryKey(Integer id);

    int insert(RunPlanBean record);

    int insertSelective(RunPlanBean record);

    RunPlanBean selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(RunPlanBean record);

    int updateByPrimaryKey(RunPlanBean record);
}
