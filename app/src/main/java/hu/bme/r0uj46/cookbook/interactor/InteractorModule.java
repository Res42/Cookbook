package hu.bme.r0uj46.cookbook.interactor;

import org.greenrobot.eventbus.EventBus;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import hu.bme.r0uj46.cookbook.interactor.recipes.RecipesInteractor;

@Module
public class InteractorModule {
    @Provides
    public RecipesInteractor provideRecipesInteractor() {
        return new RecipesInteractor();
    }

    @Provides
    @Singleton
    public EventBus provideEventBus() {
        return EventBus.getDefault();
    }
}
