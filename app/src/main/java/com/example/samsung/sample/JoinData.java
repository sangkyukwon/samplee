package com.example.samsung.sample;

import com.google.gson.annotations.SerializedName;

public class JoinData {
    @SerializedName("USER_NAME")
    private String USER_NAME;

    @SerializedName("USER_EMAIL")
    private String USER_EMAIL;

    @SerializedName("USER_PW")
    private String USER_PW;

    public JoinData(String USER_NAME, String USER_EMAIL, String USER_PW) {
        this.USER_NAME = USER_NAME;
        this.USER_EMAIL = USER_EMAIL;
        this.USER_PW = USER_PW;
    }
}