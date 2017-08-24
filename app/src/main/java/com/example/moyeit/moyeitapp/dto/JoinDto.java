package com.example.moyeit.moyeitapp.dto;

/**
 * Created by alice on 2017-08-14.
 */

public class JoinDto {
    private int pid;
    private int sid;
    private String content;
    private String state;

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public int getSid() {
        return sid;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
