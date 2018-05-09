package hu.bme.r0uj46.cookbook.interactor.recipes;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import hu.bme.r0uj46.cookbook.interactor.recipes.events.GetRecipeEvent;
import hu.bme.r0uj46.cookbook.interactor.recipes.events.GetRecipesEvent;
import hu.bme.r0uj46.cookbook.interactor.recipes.events.RemoveRecipeEvent;
import hu.bme.r0uj46.cookbook.interactor.recipes.events.SaveRecipeEvent;
import hu.bme.r0uj46.cookbook.model.Recipe;
import hu.bme.r0uj46.cookbook.network.api.RecipeApi;
import hu.bme.r0uj46.cookbook.network.dto.RecipeDto;
import hu.bme.r0uj46.cookbook.repository.recipes.RecipeRepository;
import retrofit2.Call;
import retrofit2.Response;

import static hu.bme.r0uj46.cookbook.CookbookApplication.injector;

public class RecipesInteractor {
    @Inject
    EventBus bus;

    @Inject
    RecipeApi recipeApi;

    public RecipesInteractor() {
        injector.inject(this);
    }

    public void getRecipes() {
        GetRecipesEvent event = new GetRecipesEvent();

        try {
            Response<List<RecipeDto>> response = recipeApi.listRecipes().execute();
            if (response.code() != 200) {
                throw new Exception("Result code is not 200");
            }
            event.setCode(response.code());

            List<Recipe> recipes = new ArrayList<>();
            for (RecipeDto r : response.body()) {
                recipes.add(Recipe.fromDto(r));
            }
            event.setRecipes(recipes);
            //event.setRecipes(recipeApi.listRecipes());
        } catch (Exception e) {
            event.setThrowable(e);
        } finally {
            bus.post(event);
        }
    }

    public void getRecipe(Long id) {
        GetRecipeEvent event = new GetRecipeEvent();

        try {
            Response<RecipeDto> response = recipeApi.getRecipeById(id).execute();
            if (response.code() != 200) {
                throw new Exception("Result code is not 200");
            }
            event.setCode(response.code());
            event.setRecipe(Recipe.fromDto(response.body()));
            //event.setRecipe(repository.getRecipe(id));
        } catch (Exception e) {
            event.setThrowable(e);
        } finally {
            bus.post(event);
        }
    }

    public void saveRecipe(final Recipe recipe) {
        SaveRecipeEvent event = new SaveRecipeEvent();

        try {
            Response<Void> response = recipeApi.addRecipe(recipe.toDto()).execute();

            if (response.code() != 200) {
                throw new Exception("Result code is not 200");
            }
            event.setCode(response.code());
            event.setRecipe(recipe);
            //repository.saveRecipe(recipe);
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
            Response<Void> response = recipeApi.deleteRecipe(recipe.getId()).execute();
            if (response.code() != 200) {
                throw new Exception("Result code is not 200");
            }
            event.setCode(response.code());
            event.setRecipe(recipe);
            //repository.removeRecipe(recipe);
        } catch (Exception e) {
            event.setThrowable(e);
        } finally {
            bus.post(event);
        }
    }
}
