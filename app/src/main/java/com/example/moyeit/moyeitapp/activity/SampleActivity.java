package com.example.moyeit.moyeitapp.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.moyeit.moyeitapp.Network.MoyeITServerClient;
import com.example.moyeit.moyeitapp.R;
import com.example.moyeit.moyeitapp.Service.MoyeITServerService;
import com.example.moyeit.moyeitapp.dto.MystudyDto;
import com.example.moyeit.moyeitapp.dto.Study;
import com.example.moyeit.moyeitapp.dto.StudyDto;
import com.example.moyeit.moyeitapp.dto.UserDto;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by yeeun on 2017-07-24.
 */

public class SampleActivity extends Activity{
    StudyDto study;
    UserDto userDto;
    TextView textView;
    EditText editTitle;
    EditText editRegion;
    EditText editLimit;
    EditText editDetail;
    Button button;
    public MoyeITServerClient moyeClient;
    public MoyeITServerService moyeService;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sample);

        moyeClient = new MoyeITServerClient(getApplicationContext());
        moyeService = moyeClient.getMoyeITService();
        study= new StudyDto();

        userDto=UserDto.getInstance();
        textView=(TextView)findViewById(R.id.sampleText);
        editTitle=(EditText)findViewById(R.id.editTextTitle);
        editDetail=(EditText)findViewById(R.id.editTextDetail);
        editLimit=(EditText)findViewById(R.id.editTextLimit);
        editRegion=(EditText)findViewById(R.id.editTextRegion);
        button=(Button)findViewById(R.id.button);
        textView.setText(userDto.getEmail());

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                study.setDetail(editDetail.getText().toString());
                study.setLimit(Integer.parseInt(editLimit.getText().toString()));
                study.setTitle(editTitle.getText().toString());
                study.setDetail(editDetail.getText().toString());
                study.setRegion(Integer.parseInt(editRegion.getText().toString()));
                study.setNickname(userDto.getNickname());
                 Call<StudyDto> studyDtocall= moyeService.studyRegi(study.getNickname(),study.getTitle(),study.getRegion(),study.getLimit(),study.getDetail());
            //    textView.setText(study.toString());
                studyDtocall.enqueue(new Callback<StudyDto>() {
                    @Override
                    public void onResponse(Call<StudyDto> call, Response<StudyDto> response) {
                        textView.setText(response.body().getState());

                    }

                    @Override
                    public void onFailure(Call<StudyDto> call, Throwable t) {
                        textView.setText("실패" + t.toString());
                    }
                });
            }
        });



    }
}
