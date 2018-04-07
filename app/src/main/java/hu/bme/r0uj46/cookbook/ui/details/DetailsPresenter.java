package hu.bme.r0uj46.cookbook.ui.details;

import hu.bme.r0uj46.cookbook.model.Recipe;
import hu.bme.r0uj46.cookbook.ui.Presenter;

public class DetailsPresenter extends Presenter<DetailsScreen> {
    @Override
    public void attach(DetailsScreen screen) {
        super.attach(screen);
    }

    @Override
    public void detach() {
        super.detach();
    }

    public void loadRecipe(int recipeId) {
        // TODO
        Recipe recipe = new Recipe();
        recipe.setId(recipeId);
        recipe.setName("Kutya");
        recipe.setPreparationTime("3 óra");
        recipe.setHowToMake("Ügyesen");
        recipe.setIngredients("1 kutya\n1 ügyesség");

        screen.displayRecipe(recipe);
    }

    public void loadNewRecipe() {
        // TODO
        Recipe recipe = new Recipe();
        screen.displayRecipe(recipe);
    }

    public void saveRecipe(Recipe recipe) {
        // TODO
        screen.backToPreviousActivity();
    }

    public void deleteRecipe(Recipe recipe) {
        // TODO

        screen.backToPreviousActivity();
    }
}
