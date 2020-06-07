package com.example.samsung.sample;

import android.content.ClipData;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.media.Image;
import android.media.browse.MediaBrowser;
import android.os.Bundle;
import android.provider.DocumentsContract;
import android.view.LayoutInflater;
//import android.view.View;


import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.view.menu.MenuView;
import androidx.core.view.MenuItemCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

public class menu1 extends Fragment {
    NetworkImageView symbolView;
    TextView temperatureView;
    TextView upView;
    TextView downview;
    RecyclerView recyclerView;

    MyAdapter adapter;
    ArrayList<ItemData> list;

    RequestQueue queue;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_main1, container, false);

temperatureView =(TextView)view.findViewById(R.id.mission1_temperature);
upView =(TextView)view.findViewById(R.id.mission1_up_text);
downview = (TextView)view.findViewById(R.id.mission_down_text);
symbolView=(NetworkImageView)view.findViewById(R.id.mission1_symbol);
recyclerView=(RecyclerView)view.findViewById(R.id.mission1_recycler);

list = new ArrayList<>();
adapter = new MyAdapter(list);
         //문제
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.addItemDecoration(new MyItemDecoration());
        recyclerView.setAdapter(adapter);

        queue = Volley.newRequestQueue(getActivity().getApplicationContext());
        showItemList();
        StringRequest currentRequest= new StringRequest(Request.Method.POST, "http://api.openweathermap.org/data/2.5/weather?q=seoul&mode=xml&units=metric&appid=4a7610ca0007134d22d800d37554d835", new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                parseXMLCurrent(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        StringRequest forecastRequest = new StringRequest(Request.Method.POST, "http://api.openweathermap.org/data/2.5/forecast/daily?q=seoul&mode=xml&units=metric&appid=4a7610ca0007134d22d800d37554d835", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                parseXMLForecast(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        queue.add(currentRequest);
        queue.add(forecastRequest);

        return view;
//}
//
//
//}
//class FragmentAdapter extends FragmentPagerAdapter {
//
////     ViewPager에 들어갈 Fragment들을 담을 리스트
//    private ArrayList<Fragment> fragments = new ArrayList<>();
//
////     필수 생성자
//    FragmentAdapter(FragmentManager fm) {
//        super(fm);
//    }
//
//    @Override
//    public Fragment getItem(int position) {
//        return fragments.get(position);
//    }
//
//    @Override
//    public int getCount() {
//        return fragments.size();
//    }
//
////     List에 Fragment를 담을 함수
//    void addItem(Fragment fragment) {
//        fragments.add(fragment);
//    }
//
//    public void notifyDataSetChanged() {
    }

    private class ItemData {
        public String max;
        public String min;
        public String dav;
        public Bitmap image;
    }

    private class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView dayView;
        public TextView maxView;
        public TextView minView;
        public ImageView imageView;

        public MyViewHolder(View itemView) {
            super(itemView);
            dayView = (TextView) itemView.findViewById(R.id.mission1_item_day);
            maxView =(TextView) itemView.findViewById(R.id.mission1_item_max);
            minView =(TextView)itemView.findViewById(R.id.mission1_item_min);
            imageView =(ImageView)itemView.findViewById(R.id.mission1_item_image);
        }
    }

    private class MyAdapter extends RecyclerView.Adapter<MyViewHolder>
    {
        private  final List<ItemData> list;
        public MyAdapter(List<ItemData> list){
            this.list=list;

        }

        @NonNull
        @Override
        public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

             View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.mission1_item,parent,false);
       return  new MyViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
            ItemData vo = list.get(position);
            holder.dayView.setText(vo.dav);
            holder.maxView.setText(vo.max);
            holder.minView.setText(vo.min);
            holder.imageView.setImageBitmap(vo.image);

        }

        @Override
        public int getItemCount() {
            return list.size();
        }
    }

    class MyItemDecoration extends  RecyclerView.ItemDecoration{
        @Override
        public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
            super.getItemOffsets(outRect, view, parent, state);
            outRect.set(10,10,10,10);
            view.setBackgroundColor(0x88929090);
        }
    }
    private void parseXMLCurrent(String response)
    {
        try {
            DocumentBuilderFactory factory=DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(new InputSource(new StringReader(response)));
            doc.getDocumentElement().normalize();

            Element tempElement =(Element)(doc.getElementsByTagName("temperature").item(0));
            String temperature=tempElement.getAttribute("value");
            String min = tempElement.getAttribute("min");
            String max = tempElement.getAttribute("max");

            temperatureView.setText(temperature);
            upView.setText(max);
            downview.setText(min);

            Element weatherElement=(Element)(doc.getElementsByTagName("weather")).item(0);
            String symbol =weatherElement.getAttribute("icon");

            ImageLoader imageLoader = new ImageLoader(queue, new ImageLoader.ImageCache() {
                @Override
                public Bitmap getBitmap(String url) {
                    return null;
                }

                @Override
                public void putBitmap(String url, Bitmap bitmap) {

                }
            });
            symbolView.setImageUrl("http://openweathermap.org/img/w/"+symbol+".png",imageLoader);



        /// xml 파싱
        }catch (Exception e){

        }
    }
    private  void parseXMLForecast(String response)
    {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(new InputSource(new StringReader(response)));
            doc.getDocumentElement().normalize();

            NodeList nodeList = doc.getElementsByTagName("time");
            for (int i = 0; i < nodeList.getLength(); i++) {
                final ItemData vo = new ItemData();

                Element timeNode = (Element) nodeList.item(i);
                vo.dav = timeNode.getAttribute("day").substring(5);

                Element temperatureNode = (Element) timeNode.getElementsByTagName("temperature").item(0);
                vo.max = temperatureNode.getAttribute("max");
                vo.min = temperatureNode.getAttribute("min");

                Element symbolNode = (Element) timeNode.getElementsByTagName("symbol").item(0);
                String symbol = symbolNode.getAttribute("var");

                String url = "http://openweathermap.org/img/w/" + symbol + ".png";
                ImageRequest imageRequest = new ImageRequest(url, new Response.Listener<Bitmap>() {
                    @Override
                    public void onResponse(Bitmap response) {
                        vo.image = response;
                        adapter.notifyDataSetChanged();

                    }
                }, 0, 0, ImageView.ScaleType.CENTER_CROP, null, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });

                queue.add(imageRequest);
                list.add(vo);
            }

adapter.notifyDataSetChanged();

        } catch (Exception e)
        {

        }


    }
    // 프래그먼트 싫행
    public void  showItemList()
    {
        MyAdapter  mAdapter = new MyAdapter(list);
        recyclerView.setAdapter(mAdapter);
    }

}
