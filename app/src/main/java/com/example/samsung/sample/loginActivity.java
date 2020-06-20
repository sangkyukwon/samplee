package com.example.samsung.sample;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
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
import com.nhn.android.naverlogin.OAuthLogin;
import com.nhn.android.naverlogin.OAuthLoginHandler;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.BatchUpdateException;

import static com.nhn.android.naverlogin.OAuthLogin.mOAuthLoginHandler;

public class loginActivity extends AppCompatActivity {

    private SessionCallback sessionCallback;
    private  static OAuthLogin mOAuthLoginModule;


    public loginActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);







          Button login1=(Button)findViewById(R.id.loginbtn1);
          login1.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View view) {

//                  LayoutInflater inflater = getLayoutInflater();
//                  View v1 = inflater.inflate(R.layout.activity_main5,null);
//                  TextView text1 =(TextView)findViewById(R.id.logid);
//                  TextView text2 =(TextView)findViewById(R.id.tvNickname);
//                  Button btn1 =(Button)findViewById(R.id.login);
//
//                  text1.setVisibility(View.INVISIBLE);
//                  text2.setVisibility(View.VISIBLE);
//                  btn1.setVisibility(View.INVISIBLE);

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
                    Intent intent = new Intent(getApplicationContext(),Inreser.class);
                    intent.putExtra("name", result.getNickname());
                    getApplication().startActivity(intent);
                    finish();

//                            LayoutInflater inflater = getLayoutInflater();
//                            View v1 = inflater.inflate(R.layout.activity_main5,null);
//                            TextView text1 =(TextView)findViewById(R.id.logid);
//                            TextView text2 =(TextView)findViewById(R.id.tvNickname);
//                            Button btn1 =(Button)findViewById(R.id.login);
//
//                            text1.setVisibility(View.INVISIBLE);
//                            text2.setVisibility(View.VISIBLE);
//                            btn1.setVisibility(View.INVISIBLE);
                }
            });
}

                @Override
                public void onSessionOpenFailed(KakaoException e) {
                    Toast.makeText(getApplicationContext(), "로그인 도중 오류가 발생했습니다. 인터넷 연결을 확인해주세요: "+e.toString(), Toast.LENGTH_SHORT).show();
                }







            }


            /// 네이버

//    private void setNaver() {
//        mOAuthLoginModule = OAuthLogin.getInstance();
//        mOAuthLoginModule.init(this, "wsTpNyGapr8BMpvGC12_", "HmKDlXZw_7", "clientName");
//
//        //mOAuthLoginButton = findViewById(R.id.button_naverlogin);
//
//        binding.buttonNaverlogin.setOAuthLoginHandler(mOAuthLoginHandler);
//        mOAuthLoginModule.startOauthLoginActivity(this, mOAuthLoginHandler);
//    }
//
//    private OAuthLoginHandler mOAuthLoginHandler = new OAuthLoginHandler() {
//        @Override
//        public void run(boolean success) {
//            if (success) {
//                password = mOAuthLoginModule.getAccessToken(act);
//                //getNaverInfo(accessToken);
//                ProfileTask task = new ProfileTask();
//                // 이 클래스가 유저정보를 가져오는 업무를 담당합니다.
//                task.execute(password);
//
//                String refreshToken = mOAuthLoginModule.getRefreshToken(act);
//                long expiresAt = mOAuthLoginModule.getExpiresAt(act);
//                String tokenType = mOAuthLoginModule.getTokenType(act);
//
//            } else {
//                String errorCode = mOAuthLoginModule.getLastErrorCode(act).getCode();
//                String errorDesc = mOAuthLoginModule.getLastErrorDesc(act);
//                Toast.makeText(act, "errorCode:" + errorCode
//                        + ", errorDesc:" + errorDesc, Toast.LENGTH_SHORT).show();
//            }
//        };
//    };
//
//    class ProfileTask extends AsyncTask<String, Void, String> {
//        String result;
//
//        @Override
//        protected String doInBackground(String... strings) {
//            String token = strings[0];// 네이버 로그인 접근 토큰;
//            String header = "Bearer " + token; // Bearer 다음에 공백 추가
//            try {
//                String apiURL = "https://openapi.naver.com/v1/nid/me";
//                URL url = new URL(apiURL);
//                HttpURLConnection con = (HttpURLConnection) url.openConnection();
//                con.setRequestMethod("GET");
//                con.setRequestProperty("Authorization", header);
//                int responseCode = con.getResponseCode();
//                BufferedReader br;
//                if (responseCode == 200) { // 정상 호출
//                    br = new BufferedReader(new InputStreamReader(con.getInputStream()));
//                } else {  // 에러 발생
//                    br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
//                }
//                String inputLine;
//                StringBuffer response = new StringBuffer();
//
//                while ((inputLine = br.readLine()) != null) {
//                    response.append(inputLine);
//                }
//                result = response.toString();
//                br.close();
//                System.out.println(response.toString());
//            } catch (Exception e) {
//                System.out.println(e);
//            }
//            //result 값은 JSONObject 형태로 넘어옵니다.
//            return result;
//        }
//
//        @Override
//        protected void onPostExecute(String s) {
//            super.onPostExecute(s);
//            try {
//
//                JSONObject object = new JSONObject(result);
//                Log.d(CommonUtil.TAG, "결과 : " + result);
//                if (object.getString("resultcode").equals("00")) {
//                    JSONObject jsonObject = new JSONObject(object.getString("response"));
//                    email = jsonObject.getString("id");
//                    Log.d("jsonObject", jsonObject.toString());
//
//                /*SharedPreferences.Editor editor = activity.userData.edit();
//                editor.putString("email", jsonObject.getString("email"));
//                editor.putString("name", jsonObject.getString("name"));
//                editor.putString("nickname", jsonObject.getString("nickname"));
//                editor.putString("profile", jsonObject.getString("profile_image"));
//                editor.apply();
//                Intent intent = new Intent(activity, MainActivity.class);
//                activity.startActivity(intent);*/
//                }
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }

    }

//        }






