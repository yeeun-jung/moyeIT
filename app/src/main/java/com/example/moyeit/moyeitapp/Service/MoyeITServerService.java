package com.example.moyeit.moyeitapp.Service;
import com.example.moyeit.moyeitapp.dto.Study;
import com.example.moyeit.moyeitapp.dto.StudyDto;
import com.example.moyeit.moyeitapp.dto.UserDto;

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
    Call<StudyDto> studyRegi(@Field("nickname") String name, @Field("title") String title,
                             @Field("region") int re, @Field("limitnum") int num,
                             @Field("detail") String de);




}
