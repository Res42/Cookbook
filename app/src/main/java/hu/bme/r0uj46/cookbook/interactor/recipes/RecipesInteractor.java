package hu.bme.r0uj46.cookbook.interactor.recipes;

import java.util.ArrayList;
import java.util.List;

import hu.bme.r0uj46.cookbook.model.Recipe;

public class RecipesInteractor {
    public List<Recipe> getRecipes() {
        ArrayList<Recipe> recipes = new ArrayList<>();
        for (int i = 1; i < 30; i++) {
            Recipe recipe = new Recipe();
            recipe.setId(i);
            recipe.setName("Kutya " + i);
            recipe.setPreparationTime("3 óra");
            recipes.add(recipe);
        }

        return recipes;
    }

    public Recipe getRecipe(int recipeId) {
        Recipe recipe = new Recipe();
        recipe.setId(recipeId);
        recipe.setName("Kutya");
        recipe.setPreparationTime("3 óra");
        recipe.setHowToMake("Ügyesen");
        recipe.setIngredients("1 kutya\n1 ügyesség");

        return recipe;
    }
}
