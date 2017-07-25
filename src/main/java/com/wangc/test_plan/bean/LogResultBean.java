package com.wangc.test_plan.bean;

/**
 * Created by wangchao on 2017/7/25.
 */
public class LogResultBean {
    private String logPath;
    private long modifiedTime;
    private String content;

    public LogResultBean(long modifiedTime, String content) {
        this.modifiedTime = modifiedTime;
        this.content = content;
    }

    public LogResultBean(String logPath, long modifiedTime, String content) {
        this.logPath = logPath;
        this.modifiedTime = modifiedTime;
        this.content = content;
    }

    public String getLogPath() {
        return logPath;
    }

    public void setLogPath(String logPath) {
        this.logPath = logPath;
    }

    public long getModifiedTime() {
        return modifiedTime;
    }

    public void setModifiedTime(long modifiedTime) {
        this.modifiedTime = modifiedTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
