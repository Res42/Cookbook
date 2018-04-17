package hu.bme.r0uj46.cookbook.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Recipe implements Parcelable {
    private Long id;
    private String name;
    private String preparationTime;
    private String ingredients;
    private String howToMake;
    private String pictureUri;

    public Recipe() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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


    //region Parcel
    public Recipe(Parcel in) {
        setId(in.readLong());
        setName(in.readString());
        setPreparationTime(in.readString());
        setIngredients(in.readString());
        setHowToMake(in.readString());
        setPictureUri(in.readString());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(getId());
        dest.writeString(getName());
        dest.writeString(getPreparationTime());
        dest.writeString(getIngredients());
        dest.writeString(getHowToMake());
        dest.writeString(getPictureUri());
    }

    public static final Creator<Recipe> CREATOR = new Creator<Recipe>() {
        @Override
        public Recipe createFromParcel(Parcel in) {
            return new Recipe(in);
        }

        @Override
        public Recipe[] newArray(int size) {
            return new Recipe[size];
        }
    };
    //endregion
}
