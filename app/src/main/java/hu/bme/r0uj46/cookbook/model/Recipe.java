package hu.bme.r0uj46.cookbook.model;

import com.orm.SugarRecord;

public class Recipe extends SugarRecord {
    private String name;
    private String preparationTime;
    private String ingredients;
    private String howToMake;
    private String pictureUri;

    public Recipe() {
    }

    public Recipe(String name, String preparationTime, String ingredients, String howToMake, String pictureUri) {
        this.name = name;
        this.preparationTime = preparationTime;
        this.ingredients = ingredients;
        this.howToMake = howToMake;
        this.pictureUri = pictureUri;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPreparationTime() {
        return preparationTime;
    }

    public void setPreparationTime(String preparationTime) {
        this.preparationTime = preparationTime;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public String getHowToMake() {
        return howToMake;
    }

    public void setHowToMake(String howToMake) {
        this.howToMake = howToMake;
    }

    public String getPictureUri() {
        return pictureUri;
    }

    public void setPictureUri(String pictureUri) {
        this.pictureUri = pictureUri;
    }
}
