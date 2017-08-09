package com.example.moyeit.moyeitapp.dto;

import java.util.ArrayList;

/**
 * Created by alice on 2017-07-25.
 */

public class MoimDto {

    private String moimtitle;
    private int limitnum;
    private int agrnum;
    private int disnum;
    private String content;
    private int no;
    private int pid;
    private int sid;
    private int mid;
    private String muser;
    private String date;
    public String state;
    ArrayList<CmoimDto> list;


    public String getMoimtitle() {
        return moimtitle;
    }

    public void setMoimtitle(String moimtitle) {
        this.moimtitle = moimtitle;
    }

    public int getAgrnum() {
        return agrnum;
    }

    public void setAgrnum(int agrnum) {
        this.agrnum = agrnum;
    }

    public int getDisnum() {
        return disnum;
    }

    public void setDisnum(int disnum) {
        this.disnum = disnum;
    }

    public int getLimitnum() {
        return limitnum;
    }

    public void setLimitnum(int limitnum) {
        this.limitnum = limitnum;
    }

    public int getMid() {
        return mid;
    }

    public void setMid(int mid) {
        this.mid = mid;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

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

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getMuser() {
        return muser;
    }

    public void setMuser(String muser) {
        this.muser = muser;
    }

    public ArrayList<CmoimDto> getList() {
        return list;
    }

    public void setList(ArrayList<CmoimDto> list) {
        this.list = list;
    }
}
