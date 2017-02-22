package com.wangc.test_plan.bean;

import org.springframework.context.annotation.Bean;

/**
 * Created by wangchao on 2017/2/10.
 * 测试计划名称、压测地址url、测试说明、测试数据生成类
 */
public class TestPlanBean {
    private String  tpName;
    private String url;
    private String description;
    private String generater;

    public String getTpName() {
        return tpName;
    }

    public void setTpName(String tpName) {
        this.tpName = tpName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getGenerater() {
        return generater;
    }

    public void setGenerater(String generater) {
        this.generater = generater;
    }
}
