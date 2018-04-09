package hu.bme.r0uj46.cookbook.ui.details;

import javax.inject.Inject;

import hu.bme.r0uj46.cookbook.CookbookApplication;
import hu.bme.r0uj46.cookbook.interactor.recipes.RecipesInteractor;
import hu.bme.r0uj46.cookbook.model.Recipe;
import hu.bme.r0uj46.cookbook.ui.Presenter;

public class DetailsPresenter extends Presenter<DetailsScreen> {
    @Inject
    RecipesInteractor recipesInteractor;

    @Override
    public void attach(DetailsScreen screen) {
        super.attach(screen);
        CookbookApplication.injector.inject(this);
    }

    @Override
    public void detach() {
        super.detach();
    }

    public void loadRecipe(int recipeId) {
        // TODO
        screen.displayRecipe(recipesInteractor.getRecipe(recipeId));
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
