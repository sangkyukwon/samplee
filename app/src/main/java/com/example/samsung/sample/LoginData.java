package com.example.samsung.sample;

import com.google.gson.annotations.SerializedName;

public class LoginData {
    @SerializedName("USER_EMAIL")
    String USER_EMAIL;

    @SerializedName("USER_PW")
    String USER_PW;

    public LoginData(String USER_EMAIL, String USER_PW) {
        this.USER_EMAIL= USER_EMAIL;
        this.USER_PW = USER_PW;
    }
}