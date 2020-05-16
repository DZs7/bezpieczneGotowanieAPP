package com.example.bezpiecznegotowanie.search_result_detailed;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.bezpiecznegotowanie.Recipe;
import com.example.bezpiecznegotowanie.search_result.FindRecipeRequest;
import com.example.bezpiecznegotowanie.R;

public class RecipeDetails extends AppCompatActivity {

    private ImageView mRecipeDetailsImage;
    private TextView mRecipeDetailsLabel;
    private TextView mRecipeDetailsIngredients;
    private TextView mRecipeDetailsPreparation ;
    private Recipe mRecipe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_details);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mRecipeDetailsImage = findViewById(R.id.recipe_details_image);
        mRecipeDetailsLabel = findViewById(R.id.recipe_details_label);
        mRecipeDetailsIngredients = findViewById(R.id.recipe_details_ingredients);
        mRecipeDetailsPreparation = findViewById(R.id.recipe_details_preparation);

        mRecipe = getIntent().getParcelableExtra(FindRecipeRequest.RECIPE_DETAILS);

        mRecipeDetailsImage.setImageBitmap(mRecipe.getImageBitmap());
        mRecipeDetailsLabel.setText(mRecipe.getLabel());
        mRecipeDetailsIngredients.setText(mRecipe.ingredientsListToString());
        mRecipeDetailsPreparation.setText(getString(R.string.preparation_details_disclaimer) + mRecipe.getSourceURL());

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

}
