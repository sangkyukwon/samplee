package com.example.samsung.sample;

import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;


public class imgFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
//         Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_img, container, false);


        ImageView imageView = view.findViewById(R.id.imageView);

        if(getArguments() !=null)
        {
            Bundle args = getArguments();
            imageView.setImageResource(args.getInt("imgRes"));
        }


        return  view;
    }

}
