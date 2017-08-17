package com.example.moyeit.moyeitapp.activity;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

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
 * Created by yeeun on 2017-08-05.
 */

public class StudyRegiActivity extends AppCompatActivity {

    StudyRegisterDTO register;
    UserDto userDto;
    TextView textView, nickname, title, region, subject, detail;
    EditText editTitle, editLimitnum, editDetail;
    Spinner spinSubject, spinRegion;
    Button save;
    String strRegion;
    String[] strSubject = {"Java", "C", "C++", "Android", "Web", "기타"};
    public MoyeITServerClient moyeClient;
    public MoyeITServerService moyeService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.study_register);

        final GlobalMethod g = new GlobalMethod();

        moyeClient = new MoyeITServerClient(getApplicationContext());
        moyeService = moyeClient.getMoyeITService();
        register = new StudyRegisterDTO();

        userDto = UserDto.getInstance();
        textView = (TextView) findViewById(R.id.sampleText);
        nickname = (TextView) findViewById(R.id.nickname);
        title = (TextView) findViewById(R.id.title);
        region = (TextView) findViewById(R.id.region);
        subject = (TextView) findViewById(R.id.subject);
        detail = (TextView) findViewById(R.id.detail);
        editTitle = (EditText) findViewById(R.id.title_edit);
        editLimitnum = (EditText) findViewById(R.id.limitnum_edit);
        editDetail = (EditText) findViewById(R.id.detail_edit);
        spinRegion = (Spinner) findViewById(R.id.spinner_region);
        spinSubject = (Spinner) findViewById(R.id.spinner_subject);
        save = (Button) findViewById(R.id.save);
        nickname.setText(userDto.getNickname());
        ArrayAdapter<CharSequence> adapterRegion = ArrayAdapter.createFromResource(this, R.array.str_array_region, android.R.layout.simple_spinner_item);
        ArrayAdapter<CharSequence> adapter = new ArrayAdapter<CharSequence>(this, android.R.layout.simple_spinner_item, strSubject);
        adapterRegion.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinRegion.setAdapter(adapterRegion);
        spinSubject.setAdapter(adapter);
        spinRegion.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                strRegion = adapterView.getItemAtPosition(i).toString();
                register.setRegion(g.toIntRegion(strRegion));
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
        spinSubject.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                register.setSubject(adapterView.getItemAtPosition(i).toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 /*
            server api 호출
         */


                if (editTitle.getText().toString().equals("")) {
                    Toast.makeText(StudyRegiActivity.this, "제목을 입력해주세요.", Toast.LENGTH_SHORT).show();
                    return;
                } else if (editLimitnum.getText().toString().equals("")) {
                    Toast.makeText(StudyRegiActivity.this, "제한인원을 입력해주세요.", Toast.LENGTH_SHORT).show();
                    return;
                } else if (editDetail.getText().toString().equals("")) {
                    Toast.makeText(StudyRegiActivity.this, "내용을 입력해주세요.", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    register.setLimitnum(Integer.parseInt((editLimitnum.getText().toString())));
                    register.setTitle(editTitle.getText().toString());
                    register.setDetail(editDetail.getText().toString());
                    register.setNickname(userDto.getNickname());
                    Call<StudyRegisterDTO> registerDtocall = moyeService.studyRegi(register.getNickname(), register.getTitle(), register.getRegion(), register.getLimitnum(), register.getDetail(), register.getSubject(), userDto.getPid());
                    registerDtocall.enqueue(new Callback<StudyRegisterDTO>() {
                        @Override
                        public void onResponse(Call<StudyRegisterDTO> call, Response<StudyRegisterDTO> response) {
                            if (response.body().getState().toString().equals("fail2")) {
                                Toast.makeText(StudyRegiActivity.this, "중복되는 제목입니다.", Toast.LENGTH_SHORT).show();
                                return;
                            } else {
                                Intent intent = new Intent(getApplicationContext(), MsDetailActivity.class);
                                intent.putExtra("sid", Integer.toString(response.body().getSid()));
                                startActivity(intent);
                            }
                        }

                        @Override
                        public void onFailure(Call<StudyRegisterDTO> call, Throwable t) {
                            textView.setText("실패" + t.toString());
                        }
                    });


                }
            }
        });

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_studyregi);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
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

    public void onBackPressed() {

        // TODO Auto-generated method stub
        // super.onBackPressed(); //지워야 실행됨

        AlertDialog.Builder d = new AlertDialog.Builder(this);
        d.setMessage("정말 종료하시겠습니까? \n입력한 내용이 삭제됩니다.");
        d.setPositiveButton("예", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });

        d.setNegativeButton("아니요", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        d.show();
    }
}