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
        if(null==testPlanBean){
            this.testPlanBean = new TestPlanBean(); 
        }else {
            this.testPlanBean = testPlanBean;
        }
    }
    
    
    //设置默认的duration，默认是每秒启动100个用户 
    // TODO: wangc@2017/3/13  参数化 
    public void setDefaultDuration(){
        int d = Integer.valueOf(this.usersNum)/100;
        this.duration = String.valueOf(0==d?1:d);
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
