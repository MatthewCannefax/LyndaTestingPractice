package com.sqisland.tutorial.recipes;

import com.sqisland.tutorial.recipes.data.local.Favorites;
import com.sqisland.tutorial.recipes.data.local.InMemoryFavorites;
import com.sqisland.tutorial.recipes.injection.RecipeApplication;

public class TestRecipeApplication extends RecipeApplication {
    private final Favorites favorites = new InMemoryFavorites();

    @Override
    public Favorites getFavorites() {
        return favorites;
    }
}
