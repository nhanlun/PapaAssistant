package com.example.papaassistant.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.papaassistant.DishType;
import com.example.papaassistant.R;

import java.util.ArrayList;

public class ViewPagerAdapter extends RecyclerView.Adapter<ViewPagerAdapter.ViewHolder> {

    private Context context;
    private ArrayList<DishType> dishTypeArrayList;

    public ViewPagerAdapter(Context context, ArrayList<DishType> dishTypeArrayList) {
        this.context = context;
        this.dishTypeArrayList = dishTypeArrayList;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView textView;
        private ImageView imageView;

        public ViewHolder(@NonNull  View itemView){
            super(itemView);
            imageView = itemView.findViewById(R.id.imgDishType);
            textView = itemView.findViewById(R.id.textDishType);
        }

    }

    @NonNull
    @Override
    public ViewPagerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View page = inflater.inflate(R.layout.item_page,parent,false);
        ViewHolder viewHolder = new ViewHolder(page);
        return viewHolder;
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
}
