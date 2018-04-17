package hu.bme.r0uj46.cookbook.ui.details;

import android.util.Log;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import javax.inject.Inject;

import hu.bme.r0uj46.cookbook.CookbookApplication;
import hu.bme.r0uj46.cookbook.R;
import hu.bme.r0uj46.cookbook.interactor.recipes.RecipesInteractor;
import hu.bme.r0uj46.cookbook.interactor.recipes.events.RemoveRecipeEvent;
import hu.bme.r0uj46.cookbook.interactor.recipes.events.SaveRecipeEvent;
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

    public void loadRecipe(Recipe recipe) {
        screen.displayRecipe(recipe);
    }

    public void loadNewRecipe() {
        screen.displayRecipe(new Recipe());
    }

    //region Save recipe
    public void saveRecipe(Recipe recipe) {
        recipesInteractor.saveRecipe(recipe);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onSaveRecipe(SaveRecipeEvent event) {
        if (event.getThrowable() != null) {
            event.getThrowable().printStackTrace();
            if (screen != null) {
                screen.showMessage(R.string.snackbar_error);
            }
            Log.e("Networking", "Error saving recipe", event.getThrowable());
        } else {
            if (screen != null) {
                screen.backToPreviousActivity();
            }
        }
    }
    //endregion

    //region Delete recipe
    public void deleteRecipe(Recipe recipe) {
        recipesInteractor.removeRecipe(recipe);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onDeleteRecipe(RemoveRecipeEvent event) {
        if (event.getThrowable() != null) {
            event.getThrowable().printStackTrace();
            if (screen != null) {
                screen.showMessage(R.string.snackbar_error);
            }
            Log.e("Networking", "Error deleting recipe", event.getThrowable());
        } else {
            if (screen != null) {
                screen.backToPreviousActivity();
            }
        }
    }
    //endregion
}
