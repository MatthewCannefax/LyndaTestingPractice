package com.sqisland.tutorial.recipes.recipe;

public interface RecipeContract {
    interface View{
        void showRecipeNotFoundError();

        void setTitle(String title);

        void setDescription(String description);

        void setFavorites(boolean favorite);
    }
    interface Listener{

    }
}
