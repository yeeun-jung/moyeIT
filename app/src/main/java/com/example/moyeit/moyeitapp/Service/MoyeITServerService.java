package com.example.moyeit.moyeitapp.Service;
import com.example.moyeit.moyeitapp.dto.ListDto;
import com.example.moyeit.moyeitapp.dto.MsDetailDto;
import com.example.moyeit.moyeitapp.dto.MyStudyDto;
import com.example.moyeit.moyeitapp.dto.SrListDto;
import com.example.moyeit.moyeitapp.dto.StudyDetailDto;
import com.example.moyeit.moyeitapp.dto.StudyRegisterDTO;
import com.example.moyeit.moyeitapp.dto.UserDto;

import java.util.ArrayList;
import java.util.List;

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
    Call<StudyRegisterDTO> studyRegi(@Field("nickname") String nickname,
                                     @Field("title") String title,
                                     @Field("region") int re,
                                     @Field("limitnum") int li,
                                     @Field("detail") String de,
                                     @Field("subject") String subject,
                                     @Field("pid") int pid);

    @GET("mystudy/list.php")
    Call<ListDto> myStudyList(@Query("pid") int id);

    @GET("mystudy/moimlist.php")
    Call<MsDetailDto> msMoimList(@Query("sid") int id);

    @GET("study/list/search.php")
    Call<SrListDto> srList(@Query("search[]") String[] search, @Query("pid") int id);

    @GET("study/detail.php")
    Call<StudyDetailDto> getStudyDetail(@Query("sid") int sid);

    @GET("study/join.php")
    Call<StudyDetailDto>StudyJoin(@Query("pid") int pid, @Query("sid") int sid);


}
