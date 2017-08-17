package com.example.moyeit.moyeitapp.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.moyeit.moyeitapp.Network.MoyeITServerClient;
import com.example.moyeit.moyeitapp.R;
import com.example.moyeit.moyeitapp.Service.MoyeITServerService;
import com.example.moyeit.moyeitapp.dto.CmoimDto;
import com.example.moyeit.moyeitapp.dto.MoimDto;
import com.example.moyeit.moyeitapp.dto.UserDto;

import java.util.ArrayList;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by alice on 2017-07-27.
 */

public class MoimDetailActivity extends AppCompatActivity {
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
    ArrayList<HashMap<String, String>> arrayList = new ArrayList<HashMap<String, String>>();
    HashMap<String, String> item;
    SimpleAdapter Adapter;
    public UserDto userDto;
    public MoyeITServerClient moyeClient;
    public MoyeITServerService moyeService;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detailmoim);

        textTitle = (TextView) findViewById(R.id.text_title);
        textMuser = (TextView) findViewById(R.id.text_user);
        textDetail = (TextView) findViewById(R.id.text_detail);
        textCdate = (TextView) findViewById(R.id.text_date);
        textComment = (ListView) findViewById(R.id.text_comment);
        editComment = (EditText) findViewById(R.id.edit_comment);
        btnagree = (Button) findViewById(R.id.btn_agree);
        btndisagree = (Button) findViewById(R.id.btn_disagree);
        btnaddcomment = (Button) findViewById(R.id.btn_addcomment);
        Adapter = new SimpleAdapter(
                this, arrayList, android.R.layout.simple_list_item_2,
                new String[]{"item 1", "item 2"},
                new int[]{android.R.id.text1, android.R.id.text2});
        textComment.setAdapter(Adapter);
        moyeClient = new MoyeITServerClient(getApplicationContext());
        moyeService = moyeClient.getMoyeITService();
        userDto = UserDto.getInstance();
        Intent intent = getIntent();
        sid = intent.getExtras().getString("sid"); //나중에 수정하기(현재 참여하고 들어와 있는 스터디의 id를 받아와야함)
        no = intent.getExtras().getString("no"); //나중에 수정하기(모임게시글 리스트 중 클릭한 게시글의 no를 받아와야함)
         /*
            server api 호출
         */

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
                /*for(int i = 0; i < response.body().getList().size(); i++) {
                    arrayList.add(response.body().getList().get(i).getUser() + " : " + response.body().getList().get(i).getComment());
                }*/
                for (int i = 0; i < response.body().getList().size(); i++) {
                    item = new HashMap<String, String>();
                    item.put("item 1", response.body().getList().get(i).getUser() + " : " + response.body().getList().get(i).getComment());
                    item.put("item 2", response.body().getList().get(i).getDatetime());
                    arrayList.add(item);
                    //arrayList.add(response.body().getList().get(i).getDatetime()+"\n"+response.body().getList().get(i).getUser() + " : " + response.body().getList().get(i).getComment());
                }
                textComment.setAdapter(Adapter);
                listViewHeightSet(Adapter, textComment); //이 곳에서 리스트뷰 높이 설정
                textMuser.setText(response.body().getMuser());
                int num = limitnum / 2;
                votenum = agrnum + disnum;
                if (votenum > 0) {
                    if (agrnum > num) {
                        btnagree.setEnabled(false);
                        btndisagree.setEnabled(false);
                        btnagree.setBackgroundColor(getResources().getColor(R.color.colorAccent));
                    } else if (disnum + 1 > num) {
                        btnagree.setEnabled(false);
                        btndisagree.setEnabled(false);
                        btndisagree.setBackgroundColor(getResources().getColor(R.color.colorAccent));
                    } else if (agrnum == disnum && votenum == limitnum) {
                        btnagree.setEnabled(false);
                        btndisagree.setEnabled(false);
                        btnagree.setBackgroundColor(getResources().getColor(R.color.colorAccent));
                        btndisagree.setBackgroundColor(getResources().getColor(R.color.colorAccent));
                    }
                }
            }

            @Override
            public void onFailure(Call<MoimDto> call, Throwable t) {
                textTitle.setText("실패" + t.toString());
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
                        Intent intent = new Intent(getApplicationContext(), MoimDetailActivity.class);
                        // 연주가 만든 액티비티 이름으로 바꾸고 돌리기(테스트는 끝남 no랑 sid 넘겨주는 테스트는 끝남)
                        intent.putExtra("sid", sid);
                        intent.putExtra("no", no);
                        startActivity(intent);
                    }

                    @Override
                    public void onFailure(Call<MoimDto> call, Throwable t) {
                        textTitle.setText("실패" + t.toString());
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
                        Intent intent = new Intent(getApplicationContext(), MoimDetailActivity.class);
                        // 연주가 만든 액티비티 이름으로 바꾸고 돌리기(테스트는 끝남 no랑 sid 넘겨주는 테스트는 끝남)
                        intent.putExtra("sid", sid);
                        intent.putExtra("no", no);
                        startActivity(intent);
                    }

                    @Override
                    public void onFailure(Call<MoimDto> call, Throwable t) {
                        textTitle.setText("실패" + t.toString());
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

                if (commentvalue.equals("")) {
                    Toast.makeText(MoimDetailActivity.this, "내용을 입력해주세요.", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    Call<CmoimDto> callMoimCommentInfo = moyeService.commentmoim(nickname, commentvalue, mid);

                    callMoimCommentInfo.enqueue(new Callback<CmoimDto>() {
                        @Override
                        public void onResponse(Call<CmoimDto> call, Response<CmoimDto> response) {
                            Intent intent = new Intent(getApplicationContext(), MoimDetailActivity.class);
                            // 연주가 만든 액티비티 이름으로 바꾸고 돌리기(테스트는 끝남 no랑 sid 넘겨주는 테스트는 끝남)
                            intent.putExtra("sid", sid);
                            intent.putExtra("no", no);
                            startActivity(intent);
                        }

                        @Override
                        public void onFailure(Call<CmoimDto> call, Throwable t) {
                            textTitle.setText("실패" + t.toString());
                        }
                    });
                }
            }
        });

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_detail_moim);
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

    //리스트뷰 아이템 개수만큼 높이 동적으로 조정하는 곳
    private void listViewHeightSet(android.widget.Adapter listAdapter, ListView listview) {
        int totalHeight = 0;
        for (int i = 0; i < listAdapter.getCount(); i++) {
            View listItem = listAdapter.getView(i, null, listview);
            listItem.measure(0, 0);
            totalHeight += listItem.getMeasuredHeight();
        }
        ViewGroup.LayoutParams params = listview.getLayoutParams();
        params.height = totalHeight + (listview.getDividerHeight() * (listAdapter.getCount() - 1));
        listview.setLayoutParams(params);
    }
}