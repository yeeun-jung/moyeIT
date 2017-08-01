package com.example.moyeit.moyeitapp.Service;

import com.example.moyeit.moyeitapp.dto.RegisterDto;
import com.example.moyeit.moyeitapp.dto.StudyDetailDto;
import com.example.moyeit.moyeitapp.dto.UserDto;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.*;

/**
 * Created by alice on 2017-07-21.
 */

public interface MoyeITServerService {

    @FormUrlEncoded
    @POST("user/login.php")
    Call<UserDto> login(@Field("email") String id);

    @FormUrlEncoded
    @POST("study/register.php")
    Call<RegisterDto> studyRegi(@Field("nickname") String nickname,
                               @Field("title") String title,
                               @Field("region") int re,
                               @Field("limitnum") int li,
                               @Field("detail") String de);

    @GET("study/detail.php")
    Call<ResponseBody>getComment(@Query("pid") int pid, @Query("sid") int sid);

//    @GET("/api.php")
//    Call<ServerResponse> get(
//            @Query("method") String method,
//            @Query("username") String username,
//            @Query("password") String password
//    );
}