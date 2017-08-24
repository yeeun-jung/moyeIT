package com.example.moyeit.moyeitapp.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.moyeit.moyeitapp.Network.MoyeITServerClient;
import com.example.moyeit.moyeitapp.R;
import com.example.moyeit.moyeitapp.dto.ListDto;
import com.example.moyeit.moyeitapp.dto.MsDetailListDto;
import com.example.moyeit.moyeitapp.dto.MyStudyDto;
import com.example.moyeit.moyeitapp.Service.MoyeITServerService;
import com.example.moyeit.moyeitapp.dto.MyStudyDto;
import com.example.moyeit.moyeitapp.dto.UserDto;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by ga0 on 2017-07-24.
 */


public class MsMainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    ListView mstudyList;
    ArrayList<MyStudyDto> list;
    public MoyeITServerClient moyeClient;
    public MoyeITServerService moyeService;
    ListViewAdapter adapter;
    UserDto userDto;
    String sid;
    BackButtonExit backButtonExit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        userDto=UserDto.getInstance();
        moyeClient = new MoyeITServerClient(getApplicationContext());
        moyeService = moyeClient.getMoyeITService();

        mstudyList = (ListView)findViewById(R.id.u_list);
        backButtonExit = new BackButtonExit(this);

        Call<ListDto> myStudyList = moyeService.myStudyList(userDto.getPid());
        myStudyList.enqueue(new Callback<ListDto>() {
            @Override
            public void onResponse(Call<ListDto> call, Response<ListDto> response) {
                list = response.body().getList();
                adapter = new ListViewAdapter(MsMainActivity.this, R.layout.simple_list_item_4, list);
                mstudyList.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<ListDto> call, Throwable t) {

            }
        });

        mstudyList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView sidText = (TextView)view.findViewById(R.id.sid);
                sid= (String)sidText.getText();
                Intent intent = new Intent(getApplicationContext(),MsDetailActivity.class);
                intent.putExtra("sid", sid);
                startActivity(intent);

            }
        });

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        View nav_header = navigationView.getHeaderView(0);
        TextView textNick = (TextView) nav_header.findViewById(R.id.textViewNick);
        textNick.setText(userDto.getNickname());
        TextView textEmail = (TextView) nav_header.findViewById(R.id.textViewEmail);
        textEmail.setText(userDto.getEmail());
   }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            backButtonExit.onBackPressed();
        }
    }

/*
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
*/
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

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}

class ListViewAdapter extends BaseAdapter {
    Context maincon;
    LayoutInflater Inflater;
    ArrayList<MyStudyDto> src;
    int layout;

    public ListViewAdapter(Context context, int alayout, ArrayList<MyStudyDto> asrc) {
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

        TextView title = (TextView) convertView.findViewById(R.id.title);
        String[] val = src.get(position).getTitle().split("]");
        title.setText(val[2].replaceAll("\\[",""));
        TextView region = (TextView)convertView.findViewById(R.id.s_region);
        region.setText(val[0]+"]"+val[1]+"]");
        TextView nickname = (TextView) convertView.findViewById(R.id.nickname);
        nickname.setText(src.get(position).getNickname());
        TextView contnum = (TextView) convertView.findViewById(R.id.cont_limitnum);
        contnum.setText(String.valueOf(src.get(position).getContnum() + "/" + String.valueOf(src.get(position).getLimitnum())));
        TextView sid = (TextView) convertView.findViewById(R.id.sid);
        sid.setText(String.valueOf(src.get(position).getSid()));

        return convertView;
    }
}