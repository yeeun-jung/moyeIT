package com.example.moyeit.moyeitapp.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
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


public class MsMainActivity extends Activity {
    TextView nickName;
    ListView mstudyList;
    ArrayList<MyStudyDto> list;
    public MoyeITServerClient moyeClient;
    public MoyeITServerService moyeService;
    ListViewAdapter adapter;
    UserDto userDto;
    String sid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mystudy_main);
        userDto=UserDto.getInstance();
        moyeClient = new MoyeITServerClient(getApplicationContext());
        moyeService = moyeClient.getMoyeITService();

        nickName = (TextView)findViewById(R.id.u_name);
        mstudyList = (ListView)findViewById(R.id.u_list);

        Call<ListDto> myStudyList = moyeService.myStudyList(userDto.getPid());
        nickName.setText(String.valueOf(userDto.getNickname()));
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
        title.setText(src.get(position).getTitle());
        TextView nickname = (TextView) convertView.findViewById(R.id.nickname);
        nickname.setText(src.get(position).getNickname());
        TextView contnum = (TextView) convertView.findViewById(R.id.cont_limitnum);
        contnum.setText(String.valueOf(src.get(position).getContnum()) + "/" + String.valueOf(src.get(position).getLimitnum()));
        TextView sid = (TextView) convertView.findViewById(R.id.sid);
        sid.setText(String.valueOf(src.get(position).getSid()));

        return convertView;
    }

}