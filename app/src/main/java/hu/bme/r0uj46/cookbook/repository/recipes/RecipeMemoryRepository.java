package hu.bme.r0uj46.cookbook.repository.recipes;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import hu.bme.r0uj46.cookbook.model.Recipe;

public class RecipeMemoryRepository implements RecipeRepository {
    private static List<Recipe> recipes;

    @Override
    public void open(Context context) {
        recipes = new ArrayList<>();
    }

    @Override
    public void close() {

    }

    @Override
    public List<Recipe> getRecipes() {
        return recipes;
    }

    @Override
    public void saveRecipe(Recipe recipe) {
        recipes.add(recipe);
    }

    @Override
    public void updateRecipes(List<Recipe> recipes) {
        for (int i = 0; i < RecipeMemoryRepository.recipes.size(); i++) {
            Recipe myRecipe = RecipeMemoryRepository.recipes.get(i);
            for (Recipe recipe : recipes) {
                if (myRecipe.getId().equals(recipe.getId())) {
                    RecipeMemoryRepository.recipes.set(i, recipe);
                }
            }
        }
    }

    @Override
    public void removeRecipe(Recipe recipe) {
        recipes.remove(recipe);
    }

    @Override
    public boolean isInDB(Recipe recipe) {
        return recipes.contains(recipe);
    }
}
