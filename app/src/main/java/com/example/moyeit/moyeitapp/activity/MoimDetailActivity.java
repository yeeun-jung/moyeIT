package com.example.moyeit.moyeitapp.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.moyeit.moyeitapp.Network.MoyeITServerClient;
import com.example.moyeit.moyeitapp.R;
import com.example.moyeit.moyeitapp.Service.MoyeITServerService;
import com.example.moyeit.moyeitapp.dto.CmoimDto;
import com.example.moyeit.moyeitapp.dto.MoimDto;
import com.example.moyeit.moyeitapp.dto.UserDto;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by alice on 2017-07-27.
 */

public class MoimDetailActivity extends Activity {
    private TextView textInform;
    private TextView textTitle;
    private TextView textMuser;
    private TextView textDetail;
    private TextView textCdate;
    private ListView textComment;
    private EditText editComment;
    private String sid;
    private String no;
    private String mid;
    private String vote;
    private Button btnagree;
    private Button btndisagree;
    private Button btnaddcomment;
    private int agrnum;
    private int disnum;
    private int votenum;
    private int limitnum;
    private String nickname;
    private String commentvalue;
    ArrayList<String> arrayList = new ArrayList<String>();
    ArrayAdapter<String> Adapter;
    public UserDto userDto;
    public MoyeITServerClient moyeClient;
    public MoyeITServerService moyeService;
    Intent intent = getIntent();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detailmoim);

        textInform = (TextView) findViewById(R.id.text_inform);
        textTitle = (TextView) findViewById(R.id.text_title);
        textMuser = (TextView) findViewById(R.id.text_user);
        textDetail = (TextView) findViewById(R.id.text_detail);
        textCdate = (TextView) findViewById(R.id.text_date);
        textComment = (ListView) findViewById(R.id.text_comment);
        editComment = (EditText) findViewById(R.id.edit_comment);
        btnagree = (Button) findViewById(R.id.btn_agree);
        btndisagree = (Button) findViewById(R.id.btn_disagree);
        btnaddcomment = (Button) findViewById(R.id.btn_addcomment);
        Adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arrayList);
        moyeClient = new MoyeITServerClient(getApplicationContext());
        moyeService = moyeClient.getMoyeITService();
        userDto= UserDto.getInstance();
         /*
            server api 호출
         */
                    sid = "1";
                    no = "1";
                    //sid = intent.getExtras().getString("sid"); //나중에 수정하기(현재 참여하고 들어와 있는 스터디의 id를 받아와야함)
                    //no = intent.getExtras().getString("no"); //나중에 수정하기(모임게시글 리스트 중 클릭한 게시글의 no를 받아와야함)

                    Call<MoimDto> callMoimDetailInfo = moyeService.detailmoim(sid, no);
                        //상세조회
                        callMoimDetailInfo.enqueue(new Callback<MoimDto>() {
                        @Override
                        public void onResponse(Call<MoimDto> call, Response<MoimDto> response) {
                            mid = Integer.toString(response.body().getMid());
                            agrnum = response.body().getAgrnum();
                            disnum = response.body().getDisnum();
                            limitnum = response.body().getLimitnum();
                            textTitle.setText(response.body().getMoimtitle());
                            textDetail.setText(response.body().getContent());
                            textCdate.setText(response.body().getDate());
                            for(int i = 0; i < response.body().getList().size(); i++) {
                                arrayList.add(response.body().getList().get(i).getUser() + " : " + response.body().getList().get(i).getComment());
                            }
                            textComment.setAdapter(Adapter);
                            textMuser.setText(response.body().getMuser());

                            votenum = agrnum + disnum;
                            if(votenum >= limitnum){
                                if(agrnum > disnum){
                                    btnagree.setEnabled(false);
                                    btndisagree.setEnabled(false);
                                    btnagree.setBackgroundColor(Color.RED);
                                }else if(agrnum < disnum){
                                    btnagree.setEnabled(false);
                                    btndisagree.setEnabled(false);
                                    btndisagree.setBackgroundColor(Color.RED);
                                }else if(agrnum == disnum){
                                    btnagree.setEnabled(false);
                                    btndisagree.setEnabled(false);
                                    btnagree.setBackgroundColor(Color.RED);
                                    btndisagree.setBackgroundColor(Color.RED);
                                }
                            }
                        }
                        @Override
                        public void onFailure(Call<MoimDto> call, Throwable t) {
                            textTitle.setText("실패"+t.toString());
                        }
                    });
                    //투표
                        btnagree.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                            /*
                        server api 호출
                        */
                                vote = "true"; //true:찬성, false:반대
                                Call<MoimDto> callMoimVoteInfo = moyeService.votemoim(vote, mid);

                                callMoimVoteInfo.enqueue(new Callback<MoimDto>() {
                                    @Override
                                    public void onResponse(Call<MoimDto> call, Response<MoimDto> response) {
                                        textInform.setText(response.body().getState());
                                        Intent intent = new Intent(getApplicationContext(), MoimDetailActivity.class);
                                        startActivity(intent);
                                    }

                                    @Override
                                    public void onFailure(Call<MoimDto> call, Throwable t) {
                                        textInform.setText("실패" + t.toString());
                                    }
                                });
                            }
                        });
                        btndisagree.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                        /*
                                    server api 호출
                                    */
                                vote = "false"; //true:찬성, false:반대
                                Call<MoimDto> callMoimVoteInfo = moyeService.votemoim(vote, mid);

                                callMoimVoteInfo.enqueue(new Callback<MoimDto>() {
                                    @Override
                                    public void onResponse(Call<MoimDto> call, Response<MoimDto> response) {
                                        textInform.setText(response.body().getState());
                                        Intent intent = new Intent(getApplicationContext(), MoimDetailActivity.class);
                                        startActivity(intent);
                                    }

                                    @Override
                                    public void onFailure(Call<MoimDto> call, Throwable t) {
                                        textInform.setText("실패" + t.toString());
                                    }
                                });
                            }
                        });
                    //댓글작성(insert에서 에러가 남 fail뜸)
                    btnaddcomment.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                                                    /*
                                                server api 호출
                                                */
                            commentvalue = editComment.getText().toString();
                            nickname = userDto.getNickname();

                            if(commentvalue.equals("")){
                                Toast.makeText(MoimDetailActivity.this, "내용을 입력해주세요.", Toast.LENGTH_SHORT).show();
                                return;
                            }else {
                                Call<CmoimDto> callMoimCommentInfo = moyeService.commentmoim(nickname, commentvalue, mid);

                                callMoimCommentInfo.enqueue(new Callback<CmoimDto>() {
                                    @Override
                                    public void onResponse(Call<CmoimDto> call, Response<CmoimDto> response) {
                                        textInform.setText(response.body().getState());
                                        Intent intent = new Intent(getApplicationContext(), MoimDetailActivity.class);
                                        startActivity(intent);
                                    }

                                    @Override
                                    public void onFailure(Call<CmoimDto> call, Throwable t) {
                                        textInform.setText("실패" + t.toString());
                                    }
                                });
                            }
                        }
                    });
    }
}