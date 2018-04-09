package hu.bme.r0uj46.cookbook.ui.details;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import javax.inject.Inject;

import hu.bme.r0uj46.cookbook.CookbookApplication;
import hu.bme.r0uj46.cookbook.R;
import hu.bme.r0uj46.cookbook.model.Recipe;
import hu.bme.r0uj46.cookbook.ui.main.MainActivity;

public class DetailsActivity extends AppCompatActivity implements DetailsScreen {
    @Inject
    DetailsPresenter detailsPresenter;

    private CollapsingToolbarLayout toolbarLayout;
    private ImageView image;
    private TextInputEditText etName;
    private TextInputEditText etPrepTime;
    private TextInputEditText etIngredients;
    private TextInputEditText etPreparation;
    private FloatingActionButton fab;

    private Recipe recipe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        CookbookApplication.injector.inject(this);

        Toolbar toolbar = findViewById(R.id.details_toolbar);
        setSupportActionBar(toolbar);

        toolbarLayout = findViewById(R.id.details_toolbar_layout);
        image = findViewById(R.id.details_backdrop);
        etName = findViewById(R.id.details_name);
        etPrepTime = findViewById(R.id.details_preparation_time);
        etIngredients = findViewById(R.id.details_ingredients);
        etPreparation = findViewById(R.id.details_preparation);

        fab = findViewById(R.id.details_photo_fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO picture
            }
        });

        ImageButton btBack = findViewById(R.id.details_button_back);
        btBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backToPreviousActivity();
            }
        });

        ImageButton btSave = findViewById(R.id.details_button_save);
        btSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recipe.setName(etName.getText().toString());
                recipe.setPreparationTime(etPrepTime.getText().toString());
                recipe.setIngredients(etIngredients.getText().toString());
                recipe.setHowToMake(etPreparation.getText().toString());

                detailsPresenter.saveRecipe(recipe);
            }
        });

        ImageButton btDelete = findViewById(R.id.details_button_delete);
        btDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                detailsPresenter.deleteRecipe(recipe);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        detailsPresenter.attach(this);
        loadRecipe();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onStop() {
        super.onStop();
        detailsPresenter.detach();
    }

    @Override
    public void displayRecipe(Recipe recipe) {
        this.recipe = recipe;
        toolbarLayout.setTitle(recipe.getName());
        etName.setText(recipe.getName());
        etPrepTime.setText(recipe.getPreparationTime());
        etIngredients.setText(recipe.getIngredients());
        etPreparation.setText(recipe.getHowToMake());
    }

    @Override
    public void backToPreviousActivity() {
        finish();
    }

    private void loadRecipe() {
        if (getIntent().hasExtra(MainActivity.KEY_RECIPE)) {
            int recipeId = getIntent().getIntExtra(MainActivity.KEY_RECIPE, -1);
            detailsPresenter.loadRecipe(recipeId);
        } else {
            detailsPresenter.loadNewRecipe();
        }
    }
}
