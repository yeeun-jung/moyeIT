package com.example.moyeit.moyeitapp.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.example.moyeit.moyeitapp.Network.MoyeITServerClient;
import com.example.moyeit.moyeitapp.Service.MoyeITServerService;
import com.example.moyeit.moyeitapp.dto.RegisterDto;
import com.example.moyeit.moyeitapp.dto.StudyDetailDto;

/**
 * Created by Home on 2017-07-29.
 */

public class StudyDetailActivity extends Activity{

    public MoyeITServerClient moyeClient;
    public MoyeITServerService moyeService;
    StudyDetailDto detail;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        moyeClient = new MoyeITServerClient(getApplicationContext());
        moyeService = moyeClient.getMoyeITService();
        detail = new StudyDetailDto();
    }
}
