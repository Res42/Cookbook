package hu.bme.r0uj46.cookbook.repository.recipes;

import android.content.Context;
import android.util.LongSparseArray;
import android.util.SparseArray;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import hu.bme.r0uj46.cookbook.model.Recipe;

public class RecipeMemoryRepository implements RecipeRepository {
    private static LongSparseArray<Recipe> recipes;

    @Override
    public void open(Context context) {
        recipes = new LongSparseArray<>();
    }

    @Override
    public void close() {
    }

    @Override
    public List<Recipe> getRecipes() {
        return asList(recipes);
    }

    @Override
    public Recipe getRecipe(Long id) {
        return recipes.get(id, null);
    }

    @Override
    public void saveRecipe(Recipe recipe) {
        if (recipe.getId() == null) {
            recipe.setId(getNewId());
        }

        recipes.put(recipe.getId(), recipe);
    }

    @Override
    public void removeRecipe(Recipe recipe) {
        if (recipe.getId() != null)
            recipes.remove(recipe.getId());
    }

    private static <C> List<C> asList(LongSparseArray<C> sparseArray) {
        if (sparseArray == null) return null;
        List<C> arrayList = new ArrayList<C>(sparseArray.size());
        for (int i = 0; i < sparseArray.size(); i++)
            arrayList.add(sparseArray.valueAt(i));
        return arrayList;
    }

    private Long getNewId() {
        if (recipes.size() == 0) {
            return 1L;
        }

        return recipes.keyAt(recipes.size() - 1) + 1L;
    }
}
