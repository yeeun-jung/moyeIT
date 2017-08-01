package com.example.moyeit.moyeitapp.dto;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Home on 2017-07-27.
 */

public class ListClass {
    @SerializedName("list")
    Study st;

    @SerializedName("state")
    String state;

    public String getState() {
        return state;
    }

    public void setState(String state){
        this.state = state;
    }
}
