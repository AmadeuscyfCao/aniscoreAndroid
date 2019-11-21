package com.example.aniscoreandroid.utils;

import com.example.aniscoreandroid.model.bangumiApi.BangumiDetail;
import com.example.aniscoreandroid.model.bangumiList.BangumiListResponse;
import com.example.aniscoreandroid.model.bangumiListScore.BangumiBriefScoreResponse;
import com.example.aniscoreandroid.model.bangumiListScore.BangumiListScoreResponse;
import com.example.aniscoreandroid.model.bangumi.BangumiResponse;
import com.example.aniscoreandroid.model.user.AuthResponse;
import com.example.aniscoreandroid.model.user.UserResponse;

import java.util.HashMap;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.HTTP;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;

public interface ServerCall {
    // get bangumi detail by id
    @GET("/api/bangumi/{bangumiId}")
    Call<BangumiResponse> getBangumiDetailById(@Path("bangumiId") String bangumiId);

    // get top bangumiNumber of bangumi with highest total score
    @GET("/api/bangumiList/rank/{bangumiNumber}")
    Call<BangumiListScoreResponse> getBangumiRank(@Path("bangumiNumber") int bangumiNumber);

    // get bangumi brief information by id
    @GET("/api/bangumiList/{bangumiId}")
    Call<BangumiBriefScoreResponse> getBangumiBriefById(@Path("bangumiId") String bangumiId);

    // get 10 bangumis of given year and season
    @GET("/api/bangumi/{year}/{season}/limit")
    Call<BangumiListResponse> getBangumiOfYearSeasonLimit(@Path("year") String year, @Path("season") String season);

    // get all bangumis of given year and season
    @GET("/api/bangumi/{year}/{season}")
    Call<BangumiListResponse> getBangumiOfYearSeason(@Path("year") String year, @Path("season") String season);

    // login
    @Headers("Content-Type: application/json")
    @POST("/api/auth/login")
    Call<AuthResponse> login(@Body HashMap<String, String> input);

    // logout
    @POST("/api/auth/logout")
    Call<AuthResponse> logout();

    // get user by id
    @GET("/api/user/{userId}")
    Call<UserResponse> getUserById(@Path("userId") String userId);

    // update current user avatar
    @Multipart
    @PUT("/api/avatar/{userId}")
    Call<UserResponse> updateAvatarById(@Path("userId") String userId, @Part MultipartBody.Part avatar);

    // update current background avatar
    @Multipart
    @PUT("/api/background/{userId}")
    Call<UserResponse> updateBackgroundById(@Path("userId") String userId, @Part MultipartBody.Part background);

    // user with userId follow the user with id given in body
    @Headers("Content-Type: application/json")
    @PUT("/api/user/follow/{userId}")
    Call<UserResponse> followUserById(@Path("userId") String userId, @Body HashMap<String, String> followId);

    // user with userId unfollow the user with id given in body
    @HTTP(method = "DELETE", path = "api/user/unfollow/{userId}", hasBody = true)
    Call<UserResponse> unFollowUserById(@Path("userId") String userId, @Body HashMap<String, String> unFollowId);

    // get bangumi detail by anime id
    @GET("anime/{bangumiId}")
    Call<BangumiDetail> getBangumiByIdApi(@Path("bangumiId") String bangumiId);
}