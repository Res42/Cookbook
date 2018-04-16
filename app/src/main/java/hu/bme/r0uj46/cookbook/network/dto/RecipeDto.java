package hu.bme.r0uj46.cookbook.network.dto;

import java.util.Arrays;
import java.util.Objects;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import com.google.gson.annotations.SerializedName;

@ApiModel(description = "")
public class RecipeDto   {
  
  @SerializedName("id")
  private Long id = null;
  
  @SerializedName("name")
  private String name = null;
  
  @SerializedName("preparationTime")
  private String preparationTime = null;
  
  @SerializedName("ingredients")
  private String ingredients = null;
  
  @SerializedName("howToMake")
  private String howToMake = null;
  
  @SerializedName("picture")
  private byte[] picture = null;
  

  
  /**
   **/
  @ApiModelProperty(value = "")
  public Long getId() {
    return id;
  }
  public void setId(Long id) {
    this.id = id;
  }

  
  /**
   **/
  @ApiModelProperty(required = true, value = "")
  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }

  
  /**
   **/
  @ApiModelProperty(required = true, value = "")
  public String getPreparationTime() {
    return preparationTime;
  }
  public void setPreparationTime(String preparationTime) {
    this.preparationTime = preparationTime;
  }

  
  /**
   **/
  @ApiModelProperty(required = true, value = "")
  public String getIngredients() {
    return ingredients;
  }
  public void setIngredients(String ingredients) {
    this.ingredients = ingredients;
  }

  
  /**
   **/
  @ApiModelProperty(required = true, value = "")
  public String getHowToMake() {
    return howToMake;
  }
  public void setHowToMake(String howToMake) {
    this.howToMake = howToMake;
  }

  
  /**
   **/
  @ApiModelProperty(value = "")
  public byte[] getPicture() {
    return picture;
  }
  public void setPicture(byte[] picture) {
    this.picture = picture;
  }

  

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    RecipeDto recipe = (RecipeDto) o;
    return Objects.equals(id, recipe.id) &&
        Objects.equals(name, recipe.name) &&
        Objects.equals(preparationTime, recipe.preparationTime) &&
        Objects.equals(ingredients, recipe.ingredients) &&
        Objects.equals(howToMake, recipe.howToMake) &&
        Arrays.equals(picture, recipe.picture);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, preparationTime, ingredients, howToMake, picture);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Recipe {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    preparationTime: ").append(toIndentedString(preparationTime)).append("\n");
    sb.append("    ingredients: ").append(toIndentedString(ingredients)).append("\n");
    sb.append("    howToMake: ").append(toIndentedString(howToMake)).append("\n");
    sb.append("    picture: ").append(toIndentedString(picture)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}
