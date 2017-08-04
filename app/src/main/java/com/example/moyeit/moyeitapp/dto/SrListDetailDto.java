package com.example.moyeit.moyeitapp.dto;

/**
 * Created by ga0 on 2017-08-03.
 */

public class SrListDetailDto {
    int sid;
    String nickname;
    String title;
    int limitnum;
    int contnum;
    String join;

    public int getSid() {
        return sid;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }

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

    public String getJoin() {
        return join;
    }

    public void setJoin(String join) {
        this.join = join;
    }
}
