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

public class RecipeActivity extends AppCompatActivity implements RecipeContract.View {
    public static final String KEY_ID = "id";

    private TextView titleTV;
    private TextView descTV;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        //Step 1: Set up the UI
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);


        titleTV = findViewById(R.id.title);
        descTV = findViewById(R.id.description);

        //Step 2: Load recipe from store
        RecipeStore store = new RecipeStore(this, "recipes");
        String id = getIntent().getStringExtra(KEY_ID);
        RecipeApplication app = (RecipeApplication) getApplication();
        final Favorites favorites = app.getFavorites();
        final RecipePresenter presenter = new RecipePresenter(store, this, favorites);
        presenter.loadRecipe(id);

        //Step 3: if recipe is null, show error. This is done in the presenter

        //step 4: if recipe is not null, show the recipe. This is done in the presenter

        //Step 5: when the title is clicked, toggle favorite
        titleTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.toggleFavorite();
            }
        });
    }

    @Override
    public void showRecipeNotFoundError() {
        titleTV.setVisibility(View.GONE);
        descTV.setText(R.string.recipe_not_found);
    }

    @Override
    public void setTitle(String title) {
        titleTV.setText(title);
    }

    @Override
    public void setDescription(String description) {
        descTV.setText(description);
    }

    @Override
    public void setFavorites(boolean favorite) {
        titleTV.setSelected(favorite);
    }
}
