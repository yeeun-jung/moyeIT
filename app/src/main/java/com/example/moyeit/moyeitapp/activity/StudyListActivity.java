package com.example.moyeit.moyeitapp.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.example.moyeit.moyeitapp.Network.MoyeITServerClient;
import com.example.moyeit.moyeitapp.R;
import com.example.moyeit.moyeitapp.Service.MoyeITServerService;
import com.example.moyeit.moyeitapp.dto.ListClass;
import com.example.moyeit.moyeitapp.dto.MyStudyDto;
import com.example.moyeit.moyeitapp.dto.RegisterDto;
import com.example.moyeit.moyeitapp.dto.UserDto;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Home on 2017-07-30.
 */

public class StudyListActivity extends Activity {

    ListView studyList;
    ArrayList<MyStudyDto> list;
    public MoyeITServerClient moyeClient;
    public MoyeITServerService moyeService;
//    ListViewAdapter adapter;
    UserDto userDto;
    String sid;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.study_list);

        moyeClient = new MoyeITServerClient(getApplicationContext());
        moyeService = moyeClient.getMoyeITService();

        studyList = (ListView) findViewById(R.id.study_list);

//        Call<ListClass> StudyList = moyeService.StudyList(userDto.getInstance().getPid());
//        StudyList.enqueue(new Callback<ListClass>() {
//            @Override
//            public void onResponse(Call<ListClass> call, Response<ListClass> response) {
//                list = response.body().getList();
//                adapter = new ListViewAdapter(StudyListActivity.this, R.layout.study_detail, list);
//                studyList.setAdapter(adapter);
//            }
//
//            @Override
//            public void onFailure(Call<ListClass> call, Throwable t) {
//
//            }
//        });
//    };
//
//    public class ListViewAdapter extends {
//
//    }
//
    }
}