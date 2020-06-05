package com.example.samsung.sample;

import android.content.ClipData;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.media.Image;
import android.os.Bundle;
import android.provider.DocumentsContract;
import android.view.LayoutInflater;
//import android.view.View;


import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

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
import androidx.core.view.MenuItemCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
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
            dayView = (TextView) dayView.findViewById(R.id.mission1_item_day);
            maxView =(TextView) maxView.findViewById(R.id.mission1_item_max);
            minView =(TextView)minView.findViewById(R.id.mission1_item_min);
            imageView =(ImageView)imageView.findViewById(R.id.mission1_item_image);
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
            symbolView.setImageUrl("http://openweathermap.org/img?/w/"+symbol+".png",imageLoader);



        /// xml 파싱
        }catch (Exception e){

        }
    }
    private  void parseXMLForecast(String response)
    {
        try {
            DocumentBuilderFactory factory=DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(new InputSource(new StringReader(response)));
            doc.getDocumentElement().normalize();

            NodeList nodeList =doc.getElementsByTagName("time");
            for(int i=0; i<nodeList.getLength();i++){
                final  ItemData vo = new ItemData();

                Element timeNode =(Element)nodeList.item(i);
                vo.dav = timeNode.getAttribute("day").substring(5);

                Element temperatureNode =(Element)timeNode.getElementsByTagName("temperature").item(0);
                vo.max = temperatureNode.getAttribute("max");
                vo.min = temperatureNode.getAttribute("min");

                Element symbol
            }

        }




        catch (Exception e)
        {

        }
    }
}
