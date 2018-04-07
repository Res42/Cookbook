package hu.bme.r0uj46.cookbook.ui.main;

import java.util.ArrayList;

import hu.bme.r0uj46.cookbook.model.Recipe;
import hu.bme.r0uj46.cookbook.ui.Presenter;

public class MainPresenter extends Presenter<MainScreen> {
    @Override
    public void attach(MainScreen screen) {
        super.attach(screen);
    }

    @Override
    public void detach() {
        super.detach();
    }

    public void refreshRecipes() {
        ArrayList<Recipe> recipes = new ArrayList<>();
        for (int i = 1; i < 30; i++) {
            Recipe recipe = new Recipe();
            recipe.setId(i);
            recipe.setName("Kutya " + i);
            recipe.setPreparationTime("3 óra");
            recipes.add(recipe);
        }

        screen.listRecipes(recipes);
    }

    public void recipeDetails(Recipe recipe) {
        screen.showRecipeDetails(recipe.getId());
    }

    public void newRecipe() {
        screen.showNewRecipe();
    }
}
