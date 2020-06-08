package com.example.samsung.sample;

import com.google.gson.annotations.SerializedName;

public class LoginData {
    @SerializedName("USER_ID")
    String userEmail;

    @SerializedName("USER_PW")
    String userPwd;

    public LoginData(String userEmail, String userPwd) {
        this.userEmail = userEmail;
        this.userPwd = userPwd;
    }
}