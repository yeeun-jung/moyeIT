package com.example.moyeit.moyeitapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.moyeit.moyeitapp.Network.MoyeITServerClient;
import com.example.moyeit.moyeitapp.R;
import com.example.moyeit.moyeitapp.Service.MoyeITServerService;
import com.example.moyeit.moyeitapp.dto.JoinDto;
import com.example.moyeit.moyeitapp.dto.UserDto;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by alice on 2017-08-14.
 */

public class SendIntroActivity extends AppCompatActivity {
    private EditText introduction;
    public String introvalue;
    public int sidvalue;
    public int pidvalue;
    private Button sendBtn;
    public UserDto userDto;
    public MoyeITServerClient moyeClient;
    public MoyeITServerService moyeService;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.send_introduction);

        introduction = (EditText) findViewById(R.id.edit_introduction);
        sendBtn = (Button) findViewById(R.id.btn_sendintro);
        userDto=UserDto.getInstance();
        moyeClient = new MoyeITServerClient(getApplicationContext());
        moyeService = moyeClient.getMoyeITService();

        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*
            server api 호출
            */
                introvalue = introduction.getText().toString();
                Intent intent = getIntent();
                sidvalue = Integer.parseInt(intent.getExtras().getString("sid")); //나중에 수정하기(현재 참여하고 들어와 있는 스터디의 id를 받아와야함)
                pidvalue = userDto.getPid();

                if(introvalue.equals("")){
                    Toast.makeText(SendIntroActivity.this, "자기소개를 입력해주세요.", Toast.LENGTH_SHORT).show();
                    return;
                }else{
                    Call<JoinDto> callSendIntro = moyeService.sendintro(introvalue, pidvalue, sidvalue);

                    callSendIntro.enqueue(new Callback<JoinDto>() {
                        @Override
                        public void onResponse(Call<JoinDto> call, Response<JoinDto> response) {
                            Intent intent = new Intent(getApplicationContext(), MsMainActivity.class);
                            //요청자페이지로 이동
                            startActivity(intent);
                        }
                        @Override
                        public void onFailure(Call<JoinDto> call, Throwable t) {
                            Log.i("실패", "실패");
                        }
                    });
                } }
        });
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_send_intro);
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
