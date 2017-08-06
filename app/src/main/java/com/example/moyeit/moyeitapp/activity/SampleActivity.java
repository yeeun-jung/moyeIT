package com.example.moyeit.moyeitapp.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.moyeit.moyeitapp.R;
import com.example.moyeit.moyeitapp.dto.UserDto;

import org.w3c.dom.Text;

/**
 * Created by yeeun on 2017-07-24.
 */

public class SampleActivity extends Activity{
    UserDto userDto;
    TextView textView;
    TextView textView2;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sample);

        textView=(TextView)findViewById(R.id.sampleText);

        Intent intent = getIntent();
        String sid = intent.getExtras().getString("bid");

        textView.setText(sid);



    }
}
