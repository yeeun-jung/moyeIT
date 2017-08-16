package com.example.moyeit.moyeitapp.Service;

import android.app.Service;
import android.util.Log;
import android.widget.Toast;

import com.example.moyeit.moyeitapp.Network.MoyeITServerClient;
import com.example.moyeit.moyeitapp.Service.MoyeITServerService;
import com.example.moyeit.moyeitapp.activity.LoginActivity;
import com.example.moyeit.moyeitapp.dto.UserDto;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by yeeun on 2017-08-16.
 */
public class MyFirebaseInstanceIDService extends FirebaseInstanceIdService {
    private static final String TAG = "MyFirebaseIIDService";
    public MoyeITServerClient moyeClient;
    public MoyeITServerService moyeService;

    // [START refresh_token]
    @Override
    public void onTokenRefresh() {
        // Get updated InstanceID token.
        String token = FirebaseInstanceId.getInstance().getToken();
/*
        sendRegistrationToServer(token);

    }
    private void sendRegistrationToServer(final String token) {
        moyeClient = new MoyeITServerClient(getApplicationContext());
        moyeService = moyeClient.getMoyeITService();
        Call<UserDto> callUserInfo = moyeService.sendFCM(token);

        callUserInfo.enqueue(new Callback<UserDto>() {
            @Override
            public void onResponse(Call<UserDto> call, Response<UserDto> response) {
                Log.i("jung_id",token);
            }

            @Override
            public void onFailure(Call<UserDto> call, Throwable t) {
Log.i("jung_id","fdfd");
            }


        });
*/    }


}
