package com.example.papaassistant.Adapter;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.MultiTransformation;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.example.papaassistant.R;
import com.example.papaassistant.Recipe;

import java.util.ArrayList;

public class SearchListAdapter extends RecyclerView.Adapter<SearchListAdapter.ViewHolder> {
    private Context context;
    private ArrayList<Recipe> recipes;

    public SearchListAdapter(Context context, ArrayList<Recipe> recipes) {
        this.context = context;
        this.recipes = recipes;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View convertView = inflater.inflate(R.layout.item_list_recipe, parent, false);
        return new ViewHolder(convertView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Recipe recipe = recipes.get(position);
        holder.timeTextView.setText(String.valueOf(recipe.recipe.getReadyTime()));
        holder.servingTextView.setText(String.valueOf(recipe.recipe.getNumberOfPeople()));
        holder.healthyTextView.setText(String.valueOf(recipe.recipe.getHealthScore()));
        holder.nameTextView.setText(recipe.recipe.getName());
        Glide.with(context).load(recipe.recipe.getImageLink())
                .placeholder(R.drawable.no_image)
                .transform(new MultiTransformation<Bitmap>(new CenterCrop(),
                        new RoundedCorners((int) (5 * Resources.getSystem().getDisplayMetrics().density))))
                .into(holder.imageView);
//        Log.d("Holder", String.valueOf(position));
    }

    @Override
    public int getItemCount() {
        return recipes.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView nameTextView;
        public TextView servingTextView;
        public TextView timeTextView;
        public TextView healthyTextView;
        public ImageView imageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.dishImageView);
            nameTextView = itemView.findViewById(R.id.recipeName);
            servingTextView = itemView.findViewById(R.id.recipeNumOfPeople);
            healthyTextView = itemView.findViewById(R.id.recipeHealthy);
            timeTextView = itemView.findViewById(R.id.recipeTime);
        }

        @Override
        public void onClick(View v) {
            //TODO:
        }
    }

}
