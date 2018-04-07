package hu.bme.r0uj46.cookbook.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import hu.bme.r0uj46.cookbook.R;
import hu.bme.r0uj46.cookbook.CookbookApplication;
import hu.bme.r0uj46.cookbook.model.Recipe;
import hu.bme.r0uj46.cookbook.ui.RecyclerItemClickListener;
import hu.bme.r0uj46.cookbook.ui.details.DetailsActivity;

public class MainActivity extends AppCompatActivity implements MainScreen {
    public static final String KEY_RECIPE = "KEY_RECIPE";

    private RecyclerView recyclerViewRecipes;
    private SwipeRefreshLayout swipeRefreshLayoutRecipes;
    private TextView tvNoRecipes;

    private List<Recipe> recipesList;
    private RecipesAdapter recipesAdapter;

    @Inject
    MainPresenter mainPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        CookbookApplication.injector.inject(this);

        Toolbar toolbar = findViewById(R.id.main_toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.main_add_fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainPresenter.newRecipe();
            }
        });

        tvNoRecipes = findViewById(R.id.tvNoRecipes);
        recyclerViewRecipes = findViewById(R.id.recyclerViewRecipes);
        LinearLayoutManager llm = new LinearLayoutManager(MainActivity.this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerViewRecipes.setLayoutManager(llm);

        recipesList = new ArrayList<>();
        recipesAdapter = new RecipesAdapter(MainActivity.this, recipesList);
        recyclerViewRecipes.setAdapter(recipesAdapter);

        recyclerViewRecipes.addOnItemTouchListener(
                new RecyclerItemClickListener(MainActivity.this, new RecyclerItemClickListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {
                        mainPresenter.recipeDetails(recipesAdapter.getItem(position));
                    }
                })
        );

        swipeRefreshLayoutRecipes = findViewById(R.id.swipeRefreshLayoutRecipes);

        swipeRefreshLayoutRecipes.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mainPresenter.refreshRecipes();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        mainPresenter.attach(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        mainPresenter.refreshRecipes();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mainPresenter.detach();
    }

    @Override
    public void listRecipes(List<Recipe> recipes) {
        if (swipeRefreshLayoutRecipes != null) {
            swipeRefreshLayoutRecipes.setRefreshing(false);
        }

        recipesList.clear();
        recipesList.addAll(recipes);
        recipesAdapter.notifyDataSetChanged();

        if (recipesList.isEmpty()) {
            recyclerViewRecipes.setVisibility(View.GONE);
            tvNoRecipes.setVisibility(View.VISIBLE);
        } else {
            tvNoRecipes.setVisibility(View.GONE);
            recyclerViewRecipes.setVisibility(View.VISIBLE);
        }
    }
    @Override
    public void showRecipeDetails(int recipeId) {
        Intent intent = new Intent(MainActivity.this, DetailsActivity.class);
        intent.putExtra(KEY_RECIPE, recipeId);
        startActivity(intent);
    }

    @Override
    public void showNewRecipe() {
        startActivity(new Intent(MainActivity.this, DetailsActivity.class));
    }
}
