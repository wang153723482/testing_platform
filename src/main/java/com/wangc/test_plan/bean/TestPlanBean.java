package com.wangc.test_plan.bean;

import org.springframework.context.annotation.Bean;

/**
 * Created by wangchao on 2017/2/10.
 * 测试计划名称、压测地址url、测试说明、测试数据生成类
 */
public class TestPlanBean {
    private String id;
    private String tpName;
    private String url;
    private String description;
    private String generater;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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

    @Override
    public String toString() {
        return new StringBuilder("TestPlanBean:{")
                .append("'id':'")
                .append(id)
                .append("','tpName':'")
                .append(tpName)
                .append("','url':'")
                .append(url)
                .append("','description':'")
                .append(description)
                .append("','generater':'")
                .append(generater)
                .append("'}")
                .toString();
    }
}
