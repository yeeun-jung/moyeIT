package com.example.moyeit.moyeitapp.activity;

import android.app.Activity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.moyeit.moyeitapp.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Home on 2017-08-16.
 */

public class RankingMainActivity extends AppCompatActivity {

    ImageView image1, image2, image3;
    TextView rankingTitle1, rankingTitle2, rankingTitle3;
    TextView nickname1, nickname2, nickname3;
    private BackButtonExit backButtonExit;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ranking);

        image1 = (ImageView)findViewById(R.id.image1);
        image2 = (ImageView)findViewById(R.id.image2);
        image3 = (ImageView)findViewById(R.id.image3);

        rankingTitle1 = (TextView)findViewById(R.id.rankingTitle1);
        rankingTitle2 = (TextView)findViewById(R.id.rankingTitle2);
        rankingTitle3 = (TextView)findViewById(R.id.rankingTitle3);

        nickname1 = (TextView)findViewById(R.id.nickname1);
        nickname2 = (TextView)findViewById(R.id.nickname2);
        nickname3 = (TextView)findViewById(R.id.nickname3);

        backButtonExit = new BackButtonExit(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_ranking);
        setSupportActionBar(toolbar);
        ActionBar actionBar =getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.mipmap.noun_back);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        backButtonExit.onBackPressed();
    }
}