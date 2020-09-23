package com.example.papaassistant.Adapter;

import android.content.Context;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.papaassistant.Instruction;
import com.example.papaassistant.R;
import com.example.papaassistant.Recipe;

public class ModifyRecipeViewPagerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private Recipe recipe;

    public ModifyRecipeViewPagerAdapter(Context context, Recipe recipe) {
        this.context = context;
        this.recipe = recipe;
    }

    static class ViewHolder0 extends RecyclerView.ViewHolder {
        private ImageView imageView;

        public ViewHolder0(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageViewRecipe);
        }
    }

    static class ViewHolder1 extends RecyclerView.ViewHolder {
        private EditText editTextItemRecipe;
        private EditText editTextStep;

        public ViewHolder1(@NonNull View itemView) {
            super(itemView);
            editTextItemRecipe = itemView.findViewById(R.id.editTextItemRecipe);
            editTextItemRecipe.setMovementMethod(new ScrollingMovementMethod());
            editTextStep = itemView.findViewById(R.id.editTextStep);
            editTextStep.setEnabled(false);
        }
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
            Glide.with(context).load(recipe.recipe.getImageLink()).placeholder(R.drawable.no_image).into(viewHolder.imageView);
        }
        else {
            ViewHolder1 viewHolder = (ViewHolder1) holder;
            if (position == 1) {
                viewHolder.editTextItemRecipe.setText(recipe.recipe.getIngredients());
                viewHolder.editTextStep.setText(context.getString(R.string.step, 0));
            }
            else {
                int positionInInstruction = position - 2;
                Instruction instruction = recipe.instructions.get(positionInInstruction);
                viewHolder.editTextItemRecipe.setText(instruction.getInstruction());
                viewHolder.editTextStep.setText(context.getString(R.string.step, instruction.getStep()));
            }
        }
    }

    @Override
    public int getItemCount() {
        return recipe.instructions.size() + 2;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) return 0;
        else return 1;
    }
}
