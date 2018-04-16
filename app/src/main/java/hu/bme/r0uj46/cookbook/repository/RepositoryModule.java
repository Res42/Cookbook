package hu.bme.r0uj46.cookbook.repository;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import hu.bme.r0uj46.cookbook.repository.recipes.RecipeRepository;
import hu.bme.r0uj46.cookbook.repository.recipes.RecipesSugarOrmRepository;

@Module
public class RepositoryModule {

    @Singleton
    @Provides
    public RecipeRepository provideRepository() {
        return new RecipesSugarOrmRepository();
    }
}
