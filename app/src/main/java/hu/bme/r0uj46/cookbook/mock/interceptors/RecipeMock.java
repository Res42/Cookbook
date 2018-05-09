package hu.bme.r0uj46.cookbook.mock.interceptors;

import android.net.Uri;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import hu.bme.r0uj46.cookbook.model.Recipe;
import hu.bme.r0uj46.cookbook.network.NetworkConfig;
import hu.bme.r0uj46.cookbook.network.utils.GsonHelper;
import hu.bme.r0uj46.cookbook.repository.recipes.RecipesSugarOrmRepository;
import okhttp3.Headers;
import okhttp3.Request;
import okhttp3.Response;

import static hu.bme.r0uj46.cookbook.mock.interceptors.MockHelper.bodyToString;
import static hu.bme.r0uj46.cookbook.mock.interceptors.MockHelper.makeResponse;

public class RecipeMock {
    private static Pattern p = Pattern.compile(NetworkConfig.ENDPOINT_PREFIX + "recipe/?(\\d+)?");

    public static Response process(Request request) {
        Uri uri = Uri.parse(request.url().toString());

        String responseString;
        int responseCode;
        Headers headers = request.headers();

        RecipesSugarOrmRepository repository = new RecipesSugarOrmRepository();
        repository.open(null);

        Matcher m = p.matcher(uri.getPath());

        if (m.matches()) {
            Long recipeId = m.groupCount() == 2 ? Long.parseLong(m.group(1)) : null;

            if (request.method().toLowerCase().equals("get") && recipeId == null) {
                responseString = GsonHelper.getGson().toJson(repository.getRecipes());
                responseCode = 200;
            } else if (request.method().toLowerCase().equals("get") && recipeId != null) {
                responseString = GsonHelper.getGson().toJson(repository.getRecipe(recipeId));
                responseCode = 200;
            } else if (request.method().toLowerCase().equals("post")) {
                repository.saveRecipe(GsonHelper.getGson().fromJson(bodyToString(request), Recipe.class));
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
