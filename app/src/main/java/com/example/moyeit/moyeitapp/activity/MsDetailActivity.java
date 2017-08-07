package com.example.moyeit.moyeitapp.activity;

import android.app.Activity;
import android.app.TabActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;
import android.widget.TextView;

import com.example.moyeit.moyeitapp.Network.MoyeITServerClient;
import com.example.moyeit.moyeitapp.R;
import com.example.moyeit.moyeitapp.Service.MoyeITServerService;
import com.example.moyeit.moyeitapp.dto.BoardDetailListDto;
import com.example.moyeit.moyeitapp.dto.BoardListDto;
import com.example.moyeit.moyeitapp.dto.MsDetailDto;
import com.example.moyeit.moyeitapp.dto.MsDetailListDto;
import com.example.moyeit.moyeitapp.dto.MyStudyDto;

import org.w3c.dom.Text;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by ga0 on 2017-07-27.
 */

public class MsDetailActivity extends AppCompatActivity {
    public MoyeITServerClient moyeClient;
    public MoyeITServerService moyeService;
    TextView detail_title;
    TextView detail_nickname;
    TextView detail_conlimitnum;
    ListView detail_list;
    ListView board_list;
    msDetailListViewAdapter adapter;
    BdListViewAdapter badapter;
    ArrayList<MsDetailListDto> list;
    ArrayList<BoardDetailListDto> blist;
    int sid;
    String bid;
    String no;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ms_detail);

        moyeClient = new MoyeITServerClient(getApplicationContext());
        moyeService = moyeClient.getMoyeITService();

        detail_title = (TextView)findViewById(R.id.detail_title);
        detail_nickname = (TextView)findViewById(R.id.detail_nickname);
        detail_conlimitnum = (TextView)findViewById(R.id.detail_conlimitnum);
        detail_list = (ListView)findViewById(R.id.detail_list);
        board_list = (ListView)findViewById(R.id.board_list);

        Intent intent = getIntent();
        sid = Integer.parseInt(intent.getExtras().getString("sid"));

        //floating button (하단의 +버튼)
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab1);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        TabHost tabHost=(TabHost)findViewById(R.id.tabhost);
        tabHost.setup();

        TabSpec spec1 = tabHost.newTabSpec("Tab1");
        spec1.setContent(R.id.tab1);
        spec1.setIndicator(getString(R.string.tab1));
        tabHost.addTab(spec1);

        TabSpec spec2 = tabHost.newTabSpec("Tab2");
        spec2.setContent(R.id.tab2);
        spec2.setIndicator(getString(R.string.tab2));
        tabHost.addTab(spec2);

        tabHost.setCurrentTab(0);
        final Call<MsDetailDto> detailList = moyeService.msMoimList(sid);
        detailList.enqueue(new Callback<MsDetailDto>() {
            @Override
            public void onResponse(Call<MsDetailDto> call, Response<MsDetailDto> response) {
                list = response.body().getList();
                detail_title.setText(response.body().getTitle());
                detail_nickname.setText(response.body().getNickname());
                detail_conlimitnum.setText(response.body().getContnum()+"/"+response.body().getLimitnum());
                adapter = new msDetailListViewAdapter(MsDetailActivity.this, R.layout.ms_detail_list, list);
                detail_list.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<MsDetailDto> call, Throwable t) {
                Log.i("실패","실패.........");
            }
        });

        final Call<BoardListDto> boardList = moyeService.bdList(sid);
        boardList.enqueue(new Callback<BoardListDto>() {
            @Override
            public void onResponse(Call<BoardListDto> call, Response<BoardListDto> response) {
                blist = response.body().getList();
                badapter = new BdListViewAdapter(MsDetailActivity.this, R.layout.bd_detail_list, blist);
                board_list.setAdapter(badapter);
            }

            @Override
            public void onFailure(Call<BoardListDto> call, Throwable t) {

            }
        });

        detail_list.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView no_Text = (TextView)view.findViewById(R.id.ms_detail_no);
                no = no_Text.getText().toString();
                Intent intent = new Intent(getApplicationContext(),SampleActivity.class);
                // 연주가 만든 액티비티 이름으로 바꾸고 돌리기(테스트는 끝남 no랑 sid 넘겨주는 테스트는 끝남)
                intent.putExtra("sid", String.valueOf(sid));
                intent.putExtra("no",no);
                startActivity(intent);
            }
        });

        board_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView bid_Text = (TextView)view.findViewById(R.id.bd_detail_bid);
                bid = bid_Text.getText().toString();
                Intent intent = new Intent(getApplicationContext(), SampleActivity.class);
                // 연주가 만든 액티비티 이름으로 바꾸고 돌리기(테스트는 끝남 bid 넘겨주는 테스트는 끝남)
                intent.putExtra("bid", bid);
                startActivity(intent);
            }
        });
    }
}


class msDetailListViewAdapter extends BaseAdapter {
    Context maincon;
    LayoutInflater Inflater;
    ArrayList<MsDetailListDto> src;
    int layout;

    public msDetailListViewAdapter(Context context, int alayout, ArrayList<MsDetailListDto> asrc){
        maincon = context;
        Inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        src = asrc;
        layout = alayout;
    }

    @Override
    public int getCount() {
        return src.size();
    }

    @Override
    public Object getItem(int position) {
        return src.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final int pos = position;
        if(convertView ==null){
            convertView = Inflater.inflate(layout, parent,false);
        }

        TextView no = (TextView)convertView.findViewById(R.id.ms_detail_no);
        no.setText(src.get(position).getNo());
        TextView title = (TextView)convertView.findViewById(R.id.ms_detail_title);
        title.setText(src.get(position).getMoimtitle());
        TextView num = (TextView)convertView.findViewById(R.id.ms_detail_num);
        num.setText(String.valueOf(src.get(position).getAgrnum())+"/"+String.valueOf(src.get(position).getLimitnum()));
        TextView user = (TextView)convertView.findViewById(R.id.ms_detail_user);
        user.setText(src.get(position).getMuser());
        TextView date = (TextView)convertView.findViewById(R.id.ms_detail_date);
        date.setText(src.get(position).getDate());


        return convertView;
    }
}


class BdListViewAdapter extends BaseAdapter{
    Context maincon;
    LayoutInflater Inflater;
    ArrayList<BoardDetailListDto> src;
    int layout;

    public BdListViewAdapter(Context context, int alayout, ArrayList<BoardDetailListDto> asrc){
        maincon = context;
        Inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        src = asrc;
        layout = alayout;
    }

    @Override
    public int getCount() {
        return src.size();
    }

    @Override
    public Object getItem(int position) {
        return src.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final int pos = position;
        if(convertView ==null){
            convertView = Inflater.inflate(layout, parent,false);
        }

        TextView bd_detail_no = (TextView)convertView.findViewById(R.id.bd_detail_no);
        bd_detail_no.setText(String.valueOf(src.size()-position));
        TextView bd_detail_title = (TextView)convertView.findViewById(R.id.bd_detail_title);
        bd_detail_title.setText(src.get(position).getTitle());
        TextView bd_detail_date = (TextView)convertView.findViewById(R.id.bd_detail_date);
        bd_detail_date.setText(src.get(position).getDate());
        TextView bd_detail_name = (TextView)convertView.findViewById(R.id.bd_detail_name);
        bd_detail_name.setText(src.get(position).getNickname());
        TextView bd_detail_bid = (TextView)convertView.findViewById(R.id.bd_detail_bid);
        bd_detail_bid.setText(src.get(position).getBid());

        return convertView;
    }
}