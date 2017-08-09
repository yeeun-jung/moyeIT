package com.example.moyeit.moyeitapp.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
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
    private TextView textView;
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
        final int sid=Integer.parseInt(intent.getStringExtra("sid"));
        join= intent.getStringExtra("join");

        joinBtn = (Button) findViewById(R.id.buttonJoin);
        textView = (TextView) findViewById(R.id.textView_study_detail);

        user = UserDto.getInstance();

        moyeClient = new MoyeITServerClient(getApplicationContext());
        moyeService = moyeClient.getMoyeITService();

        detail = new StudyDetailDto();

        Call<StudyDetailDto> callStudyDetail = moyeService.getStudyDetail(sid);

        callStudyDetail.enqueue(new Callback<StudyDetailDto>() {
            @Override
            public void onResponse(Call<StudyDetailDto> call, Response<StudyDetailDto> response) {
                textView.setText("방장 닉네임: "+response.body().getNickname());
                textView.append("\n상세내용: "+response.body().getDetail()+"\njoin: "+join);
            }

            @Override
            public void onFailure(Call<StudyDetailDto> call, Throwable t) {
                textView.setText("실패" + t.toString());
            }
        });
        joinBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Call<StudyDetailDto> bodyCall = moyeService.StudyJoin(user.getPid(),sid);
                bodyCall.enqueue(new Callback<StudyDetailDto>() {
                    @Override
                    public void onResponse(Call<StudyDetailDto> call, Response<StudyDetailDto> response) {
                        Toast.makeText(StudyDetailActivity.this, response.body().getState(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<StudyDetailDto> call, Throwable t) {

                    }
                });
            }});
    }
}
