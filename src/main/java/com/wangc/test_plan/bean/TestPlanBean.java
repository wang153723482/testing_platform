package com.wangc.test_plan.bean;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Created by wangchao on 2017/2/10.
 * 测试计划名称、压测地址url、测试说明、测试数据生成类
 */

@Component
public class TestPlanBean {
    private String id;
    private String tpName;
    private String url;
    private String description;
    private String generater;
    @Value("${tp.protocol}")
     String protocol;
    private String serverNameIp;
    @Value("${tp.portNum}")
    private String portNum;
    
    private String path;
    private String jmxSavePath;
    private String csvDataXpath;


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

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public String getServerNameIp() {
        return serverNameIp;
    }

    public void setServerNameIp(String serverNameIp) {
        this.serverNameIp = serverNameIp;
    }

    public String getPortNum() {
        return portNum;
    }

    public void setPortNum(String portNum) {
        this.portNum = portNum;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getJmxSavePath() {
        return jmxSavePath;
    }

    public void setJmxSavePath(String jmxSavePath) {
        this.jmxSavePath = jmxSavePath;
    }

    public String getCsvDataXpath() {
        return csvDataXpath;
    }

    public void setCsvDataXpath(String csvDataXpath) {
        this.csvDataXpath = csvDataXpath;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("TestPlanBean{");
        sb.append("id='").append(id).append('\'');
        sb.append(", tpName='").append(tpName).append('\'');
        sb.append(", url='").append(url).append('\'');
        sb.append(", description='").append(description).append('\'');
        sb.append(", generater='").append(generater).append('\'');
        sb.append(", protocol='").append(protocol).append('\'');
        sb.append(", serverNameIp='").append(serverNameIp).append('\'');
        sb.append(", portNum='").append(portNum).append('\'');
        sb.append(", path='").append(path).append('\'');
        sb.append(", jmxSavePath='").append(jmxSavePath).append('\'');
        sb.append(", csvDataXpath='").append(csvDataXpath).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
