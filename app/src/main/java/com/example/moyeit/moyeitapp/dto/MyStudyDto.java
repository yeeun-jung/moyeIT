package com.example.moyeit.moyeitapp.dto;

/**
 * Created by ga0 on 2017-07-25.
 */

public class MyStudyDto {

    String title;
    String nickname;
    int limitnum;
    int contnum;
    String region;
    int sid;


    public MyStudyDto() {

    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setLimitnum(int limitnum) {
        this.limitnum = limitnum;
    }

    public void setContnum(int contnum) {
        this.contnum = contnum;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }

    public String getTitle() {

        return title;
    }

    public String getNickname() {
        return nickname;
    }

    public int getLimitnum() {
        return limitnum;
    }

    public int getContnum() {
        return contnum;
    }

    public String getRegion() {
        return region;
    }

    public int getSid() {
        return sid;
    }
}
