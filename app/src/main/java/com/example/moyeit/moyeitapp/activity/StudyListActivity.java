package com.example.moyeit.moyeitapp.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.EditText;
import android.widget.TextView;

import com.example.moyeit.moyeitapp.Network.MoyeITServerClient;
import com.example.moyeit.moyeitapp.R;
import com.example.moyeit.moyeitapp.Service.MoyeITServerService;
import com.example.moyeit.moyeitapp.dto.RegisterDto;
import com.example.moyeit.moyeitapp.dto.UserDto;

import java.util.ArrayList;

/**
 * Created by Home on 2017-07-30.
 */

public class StudyListActivity extends Activity{

    TextView registerResult;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.study_list);

        registerResult = (TextView)findViewById(R.id.registerResult);

        Intent intent = getIntent();
        RegisterDto newstudy = (RegisterDto)intent.getSerializableExtra("study");

        String nickname = newstudy.getNickname();
        String title = newstudy.getTitle();
        int region = newstudy.getRegion();
        int limitnum = newstudy.getLimitnum();
        String detail = newstudy.getDetail();

        String msg = nickname + " " + title + " "+ region + " " + limitnum + " " + detail;
        registerResult.setText(msg);
    }

    //    TextView nickname, title, region, limitnum, detail;
//    public MoyeITServerClient moyeClient;
//    public MoyeITServerService moyeService;
//    RegisterDto register;
//    UserDto userDto;
//    ArrayList studylist = new ArrayList();
//
//    @Override
//    protected void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.study_list);
//
//        moyeClient = new MoyeITServerClient(getApplicationContext());
//        moyeService = moyeClient.getMoyeITService();
//        register = new RegisterDto();
//
//        nickname = (TextView) findViewById(R.id.nickname);
//        title = (TextView) findViewById(R.id.title);
//        limitnum = (TextView) findViewById(R.id.limitnum);
//        detail = (TextView) findViewById(R.id.detail);
//
//        register.setLimitnum(Integer.parseInt(limitnum.getText().toString()));
//        register.setTitle(title.getText().toString());
//        register.setDetail(detail.getText().toString());
//        register.setRegion(Integer.parseInt(region.getText().toString()));
//        register.setNickname(userDto.getNickname());
//
//        ArrayList<RegisterDto> studylist = new ArrayList<RegisterDto>();
//        studylist.add(new RegisterDto());
//    }
}
