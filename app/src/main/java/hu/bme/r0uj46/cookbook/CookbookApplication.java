package hu.bme.r0uj46.cookbook;

import android.app.Application;

import hu.bme.r0uj46.cookbook.ui.UIModule;

public class CookbookApplication extends Application {
    public static CookbookApplicationComponent injector;

    @Override
    public void onCreate() {
        super.onCreate();

        injector = DaggerCookbookApplicationComponent.builder()
                .uIModule(new UIModule(this))
                .build();
    }
}