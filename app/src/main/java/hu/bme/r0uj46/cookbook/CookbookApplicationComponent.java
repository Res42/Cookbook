package hu.bme.r0uj46.cookbook;

import javax.inject.Singleton;

import dagger.Component;
import hu.bme.r0uj46.cookbook.ui.UIModule;
import hu.bme.r0uj46.cookbook.ui.details.DetailsActivity;
import hu.bme.r0uj46.cookbook.ui.main.MainActivity;

@Singleton
@Component(modules = {UIModule.class/*, NetworkModule.class, InteractorModule.class*/})
public interface CookbookApplicationComponent {
    void inject(MainActivity mainActivity);
    void inject(DetailsActivity detailsActivity);
}
