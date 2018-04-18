package hu.bme.r0uj46.cookbook.interactor.recipes.events;

import java.util.List;

import hu.bme.r0uj46.cookbook.model.Recipe;

public class GetRecipesEvent {
    private int code;
    private List<Recipe> recipes;
    private Throwable throwable;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public List<Recipe> getRecipes() {
        return recipes;
    }

    public void setRecipes(List<Recipe> recipes) {
        this.recipes = recipes;
    }

    public Throwable getThrowable() {
        return throwable;
    }

    public void setThrowable(Throwable throwable) {
        this.throwable = throwable;
    }
}
