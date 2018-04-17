package hu.bme.r0uj46.cookbook.interactor.recipes;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import hu.bme.r0uj46.cookbook.interactor.recipes.events.GetRecipesEvent;
import hu.bme.r0uj46.cookbook.interactor.recipes.events.RemoveRecipeEvent;
import hu.bme.r0uj46.cookbook.interactor.recipes.events.SaveRecipeEvent;
import hu.bme.r0uj46.cookbook.model.Recipe;
import hu.bme.r0uj46.cookbook.repository.recipes.RecipeRepository;

import static hu.bme.r0uj46.cookbook.CookbookApplication.injector;

public class RecipesInteractor {
    @Inject
    EventBus bus;

    @Inject
    RecipeRepository repository;

    public RecipesInteractor() {
        injector.inject(this);
    }

    public void getRecipes() {
        GetRecipesEvent event = new GetRecipesEvent();

        try {
            event.setRecipes(repository.getRecipes());
        } catch (Exception e) {
            event.setThrowable(e);
        } finally {
            bus.post(event);
        }
    }

    public void saveRecipe(final Recipe recipe) {
        SaveRecipeEvent event = new SaveRecipeEvent();
        event.setRecipe(recipe);

        try {
            if (repository.isInDB(recipe)) {
                repository.updateRecipes(Collections.singletonList(recipe));
            } else {
                repository.saveRecipe(recipe);
            }
        } catch (Exception e) {
            event.setThrowable(e);
        } finally {
            bus.post(event);
        }
    }

    public void removeRecipe(Recipe recipe) {
        RemoveRecipeEvent event = new RemoveRecipeEvent();
        event.setRecipe(recipe);

        try {
            repository.removeRecipe(recipe);
        } catch (Exception e) {
            event.setThrowable(e);
        } finally {
            bus.post(event);
        }
    }
}
