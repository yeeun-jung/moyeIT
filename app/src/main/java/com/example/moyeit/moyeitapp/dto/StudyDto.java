package com.example.moyeit.moyeitapp.dto;

/**
 * Created by yeeun on 2017-07-27.
 */

public class StudyDto {
    String nickname;
    String title;
    int region;
    int limitnum;
    String detail;
    int sid;

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    String state;

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getRegion() {
        return region;
    }

    public void setRegion(int region) {
        this.region = region;
    }

    public int getLimit() {
        return limitnum;
    }

    public void setLimit(int limit) {
        this.limitnum = limit;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public int getSid() {
        return sid;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }

}
