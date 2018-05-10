package hu.bme.r0uj46.cookbook;

import android.content.Context;

import java.util.concurrent.Executor;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import hu.bme.r0uj46.cookbook.repository.recipes.RecipeMemoryRepository;
import hu.bme.r0uj46.cookbook.repository.recipes.RecipeRepository;
import hu.bme.r0uj46.cookbook.ui.details.DetailsPresenter;
import hu.bme.r0uj46.cookbook.ui.main.MainPresenter;
import hu.bme.r0uj46.cookbook.utils.UiExecutor;

@Module
public class TestModule {
    private Context context;

    public TestModule(Context context) {
        this.context = context;
    }

    @Provides
    public Context provideContext() {
        return context;
    }

    @Provides
    @Singleton
    public Executor provideNetworkExecutor() {
        return new UiExecutor();
    }

    @Singleton
    @Provides
    public RecipeRepository provideRepository() {
        return new RecipeMemoryRepository();
    }

    @Provides
    @Singleton
    public MainPresenter provideMainPresenter() {
        return new MainPresenter();
    }

    @Provides
    @Singleton
    public DetailsPresenter provideDetailsPresenter() {
        return new DetailsPresenter();
    }
}