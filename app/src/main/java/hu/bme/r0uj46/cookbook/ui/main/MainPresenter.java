package hu.bme.r0uj46.cookbook.ui.main;

import android.util.Log;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import javax.inject.Inject;

import hu.bme.r0uj46.cookbook.CookbookApplication;
import hu.bme.r0uj46.cookbook.R;
import hu.bme.r0uj46.cookbook.interactor.recipes.RecipesInteractor;
import hu.bme.r0uj46.cookbook.interactor.recipes.events.GetRecipesEvent;
import hu.bme.r0uj46.cookbook.model.Recipe;
import hu.bme.r0uj46.cookbook.ui.Presenter;

public class MainPresenter extends Presenter<MainScreen> {
    @Inject
    RecipesInteractor recipesInteractor;

    @Inject
    EventBus bus;

    @Override
    public void attach(MainScreen screen) {
        super.attach(screen);
        CookbookApplication.injector.inject(this);
        bus.register(this);
    }

    @Override
    public void detach() {
        bus.unregister(this);
        super.detach();
    }

    public void recipeDetails(Recipe recipe) {
        screen.showRecipeDetails(recipe);
    }

    public void newRecipe() {
        screen.showNewRecipe();
    }

    //region Refresh recipes
    public void refreshRecipes() {
        recipesInteractor.getRecipes();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onRefreshRecipes(GetRecipesEvent event) {
        if (event.getThrowable() != null) {
            event.getThrowable().printStackTrace();
            if (screen != null) {
                screen.showMessage(R.string.snackbar_error);
            }
            Log.e("Networking", "Error reading recipes", event.getThrowable());
        } else {
            if (screen != null) {
                screen.listRecipes(event.getRecipes());
            }
        }
    }
    //endregion
}
