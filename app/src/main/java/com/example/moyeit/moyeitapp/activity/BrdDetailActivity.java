package com.example.moyeit.moyeitapp.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.moyeit.moyeitapp.Network.MoyeITServerClient;
import com.example.moyeit.moyeitapp.R;
import com.example.moyeit.moyeitapp.Service.MoyeITServerService;
import com.example.moyeit.moyeitapp.dto.BrdDto;
import com.example.moyeit.moyeitapp.dto.UserDto;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by alice on 2017-08-05.
 */

public class BrdDetailActivity extends Activity {
    private TextView textTitle;
    private TextView textMuser;
    private TextView textDetail;
    private TextView textCdate;
    private String sid;
    private String bid;
    private String nickname;
    public UserDto userDto;
    public MoyeITServerClient moyeClient;
    public MoyeITServerService moyeService;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detailbrd);

        textTitle = (TextView) findViewById(R.id.text_brddetailtitle);
        textMuser = (TextView) findViewById(R.id.text_brddetailnick);
        textDetail = (TextView) findViewById(R.id.text_brddetailcontent);
        textCdate = (TextView) findViewById(R.id.text_brddetaildate);
        moyeClient = new MoyeITServerClient(getApplicationContext());
        moyeService = moyeClient.getMoyeITService();
        userDto= UserDto.getInstance();
         /*
            server api 호출
         */
        Intent intent = getIntent();
        bid = intent.getExtras().getString("bid");
        sid = intent.getExtras().getString("sid"); //나중에 수정하기(현재 참여하고 들어와 있는 스터디의 id를 받아와야함)

        Call<BrdDto> callBrdDetailInfo = moyeService.detailbrd(bid, sid);
        //상세조회
        callBrdDetailInfo.enqueue(new Callback<BrdDto>() {
            @Override
            public void onResponse(Call<BrdDto> call, Response<BrdDto> response) {
                textTitle.setText(response.body().getBrdtitle());
                textDetail.setText(response.body().getContent());
                textCdate.setText(response.body().getDate());
                textMuser.setText(response.body().getNickname());
            }
            @Override
            public void onFailure(Call<BrdDto> call, Throwable t) {
                textTitle.setText("실패"+t.toString());
            }
        });
    }
}
