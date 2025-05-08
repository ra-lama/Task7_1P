package com.serasonproject.task71p;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemViewHolder> {

    private List<LostFoundItem> itemList;
    private Context context;
    private OnRowClickListener listener;

    public ItemAdapter(List<LostFoundItem> itemList, Context context, OnRowClickListener listener) {
        this.itemList = itemList;
        this.context = context;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.item_layout, parent, false);
        return new ItemViewHolder(itemView, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        // alternating item color, from https://stackoverflow.com/questions/46127942/in-android-recyclerview-how-to-change-the-color-of-alternate-rows
        // also used in Task 4.1P
        if(position %2 == 1) {
            holder.itemView.setBackgroundColor(Color.parseColor("#16b666"));
        }
        else {
            holder.itemView.setBackgroundColor(Color.parseColor("#1666b6"));
        }

        String concatItem = itemList.get(position).getItemType() + " " + itemList.get(position).getItemName();
        holder.listedItem.setText(concatItem);
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public static class ItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public OnRowClickListener onRowClickListener;

        public TextView listedItem;

        public ItemViewHolder(@NonNull View itemView, OnRowClickListener onRowClickListener) {
            super(itemView);

            listedItem = itemView.findViewById(R.id.tvItemInList);
            this.onRowClickListener = onRowClickListener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            onRowClickListener.onItemClick(getAdapterPosition());
        }
    }

    public interface OnRowClickListener {
        void onItemClick(int position);
    }
}