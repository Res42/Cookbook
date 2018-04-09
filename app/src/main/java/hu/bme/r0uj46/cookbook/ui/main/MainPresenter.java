package hu.bme.r0uj46.cookbook.ui.main;

import javax.inject.Inject;

import hu.bme.r0uj46.cookbook.CookbookApplication;
import hu.bme.r0uj46.cookbook.interactor.recipes.RecipesInteractor;
import hu.bme.r0uj46.cookbook.model.Recipe;
import hu.bme.r0uj46.cookbook.ui.Presenter;

public class MainPresenter extends Presenter<MainScreen> {
    @Inject
    RecipesInteractor recipesInteractor;

    @Override
    public void attach(MainScreen screen) {
        super.attach(screen);
        CookbookApplication.injector.inject(this);
    }

    @Override
    public void detach() {
        super.detach();
    }

    public void refreshRecipes() {
        screen.listRecipes(recipesInteractor.getRecipes());
    }

    public void recipeDetails(Recipe recipe) {
        screen.showRecipeDetails(recipe.getId());
    }

    public void newRecipe() {
        screen.showNewRecipe();
    }
}
