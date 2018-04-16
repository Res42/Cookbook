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
        return SugarRecord.listAll(Recipe.class);
    }

    @Override
    public void saveRecipe(Recipe recipe) {
        SugarRecord.saveInTx(recipe);
    }

    @Override
    public void updateRecipes(List<Recipe> recipes) {
        List<Recipe> myRecipes = getRecipes();
        List<Recipe> toUpdate = new ArrayList<>(myRecipes.size());
        for (Recipe myRecipe : myRecipes) {
            for (Recipe recipe : recipes) {
                if (myRecipe.getId().equals(recipe.getId())) {
                    toUpdate.add(recipe);
                }
            }
        }
        SugarRecord.saveInTx(toUpdate);
    }

    @Override
    public void removeRecipe(Recipe recipe) {
        SugarRecord.deleteInTx(recipe);
    }

    @Override
    public boolean isInDB(Recipe recipe) {
        return SugarRecord.findById(Recipe.class, recipe.getId()) != null;
    }
}