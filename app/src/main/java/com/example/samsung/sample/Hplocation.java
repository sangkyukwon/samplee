package com.example.samsung.sample;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Hplocation {
    public double WGS84_LON;
    public double getWGS84_LAT;
    public int pageNo;
    public int numOfRow;

    public static void main(String[] args) {
        BufferedReader br = null;
        try{
            String urlstr = "http://apis.data.go.kr/B552657/ErmctInsttInfoInqireService/getParmacyFullDown?serviceKey=HHGr9golko8Mc27Ts1R%2FjF9dPCLwyWu%2BxlpmHG4fSGC986m5RWUY3Ch0IcEsidsNK6Ps5mHvj29BQquvw2J6tw%3D%3D&pageNo=1&numOfRows=10&";

            URL url = new URL(urlstr);
            HttpURLConnection urlconnection = (HttpURLConnection) url.openConnection();
            urlconnection.setRequestMethod("GET");
            br = new BufferedReader(new InputStreamReader(urlconnection.getInputStream(),"UTF-8"));
            String result = "";
            String line;
            while((line = br.readLine()) != null) {
                result = result + line + "\n";
            }
            System.out.println(result);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
}




