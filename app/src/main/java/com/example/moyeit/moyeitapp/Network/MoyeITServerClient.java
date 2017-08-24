package com.example.moyeit.moyeitapp.Network;

import android.content.Context;
import android.util.Log;

import com.example.moyeit.moyeitapp.Service.MoyeITServerService;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
/**
 * Created by alice on 2017-07-21.
 */

public class MoyeITServerClient implements MoyeITClient{

    private Context context;
    private String BASE_URL;
    private MoyeITServerService apiService;


    public MoyeITServerClient(Context context){
        this.context = context;
        init();
    }

    public void init(){
        BASE_URL = "http://13.124.67.227:80/moyeit/";
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        apiService = retrofit.create(MoyeITServerService.class);
    }
    @Override
    public MoyeITServerService getMoyeITService() {
        return apiService;
    }

}