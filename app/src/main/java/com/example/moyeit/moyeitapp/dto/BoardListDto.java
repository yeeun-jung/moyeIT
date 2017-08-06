package com.example.moyeit.moyeitapp.dto;

import java.util.ArrayList;

/**
 * Created by ga0 on 2017-08-05.
 */

public class BoardListDto {
    String state;
    ArrayList<BoardDetailListDto> list;

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public ArrayList<BoardDetailListDto> getList() {
        return list;
    }

    public void setList(ArrayList<BoardDetailListDto> list) {
        this.list = list;
    }
}
