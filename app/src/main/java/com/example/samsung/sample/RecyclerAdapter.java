package com.example.samsung.sample;

/**
 * Created by samsung on 2020-04-06.
 */

import android.animation.ValueAnimator;
import android.content.Context;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ItemViewHolder> {


    private ArrayList<Data> listData = new ArrayList<>();
    private Context context;
    private SparseBooleanArray selectedItems = new SparseBooleanArray();
    private int prePosition = -1;

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {

        holder.onBind(listData.get(position), position);
    }

    @Override
    public int getItemCount() {
        // RecyclerView의 총 개수 입니다.
        return listData.size();
    }

    void addItem(Data data) {

        listData.add(data);
    }


    class ItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView textView1;
        private TextView textView2;
        private ImageView imageView1;
        private ImageView imageView2;
        private Data data;
        private int position;

        ItemViewHolder(View itemView) {
            super(itemView);

            textView1 = itemView.findViewById(R.id.textView1);
            textView2 = itemView.findViewById(R.id.textView2);
            imageView1 = itemView.findViewById(R.id.imageView1);
            imageView2 = itemView.findViewById(R.id.imageView2);
        }

        void onBind(Data data, int position) {
            this.data = data;
            this.position = position;

            textView1.setText(data.getTitle());
            textView2.setText(data.getContent());
            imageView1.setImageResource(data.getResId());
            imageView2.setImageResource(data.getResId());

            changeVisibility(selectedItems.get(position));

            itemView.setOnClickListener(this);
            textView1.setOnClickListener(this);
            textView2.setOnClickListener(this);
            imageView1.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.linearItem:
                    if (selectedItems.get(position)) {

                        selectedItems.delete(position);
                    } else {

                        selectedItems.delete(prePosition);
                        selectedItems.put(position, true);
                    }

                    if (prePosition != -1) notifyItemChanged(prePosition);
                    notifyItemChanged(position);
                    prePosition = position;
                    break;
                case R.id.textView1:
                    Toast.makeText(context, data.getTitle(), Toast.LENGTH_SHORT).show();
                    break;
                case R.id.textView2:
                    Toast.makeText(context, data.getContent(), Toast.LENGTH_SHORT).show();
                    break;
                case R.id.imageView1:
                    Toast.makeText(context, data.getTitle() + " 예약 질문입니다.", Toast.LENGTH_SHORT).show();
                    break;
            }
        }


        private void changeVisibility(final boolean isExpanded) {

            int dpValue = 150;
            float d = context.getResources().getDisplayMetrics().density;
            int height = (int) (dpValue * d);


            ValueAnimator va = isExpanded ? ValueAnimator.ofInt(0, height) : ValueAnimator.ofInt(height, 0);
            va.setDuration(600);
            va.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    // value는 height 값
                    int value = (int) animation.getAnimatedValue();
                    // imageView의 높이 변경
                    imageView2.getLayoutParams().height = value;
                    imageView2.requestLayout();
                    // imageView가 실제로 사라지게하는 부분
                    imageView2.setVisibility(isExpanded ? View.VISIBLE : View.GONE);
                }
            });
            // Animation start
            va.start();
        }
    }
}