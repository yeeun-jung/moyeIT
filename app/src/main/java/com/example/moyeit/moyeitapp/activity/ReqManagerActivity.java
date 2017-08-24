package com.example.moyeit.moyeitapp.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.example.moyeit.moyeitapp.Network.MoyeITServerClient;
import com.example.moyeit.moyeitapp.R;
import com.example.moyeit.moyeitapp.Service.MoyeITServerService;
import com.example.moyeit.moyeitapp.dto.MyStudyDto;
import com.example.moyeit.moyeitapp.dto.UserDto;
import com.example.moyeit.moyeitapp.dto.reqManagerDtlDto;
import com.example.moyeit.moyeitapp.dto.reqManagerDto;

import org.w3c.dom.Text;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static java.sql.Types.NULL;

/**
 * Created by ga0 on 2017-08-13.
 */

public class ReqManagerActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    public MoyeITServerClient moyeClient;
    public MoyeITServerService moyeService;
    UserDto userDto;
    Call<reqManagerDto> r_list;
    ArrayList<reqManagerDtlDto> dtl_list;
    ReqManagerAdapter adapter;
    ListView list;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_req_m);

        moyeClient = new MoyeITServerClient(getApplicationContext());
        moyeService = moyeClient.getMoyeITService();


        userDto = UserDto.getInstance();
        final String pid = String.valueOf(userDto.getPid());

        list = (ListView)findViewById(R.id.rm_list);


        r_list = moyeService.reqMList(Integer.parseInt(pid), "", "");
        r_list.enqueue(new Callback<reqManagerDto>() {
            @Override
            public void onResponse(Call<reqManagerDto> call, Response<reqManagerDto> response) {
                dtl_list = response.body().getList();

                adapter = new ReqManagerAdapter(ReqManagerActivity.this, R.layout.req_manager_list, dtl_list);
                list.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<reqManagerDto> call, Throwable t) {

            }
        });


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout2);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view2);
        navigationView.setNavigationItemSelectedListener(this);
        View nav_header = navigationView.getHeaderView(0);
        TextView textNick = (TextView) nav_header.findViewById(R.id.textViewNick);
        textNick.setText(userDto.getNickname());
        TextView textEmail = (TextView) nav_header.findViewById(R.id.textViewEmail);
        textEmail.setText(userDto.getEmail());
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout2);
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

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout2);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}


class ReqManagerAdapter extends BaseAdapter{
    Context maincon;
    LayoutInflater Inflater;
    ArrayList<reqManagerDtlDto> src;
    String s_name;
    String s_aid;
    String s_content;
    int layout;

    public ReqManagerAdapter(Context context, int alayout, ArrayList<reqManagerDtlDto> asrc) {
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

        TextView title = (TextView) convertView.findViewById(R.id.rm_title);
        String[] val = src.get(position).getTitle().split("]");
        title.setText(val[2].replaceAll("\\[",""));
        TextView region = (TextView)convertView.findViewById(R.id.rm_region);
        region.setText(val[0]+"]"+val[1]+"]");
        TextView nickname = (TextView) convertView.findViewById(R.id.rm_name);
        nickname.setText(src.get(position).getNickname());
        TextView date = (TextView) convertView.findViewById(R.id.rm_date);
        date.setText(src.get(position).getDate());
        TextView aid = (TextView) convertView.findViewById(R.id.aid);
        aid.setText(String.valueOf(src.get(position).getAid()));
        TextView content = (TextView)convertView.findViewById(R.id.rm_content);
        content.setText(src.get(position).getContent());
        ImageButton dtl_Btn = (ImageButton)convertView.findViewById(R.id.dtl_Btn);
        dtl_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(maincon, reqMPopupActivity.class);
                s_name= src.get(pos).getNickname();
                s_aid= src.get(pos).getAid();
                s_content= src.get(pos).getContent();
                intent.putExtra("aid", s_aid);
                intent.putExtra("content", s_content);
                intent.putExtra("name", s_name);
                maincon.startActivity(intent);
            }
        });

        return convertView;
    }

}
