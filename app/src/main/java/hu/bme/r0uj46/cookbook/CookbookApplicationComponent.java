package hu.bme.r0uj46.cookbook;

import javax.inject.Singleton;

import dagger.Component;
import hu.bme.r0uj46.cookbook.interactor.InteractorModule;
import hu.bme.r0uj46.cookbook.interactor.recipes.RecipesInteractor;
import hu.bme.r0uj46.cookbook.mock.MockNetworkModule;
import hu.bme.r0uj46.cookbook.network.NetworkModule;
import hu.bme.r0uj46.cookbook.repository.RepositoryModule;
import hu.bme.r0uj46.cookbook.ui.UIModule;
import hu.bme.r0uj46.cookbook.ui.details.DetailsActivity;
import hu.bme.r0uj46.cookbook.ui.details.DetailsPresenter;
import hu.bme.r0uj46.cookbook.ui.main.MainActivity;
import hu.bme.r0uj46.cookbook.ui.main.MainPresenter;

@Singleton
@Component(modules = {UIModule.class, InteractorModule.class, MockNetworkModule.class, RepositoryModule.class})
public interface CookbookApplicationComponent {
    void inject(MainActivity mainActivity);
    void inject(DetailsActivity detailsActivity);

    void inject(MainPresenter mainPresenter);
    void inject(DetailsPresenter detailsPresenter);

    void inject(RecipesInteractor recipesInteractor);
}
