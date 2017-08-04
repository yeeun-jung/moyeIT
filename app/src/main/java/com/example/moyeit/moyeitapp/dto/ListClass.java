package com.example.moyeit.moyeitapp.dto;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by Home on 2017-07-27.
 */

public class ListClass {
//    @SerializedName("list")
//    Study st;
//
//    @SerializedName("state")
//    String state;
//
//    public String getState() {
//        return state;
//    }
//
//    public void setState(String state){
//        this.state = state;
//    }

    String state;
    ArrayList<MyStudyDto> list;

    public String getState() {
        return state;
    }

    public void setState(String state){
        this.state = state;
    }

    public ArrayList<MyStudyDto> getList() {
        return list;
    }

    public void setList(ArrayList<MyStudyDto> list) {
        this.list = list;
    }
}
