package com.example.samsung.sample;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class menu2 extends Fragment {
    GridView gridView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_main2, container, false);

        gridView = (GridView) view.findViewById(R.id.gridview);

        ArrayList<String> items = new ArrayList<>();

        items.add("정보1");
        items.add("정보2");
        items.add("정보3");
        items.add("정보4");
        items.add("정보5");
        items.add("정보6");
        items.add("정보7");
        items.add("정보8");

        CustomAdapter adapter = new CustomAdapter(getActivity().getApplicationContext(), 0, items);
        gridView.setAdapter(adapter);

        return view;
    }

    private class CustomAdapter extends ArrayAdapter<String> {
        private ArrayList<String> items;

        public CustomAdapter(Context context, int textViewResourceId, ArrayList<String> objects) {
            super(context, textViewResourceId, objects);
            this.items = objects;
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            View v = convertView;
            if (v == null) {
                LayoutInflater vi = (LayoutInflater) getActivity().getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                v = vi.inflate(R.layout.listviewitem, null);
            }

            ImageView imageView = (ImageView) v.findViewById(R.id.imageView);

           /* if("정보1".equals(items.get(position)))
                imageView.setImageResource(R.drawable.정보이미지1);
            else if("정보2".equals(items.get(position)))
                imageView.setImageResource(R.drawable.정보이미지2);
            else if("정보3".equals(items.get(position)))
                imageView.setImageResource(R.drawable.정보이미지3);
            else if("정보4".equals(items.get(position)))
                imageView.setImageResource(R.drawable.정보이미지4);*/


            TextView textView = (TextView) v.findViewById(R.id.textView);
            textView.setText(items.get(position));

            final String text = items.get(position);


            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(
                            getActivity().getApplicationContext(),
                            information_M.class);
                    startActivity(intent);

                }
            });

            return v;

        }
    }


    }
