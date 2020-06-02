package com.example.samsung.sample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class Ex extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ex);

//입력값 초기 6/1
        String strNickname, strProfile;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            TextView tvNickname = findViewById(R.id.kkoid);
            TextView tvProfile = findViewById(R.id.kko);

            Intent intent = getIntent();
            strNickname = intent.getStringExtra("name");
            strProfile = intent.getStringExtra("profile");

            tvNickname.setText(strNickname);
            tvProfile.setText(strProfile);



        }
}
