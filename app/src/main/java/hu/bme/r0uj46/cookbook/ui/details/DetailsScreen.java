package hu.bme.r0uj46.cookbook.ui.details;

import hu.bme.r0uj46.cookbook.model.Recipe;

public interface DetailsScreen {
    void displayRecipe(Recipe recipe);
    void backToPreviousActivity();
}
