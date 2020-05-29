package com.example.samsung.sample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class emaillogin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emaillogin);

        final Button join=(Button)findViewById(R.id.joinbtn);
        join.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Joinintent = new Intent(emaillogin.this,Join.class);
                startActivity(Joinintent);
            }
        });


        Button findpw =(Button)findViewById(R.id.findbtn);
        findpw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Findintent = new Intent(emaillogin.this,Findpw.class);
                startActivity(Findintent);
            }
        });

    }
}
