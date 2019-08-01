package com.sqisland.tutorial.recipes.recipe;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.sqisland.tutorial.recipes.R;
import com.sqisland.tutorial.recipes.data.local.Favorites;
import com.sqisland.tutorial.recipes.data.local.RecipeStore;
import com.sqisland.tutorial.recipes.data.local.SharedPreferencesFavorites;
import com.sqisland.tutorial.recipes.data.model.Recipe;
import com.sqisland.tutorial.recipes.injection.RecipeApplication;

public class RecipeActivity extends AppCompatActivity {
    public static final String KEY_ID = "id";

    TextView titleTV;
    TextView descTV;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);


        titleTV = findViewById(R.id.title);
        descTV = findViewById(R.id.description);

        RecipeStore store = new RecipeStore(this, "recipes");
        String id = getIntent().getStringExtra(KEY_ID);
        final Recipe recipe = store.getRecipe(id);

        if(recipe == null){
            titleTV.setVisibility(View.GONE);
            descTV.setText(R.string.recipe_not_found);
            return;
        }


        RecipeApplication app = (RecipeApplication) getApplication();
        final Favorites favorites = app.getFavorites();

        boolean favorite = favorites.get(recipe.id);

        titleTV.setText(recipe.title);
        titleTV.setSelected(favorite);
        descTV.setText(recipe.description);

        titleTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean result = favorites.toggle(recipe.id);
                titleTV.setSelected(result);
            }
        });



    }
}
