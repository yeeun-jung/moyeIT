package com.example.moyeit.moyeitapp.activity;

import android.app.Activity;
import android.app.TabActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;
import android.widget.TextView;

import com.example.moyeit.moyeitapp.Network.MoyeITServerClient;
import com.example.moyeit.moyeitapp.R;
import com.example.moyeit.moyeitapp.Service.MoyeITServerService;
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

public class MsDetailActivity extends Activity {
    public MoyeITServerClient moyeClient;
    public MoyeITServerService moyeService;
    TextView detail_region;
    TextView detail_title;
    TextView detail_nickname;
    TextView detail_conlimitnum;
    GlobalMethod global;
    ListView detail_list;
    msDetailListViewAdapter adapter;
    ArrayList<MsDetailListDto> list;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ms_detail);

        moyeClient = new MoyeITServerClient(getApplicationContext());
        moyeService = moyeClient.getMoyeITService();

        detail_region = (TextView)findViewById(R.id.detail_region);
        detail_title = (TextView)findViewById(R.id.detail_title);
        detail_nickname = (TextView)findViewById(R.id.detail_nickname);
        detail_conlimitnum = (TextView)findViewById(R.id.detail_conlimitnum);
        detail_list = (ListView)findViewById(R.id.detail_list);

        Intent intent = getIntent();
        int sid = Integer.parseInt(intent.getExtras().getString("sid"));

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
                detail_region.setText(String.valueOf("<"+global.toRegion(response.body().getRegion())+">"));
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
        no.setText(String.valueOf(position+1));
        TextView title = (TextView)convertView.findViewById(R.id.ms_detail_title);
        title.setText(src.get(position).getMoimtitle());
        TextView user = (TextView)convertView.findViewById(R.id.ms_detail_user);
        user.setText(src.get(position).getMuser());
        TextView date = (TextView)convertView.findViewById(R.id.ms_detail_date);
        date.setText(src.get(position).getDate());


        return convertView;
    }
}
