package com.example.moyeit.moyeitapp.dto;

import java.util.ArrayList;

/**
 * Created by ga0 on 2017-08-13.
 */

public class reqManagerDto {
    ArrayList<reqManagerDtlDto> list;
    String state;

    public String getState() {
        return state;
    }


    public ArrayList<reqManagerDtlDto> getList() {
        return list;
    }


}
