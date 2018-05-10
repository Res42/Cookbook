package hu.bme.r0uj46.cookbook.ui.main;

import android.content.ClipData;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.io.File;
import java.util.List;

import hu.bme.r0uj46.cookbook.R;
import hu.bme.r0uj46.cookbook.model.Recipe;

public class RecipesAdapter extends RecyclerView.Adapter<RecipesAdapter.ViewHolder> {

    private Context context;
    private List<Recipe> recipesList;

    public RecipesAdapter(Context context, List<Recipe> recipesList) {
        this.context = context;
        this.recipesList = recipesList;
    }

    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.card_recipe, viewGroup, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Recipe recipe = recipesList.get(position);

        holder.tvName.setText(recipe.getName());
        holder.tvPreparationTime.setText(recipe.getPreparationTime());
        if (recipe.getPictureUri() != null) {
            Glide.with(context).load(recipe.getPictureUri()).into(holder.ivImage);
        }
    }

    @Override
    public int getItemCount() {
        return recipesList.size();
    }

    public Recipe getItem(int position) {
        return recipesList.get(position);
    }

    protected static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView ivImage;
        public TextView tvName;
        public TextView tvPreparationTime;

        public ViewHolder(View itemView) {
            super(itemView);
            ivImage = (ImageView) itemView.findViewById(R.id.ivImage);
            tvName = (TextView) itemView.findViewById(R.id.tvName);
            tvPreparationTime = (TextView) itemView.findViewById(R.id.tvPreparationTime);
        }
    }
}