package com.example.moyeit.moyeitapp.dto;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ga0 on 2017-07-26.
 */

public class ListDto {
    String state;
    ArrayList<MyStudyDto> list;

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public ArrayList<MyStudyDto> getList() {
        return list;
    }

    public void setList(ArrayList<MyStudyDto> list) {
        this.list = list;
    }
}
