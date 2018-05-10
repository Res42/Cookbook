package hu.bme.r0uj46.cookbook;

import javax.inject.Singleton;

import dagger.Component;
import hu.bme.r0uj46.cookbook.interactor.InteractorModule;
import hu.bme.r0uj46.cookbook.mock.MockNetworkModule;
import hu.bme.r0uj46.cookbook.repository.RepositoryModule;
import hu.bme.r0uj46.cookbook.ui.UIModule;

@Singleton
@Component(modules = {InteractorModule.class, MockNetworkModule.class, TestModule.class})
public interface TestComponent extends CookbookApplicationComponent {
}
