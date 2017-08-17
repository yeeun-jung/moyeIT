package com.example.moyeit.moyeitapp.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.moyeit.moyeitapp.R;

/**
 * Created by yeeun on 2017-08-17.
 */

public class BackButtonExit extends Activity {

    private long backKeyPressedTime = 0;
    private Toast toast;

    private Activity activity;

    public BackButtonExit(Activity context) {
        this.activity = context;
    }

    @Override
    public void onBackPressed() {
        if (System.currentTimeMillis() > backKeyPressedTime + 2000) {
            backKeyPressedTime = System.currentTimeMillis();
            showGuide();
            return;
        }
        if (System.currentTimeMillis() <= backKeyPressedTime + 2000) {
            Intent t = new Intent(activity, LoginActivity.class);
            activity.startActivity(t);

            activity.moveTaskToBack(true);
            activity.finish();

            toast.cancel();
        }
    }

    public void showGuide() {
        toast = Toast.makeText(activity,
                "\'뒤로\'버튼을 한번 더 누르시면 종료됩니다.", Toast.LENGTH_SHORT);
        toast.show();
    }
}
