package com.example.moyeit.moyeitapp.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

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

public class SrListActivity extends Activity{

    public MoyeITServerClient moyeClient;
    public MoyeITServerService moyeService;
    UserDto userDto;
    SrListViewAdapter adapter;
    ListView list;
    EditText searchText;
    Button searchBtn;
    String sid;
    String join;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sr_list);

        moyeClient = new MoyeITServerClient(getApplicationContext());
        moyeService = moyeClient.getMoyeITService();

        list = (ListView)findViewById(R.id.s_srList);
        searchText = (EditText)findViewById(R.id.s_searchText);
        searchBtn = (Button)findViewById(R.id.s_searchBtn);

        userDto = UserDto.getInstance();
        String pid = String.valueOf(userDto.getPid());

        Call<SrListDto> srList = moyeService.srList("",pid);
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

        list.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView sidText = (TextView)view.findViewById(R.id.sid);
                TextView joinText = (TextView)view.findViewById(R.id.join);
                sid= (String)sidText.getText();
                join = (String)joinText.getText();

                if(join.equals("true")){
                    Intent intent = new Intent(getApplicationContext(),MsDetailListDto.class);
                    intent.putExtra("sid", sid);
                    startActivity(intent);

                }
                else{
                    Intent intent = new Intent(getApplicationContext(),SampleActivity.class);
                    intent.putExtra("sid", sid);
                    startActivity(intent);}


            }
        });

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
