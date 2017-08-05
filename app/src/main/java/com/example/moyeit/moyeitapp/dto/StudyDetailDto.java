package com.example.moyeit.moyeitapp.dto;

/**
 * Created by Home on 2017-07-29.
 */

public class StudyDetailDto {
    String nickname;
    String title;
    String detail;
    String state;
    int region;
    int limitnum;
    int contnum;

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

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getRegion() {
        return region;
    }

    public void setRegion(int region) {
        this.region = region;
    }

    public int getLimitnum() {
        return limitnum;
    }

    public void setLimitnum(int limitnum) {
        this.limitnum = limitnum;
    }

    public int getContnum() {
        return contnum;
    }

    public void setContnum(int contnum) {
        this.contnum = contnum;
    }
}
