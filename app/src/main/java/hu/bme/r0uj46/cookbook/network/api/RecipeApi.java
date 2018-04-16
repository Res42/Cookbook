package hu.bme.r0uj46.cookbook.network.api;

import java.util.List;

import hu.bme.r0uj46.cookbook.network.dto.RecipeDto;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface RecipeApi {
  
  /**
   * List all recipes
   * 
   * @return Call<List<Recipe>>
   */
  
  @GET("recipe")
  Call<List<RecipeDto>> listRecipes();
    

  
  /**
   * Add a new recipe to the store
   * 
   * @param body Recipe object that needs to be added to the store
   * @return Call<Void>
   */
  
  @POST("recipe")
  Call<Void> addRecipe(
    @Body RecipeDto body
  );

  
  /**
   * Find recipe by ID
   * Returns a single recipe
   * @param recipeId ID of recipe to return
   * @return Call<Recipe>
   */
  
  @GET("recipe/{recipeId}")
  Call<RecipeDto> getRecipeById(
    @Path("recipeId") Long recipeId
  );

  
  /**
   * Updates a recipe in the store with form data
   * 
   * @param recipeId ID of recipe to return
   * @param body Recipe object that needs to be updated in the store
   * @return Call<Void>
   */
  
  @POST("recipe/{recipeId}")
  Call<Void> updateRecipe(
    @Path("recipeId") Long recipeId, @Body RecipeDto body
  );

  
  /**
   * Deletes a recipe
   * 
   * @param recipeId Recipe id to delete
   * @return Call<Void>
   */
  
  @DELETE("recipe/{recipeId}")
  Call<Void> deleteRecipe(
    @Path("recipeId") Long recipeId
  );

  
}
