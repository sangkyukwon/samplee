package com.example.samsung.sample;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Comparator;
import com.naver.maps.map.overlay.OverlayImage;
public class Hplocation implements Comparable<Hplocation> {

    public String addr;
    public String code;
    public String created_at;
    public double lat;
    public double lng;
    public String name;
    public String type;
    public String stock_at;
    public String remain_stat;



    public int getAmount() {
        if ("plenty".equalsIgnoreCase(remain_stat)) {
            return 0;
        } else if ("some".equalsIgnoreCase(remain_stat)) {
            return 1;
        } else if ("few".equalsIgnoreCase(remain_stat)) {
            return 2;
        } else if ("empty".equalsIgnoreCase(remain_stat)) {
            return 3;
        } else if ("break".equalsIgnoreCase(remain_stat)) {
            return 4;
        } else {
            return 5;
        }
    }

@Override
    public int compareTo(Hplocation other) {
        return getAmount() - other.getAmount();
    }

    public static class NameSorter implements Comparator<Hplocation> {
        public int compare(Hplocation store1, Hplocation store2) {
            store1.name = (store1.name == null) ? "" : store1.name;
            store2.name = (store2.name == null) ? "" : store2.name;
            return store1.name.compareTo(store2.name);
        }
    }

    public static class StockAtSorter implements Comparator<Hplocation> {
        public int compare(Hplocation store1, Hplocation store2) {
            store1.stock_at = (store1.stock_at == null) ? "" : store1.stock_at;
            store2.stock_at = (store2.stock_at == null) ? "" : store2.stock_at;
            return store1.stock_at.compareTo(store2.stock_at);
        }
    }
}


