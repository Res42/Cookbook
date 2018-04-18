package hu.bme.r0uj46.cookbook.ui.details;

import android.util.Log;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import javax.inject.Inject;

import hu.bme.r0uj46.cookbook.CookbookApplication;
import hu.bme.r0uj46.cookbook.R;
import hu.bme.r0uj46.cookbook.interactor.recipes.RecipesInteractor;
import hu.bme.r0uj46.cookbook.interactor.recipes.events.GetRecipeEvent;
import hu.bme.r0uj46.cookbook.interactor.recipes.events.RemoveRecipeEvent;
import hu.bme.r0uj46.cookbook.interactor.recipes.events.SaveRecipeEvent;
import hu.bme.r0uj46.cookbook.model.Recipe;
import hu.bme.r0uj46.cookbook.ui.Presenter;

public class DetailsPresenter extends Presenter<DetailsScreen> {
    @Inject
    RecipesInteractor recipesInteractor;

    @Inject
    EventBus bus;

    @Override
    public void attach(DetailsScreen screen) {
        super.attach(screen);
        CookbookApplication.injector.inject(this);
        bus.register(this);
    }

    @Override
    public void detach() {
        bus.unregister(this);
        super.detach();
    }

    public void loadNewRecipe() {
        screen.displayRecipe(new Recipe());
    }

    //region Load recipe
    public void loadRecipe(Long recipeId) {
        recipesInteractor.getRecipe(recipeId);

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onLoadRecipe(GetRecipeEvent event) {
        if (event.getThrowable() != null) {
            event.getThrowable().printStackTrace();
            if (screen != null) {
                screen.showMessage(R.string.snackbar_error);
            }
            Log.e("Networking", "Error loading recipe", event.getThrowable());
        } else {
            if (screen != null) {
                screen.displayRecipe(event.getRecipe());
            }
        }
    }
    //endregion

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
