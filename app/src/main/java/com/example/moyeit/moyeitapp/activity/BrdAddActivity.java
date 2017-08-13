package com.example.moyeit.moyeitapp.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
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

public class BrdAddActivity extends AppCompatActivity {
    private EditText brdtitle;
    private EditText content;
    public String brdtitlevalue;
    public String contentvalue;
    public String sidvalue;
    public String nickvalue;
    private Button addbrdBtn;
    public BrdDto brdDto;
    public UserDto userDto;
    public MoyeITServerClient moyeClient;
    public MoyeITServerService moyeService;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addbrd);

        brdtitle = (EditText) findViewById(R.id.edit_brdtitle);
        content = (EditText) findViewById(R.id.edit_brddetail);
        addbrdBtn = (Button) findViewById(R.id.btn_addbrd);
        userDto=UserDto.getInstance();
        moyeClient = new MoyeITServerClient(getApplicationContext());
        moyeService = moyeClient.getMoyeITService();

        addbrdBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*
            server api 호출
            */
                brdtitlevalue = brdtitle.getText().toString();
                contentvalue = content.getText().toString();
                nickvalue = userDto.getNickname();
                Intent intent = getIntent();
                sidvalue = intent.getExtras().getString("sid"); //나중에 수정하기(현재 참여하고 들어와 있는 스터디의 id를 받아와야함)

                if(brdtitlevalue.equals("")){
                    Toast.makeText(BrdAddActivity.this, "제목을 입력해주세요.", Toast.LENGTH_SHORT).show();
                    return;
                }else if(contentvalue.equals("")) {
                    Toast.makeText(BrdAddActivity.this, "내용을 입력해주세요.", Toast.LENGTH_SHORT).show();
                    return;
                }else{
                    Call<BrdDto> callBrdInfo = moyeService.addbrd(brdtitlevalue, contentvalue, nickvalue, sidvalue);

                    callBrdInfo.enqueue(new Callback<BrdDto>() {
                        @Override
                        public void onResponse(Call<BrdDto> call, Response<BrdDto> response) {
                        }
                        @Override
                        public void onFailure(Call<BrdDto> call, Throwable t) {
                            Log.i("실패", "실패");
                        }
                    });
                } }
        });
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_add_brd);
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
