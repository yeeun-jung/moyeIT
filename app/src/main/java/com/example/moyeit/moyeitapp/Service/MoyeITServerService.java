package com.example.moyeit.moyeitapp.Service;
import com.example.moyeit.moyeitapp.dto.BrdDto;
import com.example.moyeit.moyeitapp.dto.CmoimDto;
import com.example.moyeit.moyeitapp.dto.MoimDto;
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
    @POST("mystudy/moim/add.php")
    Call<MoimDto> addmoim(@Field("moimtitle") String moimtitle, @Field("content") String content, @Field("limitnum") String limitnum, @Field("pid") String pid, @Field("sid") String sid);

    @GET("mystudy/moim/detail.php")
    Call<MoimDto> detailmoim(@Query("sid") String sid, @Query("no") String no);

    @FormUrlEncoded
    @POST("mystudy/moim/vote.php")
    Call<CmoimDto> commentmoim(@Field("nickname") String nickname, @Field("comment") String comment, @Field("mid") String mid);

    @FormUrlEncoded
    @POST("mystudy/moim/vote.php")
    Call<MoimDto> votemoim(@Field("vote") String vote, @Field("mid") String mid);

    @FormUrlEncoded
    @POST("mystudy/brd/add.php")
    Call<BrdDto> addbrd(@Field("brdtitle") String brdtitle, @Field("content") String content, @Field("nickname") String nickname, @Field("sid") String sid);

    @GET("mystudy/brd/detail.php")
    Call<BrdDto> detailbrd(@Query("bid") String bid, @Query("sid") String sid);

    /*@GET("mystudy/moim/add.php")
    Call<MoimDto> addmoim();*/

}
