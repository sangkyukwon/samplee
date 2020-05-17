package com.example.samsung.sample;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.naver.maps.map.MapFragment;
import com.naver.maps.map.NaverMap;
import com.naver.maps.map.OnMapReadyCallback;
import com.naver.maps.map.UiSettings;
import com.naver.maps.map.overlay.InfoWindow;
import com.naver.maps.map.overlay.Marker;
import com.naver.maps.map.util.FusedLocationSource;

import java.util.ArrayList;
import java.util.List;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


public class menu3 extends Fragment implements OnMapReadyCallback {
    hos fragment1;
    hos2 fragment2;
    private static final int ACCESS_LOCATION_PERMISSION_REQUEST_CODE = 100;
    private FusedLocationSource locationSource;
    private NaverMap naverMap;
    private InfoWindow infoWindow;
    private List<Marker> markerList = new ArrayList<Marker>();
    private boolean isCameraAnimated = false;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_main3, container, false);


        MapFragment mapFragment = (MapFragment)getActivity().getSupportFragmentManager().findFragmentById(R.id.map);
        if (mapFragment == null) {
            mapFragment = MapFragment.newInstance();
            getActivity().getSupportFragmentManager().beginTransaction().add(R.id.map, mapFragment).commit();
        }



        mapFragment.getMapAsync(this);

//         fragment1 = new hos();
//         fragment2 = new hos2();
//
//        Button button1=(Button)view.findViewById(R.id.button1);
//        button1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//              getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.map,fragment1);
//
//            }
//        });
//
//        Button button2= (Button)view.findViewById(R.id.button2);
//        button2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.map,fragment2).commit();
//            }
//        });






        return view;
    }

    @Override
    public void onMapReady(@NonNull NaverMap naverMap) {
        locationSource = new FusedLocationSource( this , ACCESS_LOCATION_PERMISSION_REQUEST_CODE );
        naverMap . setLocationSource (locationSource);
        UiSettings uiSettings = naverMap. getUiSettings ();
        uiSettings . setLocationButtonEnabled ( true );
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode) {
            case ACCESS_LOCATION_PERMISSION_REQUEST_CODE:
                locationSource.onRequestPermissionsResult(requestCode, permissions, grantResults);
                return;
        }
    }
}


