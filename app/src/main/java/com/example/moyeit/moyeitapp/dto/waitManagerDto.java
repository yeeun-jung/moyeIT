package com.example.moyeit.moyeitapp.dto;

import java.util.ArrayList;

/**
 * Created by Home on 2017-08-16.
 */

public class waitManagerDto {
    ArrayList<waitManagerDtlDto> list;
    String state;

    public String getState() {
        return state;
    }

    public ArrayList<waitManagerDtlDto> getList() {
        return list;
    }
}
