package com.example.moyeit.moyeitapp.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.moyeit.moyeitapp.Network.MoyeITServerClient;
import com.example.moyeit.moyeitapp.R;
import com.example.moyeit.moyeitapp.Service.MoyeITServerService;
import com.example.moyeit.moyeitapp.dto.RegisterDto;
import com.example.moyeit.moyeitapp.dto.UserDto;

import org.w3c.dom.Text;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.moyeit.moyeitapp.R.id.textView;

/**
 * Created by Home on 2017-07-25.
 */

//public class RegisterActivity extends Activity {
//
//    RegisterDto register;
//    UserDto userDto;
//    TextView textView;
//    EditText editNickname, editTitle, editRegion, editLimitnum, editDetail;
//    Button saveBtn;
//    public MoyeITServerClient moyeClient;
//    public MoyeITServerService moyeService;
//
//    @Override
//    protected void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.register);
//
//        moyeClient = new MoyeITServerClient(getApplicationContext());
//        moyeService = moyeClient.getMoyeITService();
//        register = new RegisterDto();
//
//        userDto = UserDto.getInstance();
//        textView = (TextView) findViewById(R.id.registerresult);
//        editNickname = (EditText) findViewById(R.id.nickname_edit);
//        editTitle = (EditText) findViewById(R.id.title_edit);
//        editRegion = (EditText) findViewById(R.id.region_edit);
//        editLimitnum = (EditText) findViewById(R.id.limitnum_edit);
//        editDetail = (EditText) findViewById(R.id.detail_edit);
//        saveBtn = (Button) findViewById(R.id.save);
//
//        saveBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                String nickname = editNickname.getText().toString();
//                String title = editTitle.getText().toString();
//                String region = editRegion.getText().toString();
//                String limitnum = editLimitnum.getText().toString();
//                String detail = editDetail.getText().toString();
//
//                RegisterDto newstudy = new RegisterDto();
//
//                newstudy.setNickname(nickname);
//                newstudy.setTitle(title);
//                newstudy.setRegion(Integer.parseInt(region));
//                newstudy.setLimitnum(Integer.parseInt(limitnum));
//                newstudy.setDetail(detail);
//
//                Intent intent = new Intent(RegisterActivity.this, StudyListActivity.class);
//                intent.putExtra("study", newstudy);
//                startActivity(intent);

//                register.setNickname(userDto.getNickname());
//                register.setTitle(editTitle.getText().toString());
//                register.setRegion(Integer.parseInt(editRegion.getText().toString()));
//                register.setLimitnum(Integer.parseInt(editLimitnum.getText().toString()));
//                register.setDetail(editDetail.getText().toString());
//
//                Call<RegisterDto> registerDtocall = moyeService.studyRegi(register.getNickname(), register.getTitle(), register.getRegion(), register.getLimitnum(), register.getDetail());
//                registerDtocall.enqueue(new Callback<RegisterDto>() {
//                    @Override
//                    public void onResponse(Call<RegisterDto> call, Response<RegisterDto> response) {
//                        textView.setText(response.body().getState());
//                    }
//
//                    @Override
//                    public void onFailure(Call<RegisterDto> call, Throwable t) {
//                        textView.setText("실패" + t.toString());
//                    }
//                });
//            }
//        });
//    }
//}

public class RegisterActivity extends Activity {

    RegisterDto register;
    UserDto userDto;
    TextView moyeit, nickname, title, region, detail;
    EditText editNickname, editTitle, editRegion, editLimitnum, editDetail;
    Button save;
    public TextView result, register_result;
    public String editNicknameValue;
    public String editTitleValue;
    public int editRegionValue;
    public int editLimitnumValue;
    public String editDetailValue;
    public MoyeITServerClient moyeClient;
    public MoyeITServerService moyeService;
    public RegisterDto registerDto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);

        userDto=UserDto.getInstance();
        moyeit = (TextView) findViewById(R.id.moyeit);
        nickname = (TextView) findViewById(R.id.nickname);
        title = (TextView) findViewById(R.id.title);
        region = (TextView) findViewById(R.id.region);
        detail = (TextView) findViewById(R.id.detail);
        editNickname = (EditText) findViewById(R.id.nickname_edit);
        editTitle = (EditText) findViewById(R.id.title_edit);
        editRegion = (EditText) findViewById(R.id.region_edit);
        editLimitnum = (EditText) findViewById(R.id.limitnum_edit);
        editDetail = (EditText) findViewById(R.id.detail_edit);
        save = (Button) findViewById(R.id.save);

        moyeClient = new MoyeITServerClient(getApplicationContext());
        moyeService = moyeClient.getMoyeITService();
        register = new RegisterDto();

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 /*
            server api 호출
         */
                register.setDetail(editDetail.getText().toString());
                register.setLimitnum(Integer.parseInt((editLimitnum.getText().toString())));
                register.setTitle(editTitle.getText().toString());
                register.setDetail(editDetail.getText().toString());
                register.setRegion(Integer.parseInt(editRegion.getText().toString()));
                register.setNickname(userDto.getNickname());

//                if (editNicknameValue.equals("")) {
//                    Toast.makeText(RegisterActivity.this, "닉네임을 입력해주세요.", Toast.LENGTH_SHORT).show();
//                    return;
//                } else if (editTitleValue.equals("")) {
//                    Toast.makeText(RegisterActivity.this, "제목을 입력해주세요.", Toast.LENGTH_SHORT).show();
//                    return;
//                } else if (editRegion.getText().toString().equals("")) {
//                    Toast.makeText(RegisterActivity.this, "지역을 입력해주세요.", Toast.LENGTH_SHORT).show();
//                } else if (editLimitnum.getText().toString().equals("")) {
//                    Toast.makeText(RegisterActivity.this, "제한인원을 입력해주세요.", Toast.LENGTH_SHORT).show();
//                } else if (editDetailValue.equals("")) {
//                    Toast.makeText(RegisterActivity.this, "내용을 입력해주세요.", Toast.LENGTH_SHORT).show();
//                } else {
                Call<RegisterDto> callRegisterInfo = moyeService.studyRegi(register.getNickname(), register.getTitle(), register.getRegion(), register.getLimitnum(), register.getDetail());

                Call<RegisterDto> registerDtocall = moyeService.studyRegi(register.getNickname(), register.getTitle(), register.getRegion(), register.getLimitnum(), register.getDetail());
                registerDtocall.enqueue(new Callback<RegisterDto>() {
                    @Override
                    public void onResponse(Call<RegisterDto> call, Response<RegisterDto> response) {
                        textView.setText(response.body().getState());
                    }

                    @Override
                    public void onFailure(Call<RegisterDto> call, Throwable t) {
                        textView.setText("실패" + t.toString());
                    }
                });
            }
            });
    };
}