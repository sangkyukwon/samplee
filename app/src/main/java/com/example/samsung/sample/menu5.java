package com.example.samsung.sample;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.kakao.auth.ApiErrorCode;
import com.kakao.network.ErrorResult;
import com.kakao.usermgmt.LoginButton;
import com.kakao.usermgmt.UserManagement;
import com.kakao.usermgmt.callback.LogoutResponseCallback;
import com.kakao.usermgmt.callback.UnLinkResponseCallback;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

public class menu5 extends Fragment {
    private  Button logout,logtal;
    @Nullable
    @Override

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       View view = inflater.inflate(R.layout.activity_main5, container, false);

        Button login2 =(Button)view.findViewById(R.id.login);
        login2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myintent = new Intent(getActivity().getApplicationContext(),loginActivity.class);
                startActivity(myintent);
            }


        });

        logout =(Button)view.findViewById(R.id.logout);
        logtal=(Button)view.findViewById(R.id.logtal);
//
        TextView tvNickname = view.findViewById(R.id.tvNickname);


        Intent intent = getActivity().getIntent();
        String strNickname = intent.getStringExtra("name");

        tvNickname.setText(strNickname);

//        logout.setOnClickListener(new Button.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(getActivity().getApplicationContext(), "정상적으로 로그아웃되었습니다.", Toast.LENGTH_SHORT).show();
//
//                UserManagement.getInstance().requestLogout(new LogoutResponseCallback() {
//                    @Override
//                    public void onCompleteLogout() {
//                        Intent intent = new Intent(getActivity().getApplicationContext()menu5.loginActivity.class);
//                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                        startActivity(intent);
//                    }
//                });
//            }
//        });
//
//        logtal.setOnClickListener(new Button.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                new AlertDialog.Builder(getActivity().getApplicationContext().menu5m,)
//                        .setMessage("탈퇴하시겠습니까?")
//                        .setPositiveButton("네", new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialog, int which) {
//                                UserManagement.getInstance().requestUnlink(new UnLinkResponseCallback() {
//                                    @Override
//                                    public void onFailure(ErrorResult errorResult) {
//                                        int result = errorResult.getErrorCode();
//
//                                        if(result == ApiErrorCode.CLIENT_ERROR_CODE) {
//                                            Toast.makeText(getActivity().getApplicationContext(), "네트워크 연결이 불안정합니다. 다시 시도해 주세요.", Toast.LENGTH_SHORT).show();
//                                        } else {
//                                            Toast.makeText(getActivity().getApplicationContext(), "회원탈퇴에 실패했습니다. 다시 시도해 주세요.", Toast.LENGTH_SHORT).show();
//                                        }
//                                    }
//
//                                    @Override
//                                    public void onSessionClosed(ErrorResult errorResult) {
//                                        Toast.makeText(getActivity().getApplicationContext(), "로그인 세션이 닫혔습니다. 다시 로그인해 주세요.", Toast.LENGTH_SHORT).show();
//                                        Intent intent = new Intent(Inreser.this, loginActivity.class);
//                                        startActivity(intent);
//                                        finish();
//                                    }
//
//                                    @Override
//                                    public void onNotSignedUp() {
//                                        Toast.makeText(getApplicationContext(), "가입되지 않은 계정입니다. 다시 로그인해 주세요.", Toast.LENGTH_SHORT).show();
//                                        Intent intent = new Intent(Inreser.this, loginActivity.class);
//                                        startActivity(intent);
//                                        finish();
//                                    }
//
//                                    @Override
//                                    public void onSuccess(Long result) {
//                                        Toast.makeText(getApplicationContext(), "회원탈퇴에 성공했습니다.", Toast.LENGTH_SHORT).show();
//                                        Intent intent = new Intent(Inreser.this, loginActivity.class);
//                                        startActivity(intent);
//                                        finish();
//                                    }
//                                });
//
//                                dialog.dismiss();
//                            }
//                        })
//                        .setNegativeButton("아니요", new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialog, int which) {
//                                dialog.dismiss();
//                            }
//                        }).show();
//            }
//        });


        return view;
    }


        }