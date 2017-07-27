package com.example.moyeit.moyeitapp.dto;

/**
 * Created by yeeun on 2017-07-23.
 */

public class UserDto {
    private static UserDto userDto= new UserDto();
    public static UserDto getInstance(){
        return userDto;
    }

    int pid;
    String email;
    String nickname;
    String  pwd;
    int region;
    String enjoy;
    String state;

    public void setState(String state) {this.state= state;}

    public int getPid() {
        return pid;
    }

    public String getState(){return state;}

    public void setPid(int pid) {
        this.pid = pid;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public int getRegion() {
        return region;
    }

    public void setRegion(Integer region) {
        this.region = region;
    }

    public String getEnjoy() {
        return enjoy;
    }

    public void setEnjoy(String enjoy) {
        this.enjoy = enjoy;
    }


}
