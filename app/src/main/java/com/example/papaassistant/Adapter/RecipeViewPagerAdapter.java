package com.example.papaassistant.Adapter;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.PorterDuff;
import android.graphics.drawable.BitmapDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.papaassistant.R;
import com.example.papaassistant.Recipe;

public class RecipeViewPagerAdapter extends RecyclerView.Adapter<RecipeViewPagerAdapter.ViewHolder> {
    static final String LOG_TAG = RecipeViewPagerAdapter.class.getSimpleName();

    Context context;
    Recipe recipe;
    Bitmap bitmap;

    public RecipeViewPagerAdapter(Context context, Recipe recipe) {
        this.context = context;
        this.recipe = recipe;
        // TODO: load image here
//        String urllink = this.recipe.recipe.getImageLink();
//        Log.d(LOG_TAG, urllink);
//        try {
//            URL url = new URL(urllink);
//            bitmap = BitmapFactory.decodeStream(url.openConnection().getInputStream());
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_recipe, parent, false);;
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // TODO: finish this
        bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.test);
        if (position == 0) {
            holder.textViewItemRecipe.setBackgroundTintList(ColorStateList.valueOf(0));
        }
        holder.textViewItemRecipe.setBackground(new BitmapDrawable(context.getResources(), bitmap));
        holder.textViewItemRecipe.setText("Testing");
        holder.textViewItemRecipe.setBackgroundTintMode(PorterDuff.Mode.SCREEN);
        holder.textViewItemRecipe.setBackgroundTintList(ColorStateList.valueOf(0xdddddddd));
    }

    @Override
    public int getItemCount() {
        return recipe.instructions.size() + 2;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textViewItemRecipe;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewItemRecipe = itemView.findViewById(R.id.textViewItemRecipe);
        }
    }
}