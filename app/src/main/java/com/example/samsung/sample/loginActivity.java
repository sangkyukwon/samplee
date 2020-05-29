package com.example.samsung.sample;

import android.os.Bundle;
import android.widget.Button;
import android.content.Intent;
import android.view.View;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.kakao.auth.ApiErrorCode;
import com.kakao.auth.ISessionCallback;
import com.kakao.network.ErrorResult;
import com.kakao.usermgmt.LoginButton;
import com.kakao.auth.AuthType;
import com.kakao.auth.Session;
import com.kakao.usermgmt.UserManagement;
import com.kakao.usermgmt.callback.LogoutResponseCallback;
import com.kakao.usermgmt.callback.MeV2ResponseCallback;
import com.kakao.usermgmt.response.MeV2Response;
import com.kakao.util.exception.KakaoException;

import java.sql.BatchUpdateException;

public class loginActivity extends AppCompatActivity {

    private SessionCallback sessionCallback;



    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

          Button login1=(Button)findViewById(R.id.loginbtn1);
          login1.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View view) {
                  Intent logintent =new Intent(loginActivity.this,emaillogin.class);
                  startActivity(logintent);
              }
          });

           Button join=(Button)findViewById(R.id.joinbtn);
          join.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View view) {
                  Intent Joinintent = new Intent(loginActivity.this,Join.class);
                  startActivity(Joinintent);
              }
          });

          Button findpw =(Button)findViewById(R.id.findbtn);
          findpw.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View view) {
                  Intent Findintent = new Intent(loginActivity.this,Findpw.class);
                  startActivity(Findintent);
              }
          });

                sessionCallback = new SessionCallback();
                Session.getCurrentSession().addCallback(sessionCallback);
                Session.getCurrentSession().checkAndImplicitOpen();
            }

            @Override
            protected void onActivityResult(int requestCode, int resultCode, Intent data) {
                if(Session.getCurrentSession().handleActivityResult(requestCode, resultCode, data)) {
                    super.onActivityResult(requestCode, resultCode, data);
                    return;
                }
            }

            @Override
            protected void onDestroy() {
                super.onDestroy();
                Session.getCurrentSession().removeCallback(sessionCallback);
            }

            private class SessionCallback implements ISessionCallback {
                @Override
                public void onSessionOpened() {
                    UserManagement.getInstance().me(new MeV2ResponseCallback() {
                        @Override
                        public void onFailure(ErrorResult errorResult) {
                            int result = errorResult.getErrorCode();

                            if(result == ApiErrorCode.CLIENT_ERROR_CODE) {
                                Toast.makeText(getApplicationContext(), "네트워크 연결이 불안정합니다. 다시 시도해 주세요.", Toast.LENGTH_SHORT).show();
                                finish();
                            } else {
                                Toast.makeText(getApplicationContext(),"로그인 도중 오류가 발생했습니다: "+errorResult.getErrorMessage(),Toast.LENGTH_SHORT).show();
                            }
                        }


                        @Override
                        public void onSessionClosed(ErrorResult errorResult) {
                            Toast.makeText(getApplicationContext(),"세션이 닫혔습니다. 다시 시도해 주세요: "+errorResult.getErrorMessage(),Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onSuccess(MeV2Response result) {
                            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                            intent.putExtra("name", result.getNickname());
                            intent.putExtra("profile", result.getProfileImagePath());
                            startActivity(intent);
                            finish();
                        }
                    });
                }

                @Override
                public void onSessionOpenFailed(KakaoException e) {
                    Toast.makeText(getApplicationContext(), "로그인 도중 오류가 발생했습니다. 인터넷 연결을 확인해주세요: "+e.toString(), Toast.LENGTH_SHORT).show();
                }
            }
        }






