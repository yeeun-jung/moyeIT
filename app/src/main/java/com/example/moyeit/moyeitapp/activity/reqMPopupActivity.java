package com.example.moyeit.moyeitapp.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.example.moyeit.moyeitapp.Network.MoyeITServerClient;
import com.example.moyeit.moyeitapp.R;
import com.example.moyeit.moyeitapp.Service.MoyeITServerService;
import com.example.moyeit.moyeitapp.dto.UserDto;
import com.example.moyeit.moyeitapp.dto.reqManagerDto;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by ga0 on 2017-08-14.
 */

public class reqMPopupActivity extends Activity{

    public MoyeITServerClient moyeClient;
    public MoyeITServerService moyeService;
    Button app;
    Button rej;
    TextView aid;
    TextView content;
    TextView name;
    UserDto userDto;
    Call<reqManagerDto> rList;
    String pid;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.req_m_popup);

        aid = (TextView)findViewById(R.id.rmp_aid);
        content = (TextView)findViewById(R.id.rmp_content);
        name = (TextView)findViewById(R.id.rmp_name);
        Intent intent = getIntent();
        aid.setText(intent.getExtras().getString("aid"));
        content.setText(intent.getExtras().getString("content"));
        name.setText(intent.getExtras().getString("name"));

        app = (Button)findViewById(R.id.appBtn);
        rej = (Button)findViewById(R.id.rejBtn);

        moyeClient = new MoyeITServerClient(getApplicationContext());
        moyeService = moyeClient.getMoyeITService();

        userDto = UserDto.getInstance();
        pid = String.valueOf(userDto.getPid());

        app.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rList = moyeService.reqMList(Integer.parseInt(pid),"true", aid.getText().toString());
                rList.enqueue(new Callback<reqManagerDto>() {
                    @Override
                    public void onResponse(Call<reqManagerDto> call, Response<reqManagerDto> response) {
                        Intent r_intent = new Intent(getApplicationContext(), ReqManagerActivity.class);
                        startActivity(r_intent);
                    }

                    @Override
                    public void onFailure(Call<reqManagerDto> call, Throwable t) {

                    }
                });
            }
        });

        rej.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rList = moyeService.reqMList(Integer.parseInt(pid),"false", aid.getText().toString());
                rList.enqueue(new Callback<reqManagerDto>() {
                    @Override
                    public void onResponse(Call<reqManagerDto> call, Response<reqManagerDto> response) {
                        Intent r_intent = new Intent(getApplicationContext(), ReqManagerActivity.class);
                        startActivity(r_intent);
                    }

                    @Override
                    public void onFailure(Call<reqManagerDto> call, Throwable t) {

                    }
                });
            }
        });

    }

}
