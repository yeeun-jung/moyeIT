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
import com.example.moyeit.moyeitapp.dto.CmoimDto;
import com.example.moyeit.moyeitapp.dto.MoimDto;
import com.example.moyeit.moyeitapp.dto.StudyDetailDto;
import com.example.moyeit.moyeitapp.dto.UserDto;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by alice on 2017-07-25.
 */

public class MoimAddActivity extends AppCompatActivity {
    private EditText moimtitle;
    private EditText content;
    private EditText limitnum;
    public String moimtitlevalue;
    public String contentvalue;
    public String limitnumvalue;
    public String pidvalue;
    public String sidvalue;
    private Button addBtn;
    public MoimDto moimDto;
    public UserDto userDto;
    public MoyeITServerClient moyeClient;
    public MoyeITServerService moyeService;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addmoim);

        moimtitle = (EditText) findViewById(R.id.edit_moimtitle);
        content = (EditText) findViewById(R.id.edit_content);
        limitnum = (EditText) findViewById(R.id.edit_limitnum);
        addBtn = (Button) findViewById(R.id.button_add);
        userDto=UserDto.getInstance();
        moyeClient = new MoyeITServerClient(getApplicationContext());
        moyeService = moyeClient.getMoyeITService();

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*
            server api 호출
            */
                moimtitlevalue = moimtitle.getText().toString();
                contentvalue = content.getText().toString();
                limitnumvalue = limitnum.getText().toString();
                pidvalue = Integer.toString(userDto.getPid());
                Intent intent = getIntent();
                sidvalue = intent.getExtras().getString("sid"); //나중에 수정하기(현재 참여하고 들어와 있는 스터디의 id를 받아와야함)

                if(moimtitlevalue.equals("")){
                    Toast.makeText(MoimAddActivity.this, "제목을 입력해주세요.", Toast.LENGTH_SHORT).show();
                    return;
                }else if(limitnumvalue.equals("")){
                    Toast.makeText(MoimAddActivity.this, "인원수를 입력해주세요.", Toast.LENGTH_SHORT).show();
                    return;
                }else if(contentvalue.equals("")){
                    Toast.makeText(MoimAddActivity.this, "설명을 입력해주세요.", Toast.LENGTH_SHORT).show();
                    return;
                }else{
                    Call<MoimDto> callMoimInfo = moyeService.addmoim(moimtitlevalue, contentvalue, limitnumvalue, pidvalue, sidvalue);

                    callMoimInfo.enqueue(new Callback<MoimDto>() {
                        @Override
                        public void onResponse(Call<MoimDto> call, Response<MoimDto> response) {
                            Intent intent = new Intent(getApplicationContext(), MsDetailActivity.class);
                            intent.putExtra("sid", sidvalue);
                            startActivity(intent);
                        }
                        @Override
                        public void onFailure(Call<MoimDto> call, Throwable t) {
                            Log.i("실패", "실패");
                        }
                    });
                } }
        });
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_add_moim);
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


