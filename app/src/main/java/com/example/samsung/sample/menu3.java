package com.example.samsung.sample;

import android.content.Intent;
import android.graphics.PointF;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.naver.maps.geometry.LatLng;
import com.naver.maps.map.MapFragment;
import com.naver.maps.map.NaverMap;
import com.naver.maps.map.OnMapReadyCallback;
import com.naver.maps.map.UiSettings;
import com.naver.maps.map.overlay.InfoWindow;
import com.naver.maps.map.overlay.Marker;
import com.naver.maps.map.overlay.Overlay;
import com.naver.maps.map.overlay.OverlayImage;
import com.naver.maps.map.util.FusedLocationSource;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class menu3 extends Fragment implements OnMapReadyCallback {

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


        MapFragment mapFragment = (MapFragment) getActivity().getSupportFragmentManager().findFragmentById(R.id.map);
        if (mapFragment == null) {
            mapFragment = MapFragment.newInstance();
            getActivity().getSupportFragmentManager().beginTransaction().add(R.id.map, mapFragment).commit();
        }


        mapFragment.getMapAsync(this);


        return view;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getActivity().getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.show_list:
                Intent intent = new Intent(this, StoreActivity.class);
                LatLng mapCenter = naverMap.getCameraPosition().target;
                intent.putExtra("longitude", mapCenter.longitude);
                intent.putExtra("latitude", mapCenter.latitude);
                startActivity(intent);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onMapReady(@NonNull NaverMap naverMap) {
        this.naverMap = naverMap;

        locationSource = new FusedLocationSource(this, ACCESS_LOCATION_PERMISSION_REQUEST_CODE);
        naverMap.setLocationSource(locationSource);
        UiSettings uiSettings = naverMap.getUiSettings();
        uiSettings.setLocationButtonEnabled(true);

        naverMap.addOnCameraChangeListener((NaverMap.OnCameraChangeListener) this);
        naverMap.addOnCameraIdleListener((NaverMap.OnCameraIdleListener) this);
        naverMap.setOnMapClickListener((NaverMap.OnMapClickListener) this);

        LatLng mapCenter = naverMap.getCameraPosition().target;
        fetchStoreSale(mapCenter.latitude, mapCenter.longitude, 5000);

        infoWindow = new InfoWindow();
        infoWindow.setAdapter(new InfoWindow.DefaultViewAdapter(this) {
            @NonNull
            @Override
            protected View getContentView(@NonNull InfoWindow infoWindow) {
                Marker marker = infoWindow.getMarker();
                Hplocation store = (Hplocation) marker.getTag();
                View view = View.inflate(menu3.this,getActivity().R.layout.view_info_window, null);
                ((TextView) view.findViewById(R.id.name)).setText(store.name);
                if ("plenty".equalsIgnoreCase(store.remain_stat)) {
                    ((TextView) view.findViewById(R.id.stock)).setText("100개 이상");
                } else if ("some".equalsIgnoreCase(store.remain_stat)) {
                    ((TextView) view.findViewById(R.id.stock)).setText("30개 이상 100개 미만");
                } else if ("few".equalsIgnoreCase(store.remain_stat)) {
                    ((TextView) view.findViewById(R.id.stock)).setText("2개 이상 30개 미만");
                } else if ("empty".equalsIgnoreCase(store.remain_stat)) {
                    ((TextView) view.findViewById(R.id.stock)).setText("1개 이하");
                } else if ("break".equalsIgnoreCase(store.remain_stat)) {
                    ((TextView) view.findViewById(R.id.stock)).setText("판매중지");
                } else {
                    ((TextView) view.findViewById(R.id.stock)).setText(null);
                }
                ((TextView) view.findViewById(R.id.time)).setText("입고 " + store.stock_at);
                return view;
            }
        });
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

    @Override
    public void onCameraChange(int reason, boolean animated) {
        isCameraAnimated = animated;
    }

    @Override
    public void onCameraIdle() {
        if (isCameraAnimated) {
            LatLng mapCenter = naverMap.getCameraPosition().target;
            fetchStoreSale(mapCenter.latitude, mapCenter.longitude, 5000);
        }
    }

    private void fetchStoreSale(double lat, double lng, int m) {
        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://8oi9s0nnth.apigw.ntruss.com").addConverterFactory(GsonConverterFactory.create()).build();
        MaskApi maskApi = retrofit.create(MaskApi.class);
        maskApi.getStoresByGeo(lat, lng, m).enqueue(new Callback<Hplocationresult>() {
            @Override
            public void onResponse(Call<Hplocationresult> call, Response<Hplocationresult> response) {
                if (response.code() == 200) {
                    Hplocationresult result = response.body();
                    updateMapMarkers(result);
                }
            }

            @Override
            public void onFailure(Call<Hplocationresult> call, Throwable t) {

            }
        });
    }

    private void updateMapMarkers(Hplocationresult result) {
        resetMarkerList();
        if (result.stores != null && result.stores.size() > 0) {
            for (Hplocation store : result.stores) {
                Marker marker = new Marker();
                marker.setTag(store);
                marker.setPosition(new LatLng(store.lat, store.lng));
                if ("plenty".equalsIgnoreCase(store.remain_stat)) {
                    marker.setIcon(OverlayImage.fromResource(R.drawable.marker_green));
                } else if ("some".equalsIgnoreCase(store.remain_stat)) {
                    marker.setIcon(OverlayImage.fromResource(R.drawable.marker_yellow));
                } else if ("fiew".equalsIgnoreCase(store.remain_stat)) {
                    marker.setIcon(OverlayImage.fromResource(R.drawable.marker_red));
                } else {
                    marker.setIcon(OverlayImage.fromResource(R.drawable.marker_gray));
                }
                marker.setAnchor(new PointF(0.5f, 1.0f));
                marker.setMap(naverMap);
                marker.setOnClickListener((Overlay.OnClickListener) this);
                markerList.add(marker);
            }
        }
    }

    @Override
    public void onMapClick(@NonNull PointF pointF, @NonNull LatLng latLng) {
        if (infoWindow.getMarker() != null) {
            infoWindow.close();
        }
    }

    @Override
    public boolean onClick(@NonNull Overlay overlay) {
        if (overlay instanceof Marker) {
            Marker marker = (Marker) overlay;
            if (marker.getInfoWindow() != null) {
                infoWindow.close();
            } else {
                infoWindow.open(marker);
            }
            return true;
        }
        return false;
    }

    private void resetMarkerList() {
        if (markerList != null && markerList.size() > 0) {
            for (Marker marker : markerList) {
                marker.setMap(null);
            }
            markerList.clear();
        }
    }
}