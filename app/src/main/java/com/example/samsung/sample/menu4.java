package com.example.samsung.sample;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Arrays;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class menu4 extends Fragment {
    private RecyclerAdapter adapter;
    RecyclerView recyclerView;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_main4, container, false);


        recyclerView = (RecyclerView) v.findViewById(R.id.recycleView);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);

        adapter = new RecyclerAdapter();
        recyclerView.setAdapter(adapter);

        getData();

        return v;


    }

    private void getData() {
        // 임의의 데이터입니다.
        List<String> listTitle = Arrays.asList("예약", "건강정보", "마이페이지", "관심예약문의", "가까운약국 찾는법", "회원가입", "병원찾기", "병원 시간대",
                "카카오톡으로 연동", "시술문의", "시술 전/후", "알림설정", "회원탈퇴", "결제방법", "약국찾기", "노쇼시 대책");
        List<String> listContent = Arrays.asList(
                "예약관련 문의.",
                "건강정보 문의.",
                "마이페이지 문의.",
                "관심예약 문의.",
                "가까운 약국 찾는법.",
                "회원가입 문의.",
                "병원찾기 문의.",
                "병원 시간대 문의 .",
                ".",
                ".",
                ".",
                ".",
                ".",
                ".",
                ".",
                "."
        );
        List<Integer> listResId = Arrays.asList(
                R.drawable.ha,
                R.drawable.ha,
                R.drawable.ha,
                R.drawable.ha,
                R.drawable.ha,
                R.drawable.ha,
                R.drawable.ha,
                R.drawable.ha,
                R.drawable.ha,
                R.drawable.ha,
                R.drawable.ha,
                R.drawable.ha,
                R.drawable.ha,
                R.drawable.ha,
                R.drawable.ha,
                R.drawable.ha
        );
        for (int i = 0; i < listTitle.size(); i++) {

            Data data = new Data();
            data.setTitle(listTitle.get(i));
            data.setContent(listContent.get(i));
            data.setResId(listResId.get(i));


            adapter.addItem(data);
        }


        adapter.notifyDataSetChanged();


    }

}






