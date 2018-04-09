package hu.bme.r0uj46.cookbook.interactor;

import dagger.Module;
import dagger.Provides;
import hu.bme.r0uj46.cookbook.interactor.recipes.RecipesInteractor;

@Module
public class InteractorModule {
    @Provides
    public RecipesInteractor provideRecipesInteractor() {
        return new RecipesInteractor();
    }
}
