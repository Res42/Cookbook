package hu.bme.r0uj46.cookbook.ui.details;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.inject.Inject;

import hu.bme.r0uj46.cookbook.CookbookApplication;
import hu.bme.r0uj46.cookbook.R;
import hu.bme.r0uj46.cookbook.model.Recipe;
import hu.bme.r0uj46.cookbook.ui.main.MainActivity;
import hu.bme.r0uj46.cookbook.utils.AndroidBug5497Workaround;

public class DetailsActivity extends AppCompatActivity implements DetailsScreen {
    private static final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 42;

    @Inject
    DetailsPresenter detailsPresenter;

    private CoordinatorLayout coordinatorLayout;
    private CollapsingToolbarLayout toolbarLayout;
    private ImageView image;
    private TextInputEditText etName;
    private TextInputEditText etPrepTime;
    private TextInputEditText etIngredients;
    private TextInputEditText etPreparation;

    private Recipe recipe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        AndroidBug5497Workaround.assistActivity(this);

        CookbookApplication.injector.inject(this);

        coordinatorLayout = findViewById(R.id.details_layout);

        Toolbar toolbar = findViewById(R.id.details_toolbar);
        setSupportActionBar(toolbar);

        toolbarLayout = findViewById(R.id.details_toolbar_layout);
        image = findViewById(R.id.details_backdrop);
        etName = findViewById(R.id.details_name);
        etPrepTime = findViewById(R.id.details_preparation_time);
        etIngredients = findViewById(R.id.details_ingredients);
        etPreparation = findViewById(R.id.details_preparation);

        FloatingActionButton fab = findViewById(R.id.details_photo_fab);
        if (isIntentAvailable(this, MediaStore.ACTION_IMAGE_CAPTURE)) {
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dispatchTakePictureIntent(CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE);
                }
            });
        } else {
            fab.setVisibility(View.INVISIBLE);
        }


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
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE: {
                if (resultCode == RESULT_OK) {
                    loadImage();
                } else {
                    recipe.setPictureUri(null);
                }
                break;
            } // CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE
        }
    }

    @Override
    public void displayRecipe(Recipe recipe) {
        this.recipe = recipe;
        toolbarLayout.setTitle(recipe.getName());
        etName.setText(recipe.getName());
        etPrepTime.setText(recipe.getPreparationTime());
        etIngredients.setText(recipe.getIngredients());
        etPreparation.setText(recipe.getHowToMake());

        loadImage();
    }

    @Override
    public void backToPreviousActivity() {
        finish();
    }

    @Override
    public void showMessage(String message) {
        Snackbar.make(coordinatorLayout, message, Snackbar.LENGTH_LONG).setAction(R.string.snackbar_ok, null).show();
    }

    @Override
    public void showMessage(int resourceId) {
        Snackbar.make(coordinatorLayout, resourceId, Snackbar.LENGTH_LONG).setAction(R.string.snackbar_ok, null).show();
    }

    private void loadRecipe() {
        if (recipe != null) {
            return;
        }

        if (getIntent().hasExtra(MainActivity.KEY_RECIPE)) {
            Long recipeId = getIntent().getLongExtra(MainActivity.KEY_RECIPE, -1L);
            detailsPresenter.loadRecipe(recipeId);
        } else {
            detailsPresenter.loadNewRecipe();
        }
    }

    private void loadImage() {
        if (recipe.getPictureUri() != null) {
            try {
                Bitmap bp = MediaStore.Images.Media.getBitmap(getContentResolver(), Uri.parse(recipe.getPictureUri()));
                image.setImageBitmap(bp);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void dispatchTakePictureIntent(int actionCode) {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        Uri imageUri = getPictureUri();
        recipe.setPictureUri(imageUri.toString());
        takePictureIntent.putExtra(android.provider.MediaStore.EXTRA_OUTPUT, imageUri);

        startActivityForResult(takePictureIntent, actionCode);
    }

    private Uri getPictureUri() {
        File newFile = new File(getFilesDir(), "food_picture_" + String.valueOf(System.currentTimeMillis()) + ".jpg");
        return FileProvider.getUriForFile(this, getApplicationContext().getPackageName() + ".fileprovider", newFile);
    }

    public static boolean isIntentAvailable(Context context, String action) {
        final PackageManager packageManager = context.getPackageManager();
        final Intent intent = new Intent(action);
        List<ResolveInfo> list = packageManager.queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY);
        return list.size() > 0;
    }
}
