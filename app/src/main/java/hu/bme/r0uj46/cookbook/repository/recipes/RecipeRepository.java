package hu.bme.r0uj46.cookbook.repository.recipes;

import android.content.Context;

import java.util.List;

import hu.bme.r0uj46.cookbook.model.Recipe;

public interface RecipeRepository {
    void open(Context context);

    void close();

    List<Recipe> getRecipes();

    void saveRecipe(Recipe recipe);

    void updateRecipes(List<Recipe> recipes);

    void removeRecipe(Recipe recipe);

    boolean isInDB(Recipe recipe);
}
