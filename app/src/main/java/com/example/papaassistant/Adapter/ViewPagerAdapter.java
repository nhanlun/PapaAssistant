package com.example.papaassistant.Adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.papaassistant.Activity.SearchActivity;
import com.example.papaassistant.DishType;
import com.example.papaassistant.R;

import java.sql.ClientInfoStatus;
import java.util.ArrayList;

public class ViewPagerAdapter extends RecyclerView.Adapter<ViewPagerAdapter.ViewHolder> {
    private static final String LOG_TAG = ViewPagerAdapter.class.getSimpleName();

    private Context context;
    private ArrayList<DishType> dishTypeArrayList;
    private static ItemClickListener itemClickListener;

    public ViewPagerAdapter(Context context, ArrayList<DishType> dishTypeArrayList) {
        this.context = context;
        this.dishTypeArrayList = dishTypeArrayList;
    }

    public void setOnItemClickListener(ItemClickListener itemClickListener) {
        ViewPagerAdapter.itemClickListener = itemClickListener;
    }

    static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView textView;

        private ImageView imageView;

        public ViewHolder(@NonNull View itemView){
            super(itemView);
            itemView.setOnClickListener(this);
            imageView = itemView.findViewById(R.id.imgDishType);
            textView = itemView.findViewById(R.id.textDishType);
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            itemClickListener.onItemClick(position, v);
        }
    }

    @NonNull
    @Override
    public ViewPagerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View page = inflater.inflate(R.layout.item_page,parent,false);
        return new ViewHolder(page);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewPagerAdapter.ViewHolder holder, int position) {
        DishType dishType = dishTypeArrayList.get(position);
        holder.imageView.setImageResource(dishType.getImgID());
        holder.textView.setText(dishType.getName());
    }

    @Override
    public int getItemCount() {
        return dishTypeArrayList.size();
    }

    public interface ItemClickListener {
        void onItemClick(int position, View v);
    }
}
