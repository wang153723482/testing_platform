package com.wangc.test_plan.bean;

/**
 * Created by wangchao on 2017/3/10.
 */
public class RunPlanBean {
    private String id;
    private String duration;
    private String usersNum;
    private String rampUp;
    private String tId;
    private TestPlanBean testPlanBean;

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

    public String gettId() {
        return tId;
    }

    public void settId(String tId) {
        this.tId = tId;
    }

    public TestPlanBean getTestPlanBean() {
        return testPlanBean;
    }

    public void setTestPlanBean(TestPlanBean testPlanBean) {
        this.testPlanBean = testPlanBean;
    }


    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("RunPlanBean{");
        sb.append("id='").append(id).append('\'');
        sb.append(", duration='").append(duration).append('\'');
        sb.append(", usersNum='").append(usersNum).append('\'');
        sb.append(", rampUp='").append(rampUp).append('\'');
        sb.append(", tId='").append(tId).append('\'');
        sb.append(", testPlanBean=").append(testPlanBean);
        sb.append('}');
        return sb.toString();
    }
}
