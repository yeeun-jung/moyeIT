package com.example.moyeit.moyeitapp.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.moyeit.moyeitapp.Network.MoyeITServerClient;
import com.example.moyeit.moyeitapp.R;
import com.example.moyeit.moyeitapp.Service.MoyeITServerService;
import com.example.moyeit.moyeitapp.dto.StudyDetailDto;
import com.example.moyeit.moyeitapp.dto.UserDto;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by yeeun on 2017-08-05.
 */

public class StudyDetailActivity extends AppCompatActivity {

    private Button joinBtn;
    private TextView textView, studyTitle;
    private UserDto user;
    String join;
    public MoyeITServerClient moyeClient;
    public MoyeITServerService moyeService;
    public StudyDetailDto detail;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.study_detail);

        Intent intent = getIntent();
        final int sid = Integer.parseInt(intent.getStringExtra("sid"));
        join = intent.getStringExtra("join");

        joinBtn = (Button) findViewById(R.id.buttonJoin);
        textView = (TextView) findViewById(R.id.textView_study_detail);
        studyTitle = (TextView) findViewById(R.id.textView_study);

        user = UserDto.getInstance();

        moyeClient = new MoyeITServerClient(getApplicationContext());
        moyeService = moyeClient.getMoyeITService();

        detail = new StudyDetailDto();
        detail.setSid(sid);
        Call<StudyDetailDto> callStudyDetail = moyeService.getStudyDetail(sid);

        callStudyDetail.enqueue(new Callback<StudyDetailDto>() {
            @Override
            public void onResponse(Call<StudyDetailDto> call, Response<StudyDetailDto> response) {
                String[] val=response.body().getTitle().toString().trim().split("]");
                studyTitle.setText(val[2]);
                textView.setText("- 방장 닉네임: " + response.body().getNickname());
                textView.append("\n- 상세내용: " + response.body().getDetail());
                textView.append("\n- 제한인원: " + response.body().getLimitnum());
                textView.append("\n- 현재인원: " + response.body().getContnum()+"\n- 지역: "+val[0].replaceAll("\\[","")+"\n- 분야: "+val[1].replaceAll("\\[",""));
                if (response.body().getLimitnum() > response.body().getContnum()) {
                    joinBtn.setEnabled(true);
                    joinBtn.setText("참여하기");
                } else {
                    joinBtn.setEnabled(false);
                    joinBtn.setText("마감되었습니다");

                }

            }

            @Override
            public void onFailure(Call<StudyDetailDto> call, Throwable t) {
                textView.setText("실패" + t.toString());
            }
        });

        joinBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myintent = new Intent(getApplicationContext(), SendIntroActivity.class);
                myintent.putExtra("sid", Integer.toString(detail.getSid()));
                startActivity(myintent);
            }
        });

        //툴바, 뒤로가기

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_study_detail);
        setSupportActionBar(toolbar);
        ActionBar actionBar =getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.mipmap.noun_back);

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
