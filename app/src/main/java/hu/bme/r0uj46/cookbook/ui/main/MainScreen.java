package hu.bme.r0uj46.cookbook.ui.main;

import java.util.List;

import hu.bme.r0uj46.cookbook.model.Recipe;

public interface MainScreen {
    void listRecipes(List<Recipe> recipes);

    void showRecipeDetails(Long recipeId);
    void showNewRecipe();
}
