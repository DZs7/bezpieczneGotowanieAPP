package com.example.bezpiecznegotowanie.search_result;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.bezpiecznegotowanie.Recipe;
import com.example.bezpiecznegotowanie.R;
import com.example.bezpiecznegotowanie.search_result_detailed.RecipeDetails;

import java.lang.ref.WeakReference;
import java.util.List;

public class FindRecipeRequest extends RecyclerView.Adapter<FindRecipeRequest.ViewHolder> {

    private List<Recipe> mRecipes;
    private Context mContext;

    public static final String RECIPE_DETAILS = "com.example.bezpiecznegotowanie.search_resutl.FindRecipeRequest.lRecipe";

    FindRecipeRequest(Context context, List<Recipe> recipes){
        this.mRecipes = recipes;
        this.mContext = context;
    }

    @NonNull
    @Override
    public FindRecipeRequest.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new FindRecipeRequest.ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.recipes_item, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull FindRecipeRequest.ViewHolder viewHolder, int i) {
        viewHolder.bind(mRecipes.get(i));
    }

    @Override
    public int getItemCount() {
        return mRecipes.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private ImageView mRecipeImage;
        private TextView mRecipeLabel;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            mRecipeImage = itemView.findViewById(R.id.recipe_image);
            mRecipeLabel = itemView.findViewById(R.id.recipe_label);
            itemView.setOnClickListener(this);
        }

        void bind(Recipe currentRecipe){
            mRecipeLabel.setText(currentRecipe.getLabel());
            new ImageDownloadTask(mRecipeImage).execute(currentRecipe);
        }


        @Override
        public void onClick(View v) {
            Intent intent = new Intent(mContext, RecipeDetails.class);
            Log.d("App-com.example.bezpiecznegotowanie",mRecipes.get(getAdapterPosition()).getImageBitmap().toString());
            intent.putExtra(RECIPE_DETAILS, mRecipes.get(getAdapterPosition()));
            mContext.startActivity(intent);
        }
    }

    static class ImageDownloadTask extends AsyncTask<Recipe, Void, Bitmap>{

        private WeakReference<ImageView> mImageView;

        ImageDownloadTask(ImageView imageView){
            this.mImageView = new WeakReference<>(imageView);
        }

        @Override
        protected Bitmap doInBackground(Recipe... recipes) {
            return new ImageDownloadRecipe(recipes[0]).download();
        }

        @Override
        protected void onPostExecute(Bitmap recipeImage) {
            super.onPostExecute(recipeImage);
            mImageView.get().setImageBitmap(recipeImage);
        }
    }


}
