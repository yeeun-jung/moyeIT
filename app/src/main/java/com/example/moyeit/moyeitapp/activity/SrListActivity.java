package com.example.moyeit.moyeitapp.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.moyeit.moyeitapp.Network.MoyeITServerClient;
import com.example.moyeit.moyeitapp.R;
import com.example.moyeit.moyeitapp.Service.MoyeITServerService;
import com.example.moyeit.moyeitapp.dto.MsDetailListDto;
import com.example.moyeit.moyeitapp.dto.MyStudyDto;
import com.example.moyeit.moyeitapp.dto.SrListDetailDto;
import com.example.moyeit.moyeitapp.dto.SrListDto;
import com.example.moyeit.moyeitapp.dto.UserDto;

import org.w3c.dom.Text;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by ga0 on 2017-08-03.
 */

public class SrListActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    public MoyeITServerClient moyeClient;
    public MoyeITServerService moyeService;
    UserDto userDto;
    SrListViewAdapter adapter;
    ListView list;
    EditText searchText;
    Button searchBtn;
    String sid;
    String join;
    String[] searchval;
    Call < SrListDto > srList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_sr);

        moyeClient = new MoyeITServerClient(getApplicationContext());
        moyeService = moyeClient.getMoyeITService();


        list = (ListView) findViewById(R.id.s_srList);
        searchText = (EditText) findViewById(R.id.s_searchText);
        searchBtn = (Button) findViewById(R.id.s_searchBtn);

        userDto = UserDto.getInstance();
        final String pid = String.valueOf(userDto.getPid());

       srList = moyeService.srList(searchval, userDto.getPid());


        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String str=searchText.getText().toString().trim();
               searchval =str.split("\\s+");
               srList = moyeService.srList(searchval, userDto.getPid());
                srList.enqueue(new Callback<SrListDto>() {
                    @Override
                    public void onResponse(Call<SrListDto> call, Response<SrListDto> response) {
                        ArrayList<SrListDetailDto> detailList = response.body().getList();
                        adapter = new SrListViewAdapter(SrListActivity.this, R.layout.simple_list_item_4, detailList);
                        list.setAdapter(adapter);
                        if(response.body().getList().toString().equals("[]")) {
                            Toast.makeText(SrListActivity.this, "검색어와 일치하는 스터디가 없습니다.", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<SrListDto> call, Throwable t) {

                    }
                });

            }
        });

        srList.enqueue(new Callback<SrListDto>() {
            @Override
            public void onResponse(Call<SrListDto> call, Response<SrListDto> response) {
                ArrayList<SrListDetailDto> detailList = response.body().getList();
                adapter = new SrListViewAdapter(SrListActivity.this, R.layout.simple_list_item_4, detailList);
                list.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<SrListDto> call, Throwable t) {

            }
        });

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView sidText = (TextView) view.findViewById(R.id.sid);
                TextView joinText = (TextView) view.findViewById(R.id.join);

                sid = (String) sidText.getText();
                join = (String) joinText.getText();


                //가입한 스터디
                if (join.equals("true")) {
                    Intent intent = new Intent(getApplicationContext(), MsDetailActivity.class);
                    intent.putExtra("sid", sid);
                    startActivity(intent);

                }
                //가입 안 한 스터디 디테일 뷰
                else {
                    Intent intent = new Intent(getApplicationContext(), StudyDetailActivity.class);
                    intent.putExtra("sid", sid);
                    intent.putExtra("join",join);
                    startActivity(intent);
                }


            }
        });

        //툴바, 네브바, 플로팅 버튼
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar1);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout1);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view1);
        navigationView.setNavigationItemSelectedListener(this);
        View nav_header = navigationView.getHeaderView(0);
        TextView textNick = (TextView) nav_header.findViewById(R.id.textViewNick);
        textNick.setText(userDto.getNickname());
        TextView textEmail = (TextView) nav_header.findViewById(R.id.textViewEmail);
        textEmail.setText(userDto.getEmail());

        getSupportActionBar().setDisplayShowTitleEnabled(false);

        //floating button (하단의 +버튼)
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab_sr);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),StudyRegiActivity.class);
                startActivity(intent);
            }
        });



    }
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout1);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
    /*
        @Override
        public boolean onCreateOptionsMenu(Menu menu) {
            // Inflate the menu; this adds items to the action bar if it is present.
            getMenuInflater().inflate(R.menu.main, menu);
           eturn true;
        }
    /*
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

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {
            intent = new Intent(getApplicationContext(),ReqManagerActivity.class);
            startActivity(intent);

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout1);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}


class SrListViewAdapter extends BaseAdapter {
    Context maincon;
    LayoutInflater Inflater;
    ArrayList<SrListDetailDto> src;
    int layout;

    public SrListViewAdapter(Context context, int alayout, ArrayList<SrListDetailDto> asrc) {
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
        title.setText(src.get(position).getTitle());
        TextView nickname = (TextView) convertView.findViewById(R.id.nickname);
        nickname.setText(src.get(position).getNickname());
        TextView contnum = (TextView) convertView.findViewById(R.id.cont_limitnum);
        contnum.setText(String.valueOf(src.get(position).getContnum()) + "/" + String.valueOf(src.get(position).getLimitnum()));
        TextView sid = (TextView) convertView.findViewById(R.id.sid);
        sid.setText(String.valueOf(src.get(position).getSid()));
        TextView join = (TextView) convertView.findViewById(R.id.join);
        join.setText(src.get(position).getJoin());

        return convertView;
    }

}
