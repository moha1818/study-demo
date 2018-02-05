package com.moha.demo.entity;

public class CheckEmailValidator {
    private String email;
    /**
     * 0-无效 1-有效
     */
    private int status=1;
    private String replyString;
    private String createTime;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getReplyString() {
        return replyString;
    }

    public void setReplyString(String replyString) {
        this.replyString = replyString;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
}
