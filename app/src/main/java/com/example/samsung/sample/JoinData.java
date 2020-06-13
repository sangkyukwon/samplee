package com.example.samsung.sample;

import com.google.gson.annotations.SerializedName;

public class JoinData {
    @SerializedName("USER_NAME")
    private String USER_NAME;

    @SerializedName("USER_EMAIL")
    private String USER_EMAIL;

    @SerializedName("USER_PW")
    private String USER_PW;


    @SerializedName("USER_AGE")
    private String USER_AGE;


    @SerializedName("USER_BIRTHDAY")
    private String USER_BIRTHDAY;

    @SerializedName("USER_PHONE")
    private String USER_PHONE;

    @SerializedName("USER_SEX")
    private String USER_SEX;

    public JoinData(String USER_NAME, String USER_EMAIL, String USER_PW, String USER_AGE, String USER_PHONE, String USER_SEX, String USER_BIRTHDAY)
    {
        this.USER_NAME = USER_NAME;
        this.USER_EMAIL = USER_EMAIL;
        this.USER_PW = USER_PW;
        this.USER_AGE= USER_AGE;
        this.USER_BIRTHDAY=USER_BIRTHDAY;
        this.USER_PHONE =USER_PHONE;
        this.USER_SEX = USER_SEX;
    }
}