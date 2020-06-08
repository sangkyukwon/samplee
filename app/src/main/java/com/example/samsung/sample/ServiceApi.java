package com.example.samsung.sample;

import com.example.samsung.sample.JoinData;
import com.example.samsung.sample.JoinResponse;
import com.example.samsung.sample.LoginData;
import com.example.samsung.sample.LoginResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ServiceApi {
    @POST("/user/login")
    Call<LoginResponse> userLogin(@Body LoginData data);

    @POST("/user/join")
    Call<JoinResponse> userJoin(@Body JoinData data);
}