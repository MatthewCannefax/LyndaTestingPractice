package com.sqisland.tutorial.recipes.recipe;

import android.view.View;

import com.sqisland.tutorial.recipes.R;
import com.sqisland.tutorial.recipes.data.local.Favorites;
import com.sqisland.tutorial.recipes.data.local.RecipeStore;
import com.sqisland.tutorial.recipes.data.model.Recipe;

public class RecipePresenter implements RecipeContract.Listener{
    private final RecipeStore store;
    private final Favorites favorites;
    private Recipe recipe;
    private final RecipeContract.View view;

    public RecipePresenter(RecipeStore store, RecipeContract.View view, Favorites favorites) {
        this.store = store;
        this.view = view;
        this.favorites = favorites;
    }

    public void loadRecipe(String id){
        recipe = store.getRecipe(id);
        if(recipe == null){
            view.showRecipeNotFoundError();
        }else {
            view.setTitle(recipe.title);
            view.setDescription(recipe.description);
            view.setFavorites(favorites.get(recipe.id));
        }
    }

    public void toggleFavorite() {
        boolean result = favorites.toggle(recipe.id);
        view.setFavorites(result);
    }
}
