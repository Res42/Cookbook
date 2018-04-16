package hu.bme.r0uj46.cookbook.interactor.recipes;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

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

    public List<Recipe> getRecipes() {
        ArrayList<Recipe> recipes = new ArrayList<>();
        for (Long i = 1L; i < 30L; i++) {
            Recipe recipe = new Recipe();
            recipe.setId(i);
            recipe.setName("Kutya " + i);
            recipe.setPreparationTime("3 óra");
            recipes.add(recipe);
        }

        return recipes;
    }

    public Recipe getRecipe(Long recipeId) {
        Recipe recipe = new Recipe();
        recipe.setId(recipeId);
        recipe.setName("Kutya");
        recipe.setPreparationTime("3 óra");
        recipe.setHowToMake("Ügyesen");
        recipe.setIngredients("1 kutya\n1 ügyesség");

        return recipe;
    }
}
