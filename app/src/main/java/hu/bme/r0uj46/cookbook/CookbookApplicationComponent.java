package hu.bme.r0uj46.cookbook;

import javax.inject.Singleton;

import dagger.Component;
import hu.bme.r0uj46.cookbook.interactor.InteractorModule;
import hu.bme.r0uj46.cookbook.ui.UIModule;
import hu.bme.r0uj46.cookbook.ui.details.DetailsActivity;
import hu.bme.r0uj46.cookbook.ui.details.DetailsPresenter;
import hu.bme.r0uj46.cookbook.ui.main.MainActivity;
import hu.bme.r0uj46.cookbook.ui.main.MainPresenter;

@Singleton
@Component(modules = {UIModule.class, InteractorModule.class})
public interface CookbookApplicationComponent {
    void inject(MainActivity mainActivity);
    void inject(DetailsActivity detailsActivity);

    void inject(MainPresenter mainPresenter);
    void inject(DetailsPresenter detailsPresenter);
}
