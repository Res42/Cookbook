package hu.bme.r0uj46.cookbook.interactor.recipes.events;

import hu.bme.r0uj46.cookbook.model.Recipe;

public class RemoveRecipeEvent {
    private int code;
    private Recipe recipe;
    private Throwable throwable;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    public Throwable getThrowable() {
        return throwable;
    }

    public void setThrowable(Throwable throwable) {
        this.throwable = throwable;
    }
}
