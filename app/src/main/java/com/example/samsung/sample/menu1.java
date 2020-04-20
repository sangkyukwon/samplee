package com.example.samsung.sample;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class menu1 extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_main1, container, false);

        ArrayList<Integer> listlmage = new ArrayList<>();
        listlmage.add(R.drawable.fa);
        listlmage.add(R.drawable.we);
        listlmage.add(R.drawable.tocs);
        listlmage.add(R.drawable.miso);


        // 임시 이미지 넘김 향후 배너로 사용



        ViewPager viewPager = view.findViewById(R.id.viewPager);
        FragmentAdapter fragmentAdapter = new FragmentAdapter(getFragmentManager());
        viewPager.setAdapter(fragmentAdapter);

        viewPager.setClipToPadding(false);
        int dpValue =16;
        float d =getResources().getDisplayMetrics().density;
        int margin = (int) (dpValue *d);
        viewPager.setPadding(margin,0 ,margin,0);
        viewPager.setPageMargin(margin/2);


        for(int i=0;i<listlmage.size();i++){
            imgFragment imageFragmentt = new imgFragment();
            Bundle bundle = new Bundle();
            bundle.putInt("imgRes",listlmage.get(i));
            imageFragmentt.setArguments(bundle);
            fragmentAdapter.addItem(imageFragmentt);
    }
        fragmentAdapter.notifyDataSetChanged();

        return  view;
}


}
class FragmentAdapter extends FragmentPagerAdapter {

    // ViewPager에 들어갈 Fragment들을 담을 리스트
    private ArrayList<Fragment> fragments = new ArrayList<>();

    // 필수 생성자
    FragmentAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    // List에 Fragment를 담을 함수
    void addItem(Fragment fragment) {
        fragments.add(fragment);
    }
}


