package com.example.samsung.sample;

import com.google.gson.annotations.SerializedName;

public class JoinData {
    @SerializedName("USER_NAME")
    private String userName;

    @SerializedName("USER_ID")
    private String userEmail;

    @SerializedName("USER_PW")
    private String userPwd;

    public JoinData(String userName, String userEmail, String userPwd) {
        this.userName = userName;
        this.userEmail = userEmail;
        this.userPwd = userPwd;
    }
}