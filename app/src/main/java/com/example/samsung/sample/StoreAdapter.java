package com.example.samsung.sample;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class StoreAdapter extends RecyclerView.Adapter<StoreAdapter.StoreViewHolder> {
    private List<Hplocation> storeList;

    public StoreAdapter(List<Hplocation> storeList) {
        this.storeList = storeList;
    }

    @NonNull
    @Override
    public StoreViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_store, parent, false);
        return new StoreViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StoreViewHolder holder, int position) {
        Hplocation store = storeList.get(position);
        holder.name.setText(store.name);
        holder.description.setText(store.remain_stat + " stock at " + store.stock_at);
    }

    @Override
    public int getItemCount() {
        if (storeList != null) {
            return storeList.size();
        }
        return 0;
    }

    public class StoreViewHolder extends RecyclerView.ViewHolder {
        public TextView name;
        public TextView description;

        public StoreViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            description = itemView.findViewById(R.id.description);
        }
    }
}