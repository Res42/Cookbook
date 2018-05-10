package hu.bme.r0uj46.cookbook.mock.interceptors;

import android.net.Uri;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import hu.bme.r0uj46.cookbook.model.Recipe;
import hu.bme.r0uj46.cookbook.network.NetworkConfig;
import hu.bme.r0uj46.cookbook.network.dto.RecipeDto;
import hu.bme.r0uj46.cookbook.network.utils.GsonHelper;
import hu.bme.r0uj46.cookbook.repository.recipes.RecipeMemoryRepository;
import hu.bme.r0uj46.cookbook.repository.recipes.RecipesSugarOrmRepository;
import okhttp3.Headers;
import okhttp3.Request;
import okhttp3.Response;

import static hu.bme.r0uj46.cookbook.mock.interceptors.MockHelper.bodyToString;
import static hu.bme.r0uj46.cookbook.mock.interceptors.MockHelper.makeResponse;

public class RecipeMock {
    private static Pattern p = Pattern.compile(NetworkConfig.ENDPOINT_PREFIX + "recipe/?(\\d+)?");
    private static RecipeMemoryRepository repository = new RecipeMemoryRepository();

    static {
        repository.open(null);
    }

    public static Response process(Request request) {
        Uri uri = Uri.parse(request.url().toString());

        String responseString;
        int responseCode;
        Headers headers = request.headers();

        Matcher m = p.matcher(uri.getPath());

        if (m.matches()) {
            Long recipeId = m.group(1) != null ? Long.parseLong(m.group(1)) : null;

            if (request.method().toLowerCase().equals("get") && recipeId == null) {

                List<RecipeDto> recipes = new ArrayList<>();

                for (Recipe r : repository.getRecipes()) {
                    recipes.add(r.toDto());
                }

                responseString = GsonHelper.getGson().toJson(recipes);
                responseCode = 200;
            } else if (request.method().toLowerCase().equals("get") && recipeId != null) {
                responseString = GsonHelper.getGson().toJson(repository.getRecipe(recipeId).toDto());
                responseCode = 200;
            } else if (request.method().toLowerCase().equals("post")) {
                Recipe recipe = Recipe.fromDto(GsonHelper.getGson().fromJson(bodyToString(request), RecipeDto.class));
                repository.saveRecipe(recipe);
                responseString = "";
                responseCode = 200;
            } else if (request.method().toLowerCase().equals("delete")) {
                repository.removeRecipe(repository.getRecipe(recipeId));
                responseString = "";
                responseCode = 200;
            } else {
                responseString = "ERROR";
                responseCode = 503;
            }
        } else {
            responseString = "ERROR";
            responseCode = 503;
        }

        return makeResponse(request, headers, responseCode, responseString);
    }
}
