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
import hu.bme.r0uj46.cookbook.ui.details.DetailsPresenter;
import hu.bme.r0uj46.cookbook.ui.details.DetailsScreen;

import static hu.bme.r0uj46.cookbook.utils.TestHelper.setTestInjector;
import static junit.framework.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21)
public class DetailsPresenterTest {
    private DetailsPresenter presenter;
    private DetailsScreen screen;

    @Before
    public void setup() {
        setTestInjector();
        screen = mock(DetailsScreen.class);
        presenter = new DetailsPresenter();
        presenter.attach(screen);
    }
    @After
    public void tearDown() {
        presenter.detach();
    }

    @Test
    public void testDetails() {
        ArgumentCaptor<Recipe> argumentCaptor = ArgumentCaptor.forClass(Recipe.class);

        presenter.loadNewRecipe();
        verify(screen, times(1)).displayRecipe(argumentCaptor.capture());

        assertTrue(argumentCaptor.getValue().getId() == null);
    }

    @Test
    public void testSaveNewRecipe() {
        presenter.saveRecipe(new Recipe());
        verify(screen, times(1)).backToPreviousActivity();
    }

    @Test
    public void testSaveExistingRecipe() {
        Recipe recipe = new Recipe();
        recipe.setId(10L);

        presenter.saveRecipe(recipe);
        verify(screen, times(1)).backToPreviousActivity();
    }

    @Test
    public void testDeleteRecipe() {
        presenter.deleteRecipe(new Recipe());
        verify(screen, times(1)).backToPreviousActivity();
    }

    @Test
    public void testLoadRecipe() {
        Recipe recipe = new Recipe();
        recipe.setId(42L);

        presenter.saveRecipe(recipe);
        presenter.loadRecipe(42L);
        verify(screen, times(1)).backToPreviousActivity();
    }
}
