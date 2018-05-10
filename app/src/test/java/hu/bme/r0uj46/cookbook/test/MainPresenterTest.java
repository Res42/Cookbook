package hu.bme.r0uj46.cookbook.test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import java.util.List;

import hu.bme.r0uj46.cookbook.BuildConfig;
import hu.bme.r0uj46.cookbook.model.Recipe;
import hu.bme.r0uj46.cookbook.ui.main.MainPresenter;
import hu.bme.r0uj46.cookbook.ui.main.MainScreen;

import static hu.bme.r0uj46.cookbook.utils.TestHelper.setTestInjector;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 23)
public class MainPresenterTest {
    private MainPresenter presenter;
    private MainScreen screen;

    @Before
    public void setup() {
        setTestInjector();
        screen = mock(MainScreen.class);
        presenter = new MainPresenter();
        presenter.attach(screen);
    }
    @After
    public void tearDown() {
        presenter.detach();
    }

    @Test
    public void testDetails() {
        Recipe recipe = new Recipe();

        presenter.recipeDetails(recipe);
        verify(screen, times(1)).showRecipeDetails(recipe);
    }

    @Test
    public void testNewDetails() {
        presenter.newRecipe();
        verify(screen, times(1)).showNewRecipe();
    }

    @Test
    public void testRefresh() {
        ArgumentCaptor<List> argumentCaptor = ArgumentCaptor.forClass(List.class);

        presenter.refreshRecipes();
        verify(screen, times(1)).listRecipes(argumentCaptor.capture());
    }
}
