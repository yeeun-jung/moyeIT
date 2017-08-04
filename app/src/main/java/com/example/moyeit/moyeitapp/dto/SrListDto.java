package com.example.moyeit.moyeitapp.dto;

import java.util.ArrayList;

/**
 * Created by ga0 on 2017-08-03.
 */

public class SrListDto {
    String state;
    ArrayList<SrListDetailDto> list;

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public ArrayList<SrListDetailDto> getList() {
        return list;
    }

    public void setList(ArrayList<SrListDetailDto> list) {
        this.list = list;
    }
}
