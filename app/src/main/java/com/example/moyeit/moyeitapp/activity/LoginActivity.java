package com.example.moyeit.moyeitapp.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.example.moyeit.moyeitapp.Network.MoyeITServerClient;
import com.example.moyeit.moyeitapp.R;
import com.example.moyeit.moyeitapp.Service.MoyeITServerService;
import com.example.moyeit.moyeitapp.dto.UserDto;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by yeeun on 2017-07-23.
 */

public class LoginActivity extends Activity {
    private TextView textInform;
    private EditText editId;
    private EditText editPwd;
    private Button loginBtn;
    public String editIDValue;
    public String editPwdValue;
    public UserDto userDto;
    public MoyeITServerClient moyeClient;
    public MoyeITServerService moyeService;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        textInform = (TextView) findViewById(R.id.textView);
        editId = (EditText) findViewById(R.id.editText_id);
        editPwd = (EditText) findViewById(R.id.editText_pwd);
        loginBtn = (Button) findViewById(R.id.buttonlogin);

        moyeClient = new MoyeITServerClient(getApplicationContext());
        moyeService = moyeClient.getMoyeITService();

        userDto= UserDto.getInstance();




        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 /*
            server api 호출
         */
                editIDValue = editId.getText().toString();
                editPwdValue = editPwd.getText().toString();

                if (editIDValue.equals("")) {
                    Toast.makeText(LoginActivity.this, "아이디를 입력해주세요.", Toast.LENGTH_SHORT).show();
                    return;
                } else if (editPwdValue.equals("")) {
                    Toast.makeText(LoginActivity.this, "비밀번호를 입력해주세요.", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    Call<UserDto> callUserInfo = moyeService.login(editIDValue);

                    callUserInfo.enqueue(new Callback<UserDto>() {
                        @Override
                        public void onResponse(Call<UserDto> call, Response<UserDto> response) {

                       //       textInform.setText(response.body().getEmail());
                            if (response.body().getState().equals("fail")) {
                                Toast.makeText(LoginActivity.this, response.body().getState().toString()+"서버에 문제가 생겼습니다. 관리자에게 문의하세요.", Toast.LENGTH_SHORT).show();
                            }
                         else if(response.body().getEmail().equals("")){
                            Toast.makeText(LoginActivity.this, "없는 계정입니다.", Toast.LENGTH_SHORT).show();

                        }else if (response.body().getPwd().equals(editPwdValue)) {
                            userDto.setEmail(response.body().getEmail());
                            userDto.setEnjoy(response.body().getEnjoy());
                            userDto.setNickname(response.body().getNickname());
                            userDto.setPid(response.body().getPid());
                            userDto.setRegion(response.body().getRegion());
                            Toast.makeText(LoginActivity.this, response.body().getNickname()+"님, 안녕하세요.", Toast.LENGTH_SHORT).show();

                            Intent intent =new Intent(getApplicationContext(),SampleActivity.class);
                            startActivity(intent);
                        }else if(!response.body().getPwd().equals(editPwdValue)){
                            Toast.makeText(LoginActivity.this, "아이디와 비밀번호를 확인해주세요.", Toast.LENGTH_SHORT).show();
                            userDto.setRegion(0);
                            userDto.setPid(0);
                            userDto.setEnjoy("");
                            userDto.setEmail("");
                            userDto.setNickname("");
                        }


                        }

                        @Override
                        public void onFailure(Call<UserDto> call, Throwable t) {
                            textInform.setText("실패" + t.toString());
                        }
                    });


                }
            }
            });
        }
    }







