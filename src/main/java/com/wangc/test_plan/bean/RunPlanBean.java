package com.wangc.test_plan.bean;

/**
 * Created by wangchao on 2017/3/10.
 */
public class RunPlanBean {
    private String id;
    private String duration;
    public String usersNum;
    private String rampUp;
    private String tpId;
    private TestPlanBean testPlanBean;
    private String jmxPath;
    private String jtlPath;
    private String logPath;
    private String htmlPath;
    private String createTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getUsersNum() {
        return usersNum;
    }

    public void setUsersNum(String usersNum) {
        this.usersNum = usersNum;
    }

    public String getRampUp() {
        return rampUp;
    }

    public void setRampUp(String rampUp) {
        this.rampUp = rampUp;
    }

    public String getTpId() {
        return tpId;
    }

    public void setTpId(String tpId) {
        this.tpId = tpId;
    }

    public TestPlanBean getTestPlanBean() {
        return testPlanBean;
    }

    public void setTestPlanBean(TestPlanBean testPlanBean) {
        if (null == testPlanBean) {
            this.testPlanBean = new TestPlanBean();
        } else {
            this.testPlanBean = testPlanBean;
        }
    }

    public String getJmxPath() {
        return jmxPath;
    }

    public void setJmxPath(String jmxPath) {
        this.jmxPath = jmxPath;
    }

    //设置默认的启动频率，默认是每秒启动100个用户 
    // TODO: wangc@2017/3/13  参数化
    public void setDefaultRampUp() {
        int d = Integer.valueOf(this.usersNum) / 100;
        this.rampUp = String.valueOf(0 == d ? 1 : d);
    }

    public String getJtlPath() {
        return jtlPath;
    }

    public void setJtlPath(String jtlPath) {
        this.jtlPath = jtlPath;
    }

    public String getLogPath() {
        return logPath;
    }

    public void setLogPath(String logPath) {
        this.logPath = logPath;
    }

    public String getHtmlPath() {
        return htmlPath;
    }

    public void setHtmlPath(String htmlPath) {
        this.htmlPath = htmlPath;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("RunPlanBean{");
        sb.append("id='").append(id).append('\'');
        sb.append(", duration='").append(duration).append('\'');
        sb.append(", usersNum='").append(usersNum).append('\'');
        sb.append(", rampUp='").append(rampUp).append('\'');
        sb.append(", tpId='").append(tpId).append('\'');
        sb.append(", testPlanBean=").append(testPlanBean);
        sb.append(", jmxPath='").append(jmxPath).append('\'');
        sb.append(", jtlPath='").append(jtlPath).append('\'');
        sb.append(", logPath='").append(logPath).append('\'');
        sb.append(", htmlPath='").append(htmlPath).append('\'');
        sb.append(", createTime='").append(createTime).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
