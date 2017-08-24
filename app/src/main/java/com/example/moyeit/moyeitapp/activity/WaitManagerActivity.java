package com.example.moyeit.moyeitapp.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.moyeit.moyeitapp.Network.MoyeITServerClient;
import com.example.moyeit.moyeitapp.R;
import com.example.moyeit.moyeitapp.Service.MoyeITServerService;
import com.example.moyeit.moyeitapp.dto.UserDto;
import com.example.moyeit.moyeitapp.dto.reqManagerDtlDto;
import com.example.moyeit.moyeitapp.dto.reqManagerDto;
import com.example.moyeit.moyeitapp.dto.waitManagerDtlDto;
import com.example.moyeit.moyeitapp.dto.waitManagerDto;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Home on 2017-08-17.
 */

public class WaitManagerActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    public MoyeITServerClient moyeClient;
    public MoyeITServerService moyeService;
    UserDto userDto;
    Call<waitManagerDto> w_list;
    ArrayList<waitManagerDtlDto> w_dtl_list;
    WaitManagerAdapter adapter;
    ListView list;
    int pid;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wait_m);

        moyeClient = new MoyeITServerClient(getApplicationContext());
        moyeService = moyeClient.getMoyeITService();

        userDto = UserDto.getInstance();
        final String pid = String.valueOf(userDto.getPid());

        list = (ListView) findViewById(R.id.wm_list);

        Intent intent = getIntent();

        w_list = moyeService.waitMList(Integer.parseInt(pid));
        w_list.enqueue(new Callback<waitManagerDto>() {
            @Override
            public void onResponse(Call<waitManagerDto> call, Response<waitManagerDto> response) {
                w_dtl_list = response.body().getList();

                adapter = new WaitManagerAdapter(WaitManagerActivity.this, R.layout.wait_manager_list, w_dtl_list);
                list.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<waitManagerDto> call, Throwable t) {

            }
        });

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar3);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout3);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view3);
        navigationView.setNavigationItemSelectedListener(this);
        View nav_header = navigationView.getHeaderView(0);
        TextView textNick = (TextView) nav_header.findViewById(R.id.textViewNick);
        textNick.setText(userDto.getNickname());
        TextView textEmail = (TextView) nav_header.findViewById(R.id.textViewEmail);
        textEmail.setText(userDto.getEmail());
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout3);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        Intent intent;

        if (id == R.id.nav_camera) {
            // Handle the camera action
            intent = new Intent(getApplicationContext(),MsMainActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_gallery) {
            intent = new Intent(getApplicationContext(),SrListActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_slideshow) {
            intent = new Intent(getApplicationContext(),RankingMainActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_manage) {
            intent = new Intent(getApplicationContext(),LoginActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_share) {
            intent = new Intent(getApplicationContext(),ReqManagerActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_send) {
            intent = new Intent(getApplicationContext(),WaitManagerActivity.class);
            startActivity(intent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout3);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}

class WaitManagerAdapter extends BaseAdapter {
    Context maincon;
    LayoutInflater Inflater;
    ArrayList<waitManagerDtlDto> src;
    int layout;

    public WaitManagerAdapter(Context context, int alayout, ArrayList<waitManagerDtlDto> asrc) {
        maincon = context;
        Inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
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

        if (convertView == null) {
            convertView = Inflater.inflate(layout, parent, false);
        }

        TextView title = (TextView) convertView.findViewById(R.id.wm_title);
        String[] val = src.get(position).getTitle().split("]");
        title.setText(val[2].replaceAll("\\[",""));
        TextView region = (TextView)convertView.findViewById(R.id.wm_region);
        region.setText(val[0]+"]"+val[1]+"]");
        /*TextView title = (TextView) convertView.findViewById(R.id.wm_title);
        title.setText(src.get(position).getTitle());*/
        TextView nickname = (TextView) convertView.findViewById(R.id.wm_name);
        nickname.setText(src.get(position).getNickname());
        TextView date = (TextView) convertView.findViewById(R.id.wm_date);
        date.setText(src.get(position).getDate());
        TextView agree = (TextView) convertView.findViewById(R.id.wm_agree);
        if(src.get(position).getAgree().equals("")){
            agree.setText("대기중");
        }else if((src.get(position).getAgree().equals("true"))) {
            agree.setText("수락");
        }else{
            agree.setText("거절");
        }
        return convertView;
    }
}