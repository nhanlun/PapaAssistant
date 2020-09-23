package com.example.papaassistant.Adapter;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.papaassistant.Instruction;
import com.example.papaassistant.R;
import com.example.papaassistant.Recipe;

public class RecipeViewPagerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    static final String LOG_TAG = RecipeViewPagerAdapter.class.getSimpleName();

    Context context;
    Recipe recipe;
    Drawable drawable;

    public static class ViewHolder0 extends RecyclerView.ViewHolder {
        public ImageView imageView;

        public ViewHolder0(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageViewRecipe);
        }
    }

    public static class ViewHolder1 extends RecyclerView.ViewHolder {
        public TextView textViewItemRecipe;
        public TextView textViewStep;

        public ViewHolder1(@NonNull View itemView) {
            super(itemView);
            textViewItemRecipe = itemView.findViewById(R.id.textViewItemRecipe);
            textViewItemRecipe.setMovementMethod(new ScrollingMovementMethod());
            textViewStep = itemView.findViewById(R.id.textViewStep);
        }
    }

    public RecipeViewPagerAdapter(Context context, Recipe recipe) {
        this.context = context;
        this.recipe = recipe;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) return 0;
        else return 1;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view;
        if (viewType == 0) {
            view = inflater.inflate(R.layout.item_recipe_image, parent, false);
            return new ViewHolder0(view);
        }
        else {
            view = inflater.inflate(R.layout.item_recipe, parent, false);
            return new ViewHolder1(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (position == 0) {
            ViewHolder0 viewHolder = (ViewHolder0) holder;
            Glide.with(context).load(recipe.recipe.getImageLink()).placeholder(R.drawable.no_image)
                    .into(viewHolder.imageView);
        }
        else {
            ViewHolder1 viewHolder = (ViewHolder1) holder;
            if (position == 1) {
                viewHolder.textViewItemRecipe.setText(recipe.recipe.getIngredients());
                viewHolder.textViewStep.setText(context.getString(R.string.step, 0));
            }
            else {
                int positionInInstruction = position - 2;
                Instruction instruction = recipe.instructions.get(positionInInstruction);
                viewHolder.textViewItemRecipe.setText(instruction.getInstruction());
                viewHolder.textViewStep.setText(context.getString(R.string.step, positionInInstruction + 1));
            }
        }
    }

    @Override
    public int getItemCount() {
        return recipe.instructions.size() + 2;
    }
}