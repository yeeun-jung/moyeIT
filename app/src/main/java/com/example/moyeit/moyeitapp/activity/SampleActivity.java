package com.example.moyeit.moyeitapp.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.moyeit.moyeitapp.R;
import com.example.moyeit.moyeitapp.dto.UserDto;

/**
 * Created by yeeun on 2017-07-24.
 */

public class SampleActivity extends Activity{
    UserDto userDto;
    TextView textView;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sample);

        userDto=UserDto.getInstance();
        textView=(TextView)findViewById(R.id.sampleText);

        textView.setText(userDto.getEmail());
    }
}
