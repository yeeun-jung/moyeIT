package com.example.moyeit.moyeitapp.dto;

import java.util.ArrayList;

/**
 * Created by ga0 on 2017-07-27.
 */

public class MsDetailDto {
    String state;
    String title;
    String nickname;
    int limitnum;
    int contnum;
    ArrayList<MsDetailListDto> list;

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
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



    public ArrayList<MsDetailListDto> getList() {
        return list;
    }

    public void setList(ArrayList<MsDetailListDto> list) {
        this.list = list;
    }
}
