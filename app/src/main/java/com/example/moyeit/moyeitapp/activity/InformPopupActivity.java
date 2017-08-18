package com.example.moyeit.moyeitapp.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import com.example.moyeit.moyeitapp.Network.MoyeITServerClient;
import com.example.moyeit.moyeitapp.R;
import com.example.moyeit.moyeitapp.Service.MoyeITServerService;
import com.example.moyeit.moyeitapp.dto.StudyDetailDto;
import com.example.moyeit.moyeitapp.dto.StudyRegisterDTO;
import com.example.moyeit.moyeitapp.dto.UserDto;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by yeeun on 2017-08-13.
 */

public class InformPopupActivity extends Activity implements View.OnClickListener {

    public MoyeITServerClient moyeClient;
    public MoyeITServerService moyeService;
    private int sid;
    private TextView textViewTitle, textViewConts;
    public UserDto userDto;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.inform_popup);
        textViewTitle=(TextView)findViewById(R.id.textView_inform_title);
        textViewConts=(TextView)findViewById(R.id.textView_inform_contents);

        moyeClient = new MoyeITServerClient(getApplicationContext());
        moyeService = moyeClient.getMoyeITService();
        userDto=UserDto.getInstance();

        Intent intent = getIntent();
        sid = Integer.parseInt(intent.getExtras().getString("sid"));
        Call<StudyDetailDto> callUserInfo = moyeService.getStudyDetail(sid,userDto.getPid());

        callUserInfo.enqueue(new Callback<StudyDetailDto>() {
            @Override
            public void onResponse(Call<StudyDetailDto> call, Response<StudyDetailDto> response) {
                String[] val=response.body().getTitle().toString().trim().split("]");
                textViewTitle.setText(val[2]);
                textViewConts.setText("- 방장 닉네임: " + response.body().getNickname());
                textViewConts.append("\n- 분야: " + val[1].replaceAll("\\[", "") + "\n- 지역: " + val[0].replaceAll("\\[", ""));
                textViewConts.append("\n- 현재인원: " + response.body().getContnum() + "\n- 제한인원: " + response.body().getLimitnum());
                textViewConts.append("\n- 상세내용: " + response.body().getDetail());
            }

            @Override
            public void onFailure(Call<StudyDetailDto> call, Throwable t) {

            }
        });


        findViewById(R.id.button_close).setOnClickListener(this);

    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_close:
                this.finish();
                break;
        }
    }

}
