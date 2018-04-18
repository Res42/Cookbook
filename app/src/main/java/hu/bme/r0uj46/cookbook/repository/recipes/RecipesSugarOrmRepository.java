package hu.bme.r0uj46.cookbook.repository.recipes;

import android.content.Context;

import com.orm.SugarContext;
import com.orm.SugarRecord;

import java.util.ArrayList;
import java.util.List;

import hu.bme.r0uj46.cookbook.model.Recipe;

public class RecipesSugarOrmRepository implements RecipeRepository {
    @Override
    public void open(Context context) {
        SugarContext.init(context);
    }

    @Override
    public void close() {
        SugarContext.terminate();
    }

    @Override
    public List<Recipe> getRecipes() {
        return Recipe.listAll(Recipe.class);
    }

    @Override
    public Recipe getRecipe(Long id) {
        return Recipe.findById(Recipe.class, id);
    }

    @Override
    public void saveRecipe(Recipe recipe) {
        recipe.save();
    }

    @Override
    public void removeRecipe(Recipe recipe) {
        Recipe.delete(recipe);
    }
}
