package hu.bme.r0uj46.cookbook;

import com.crashlytics.android.Crashlytics;
import com.orm.SugarApp;

import hu.bme.r0uj46.cookbook.ui.UIModule;
import io.fabric.sdk.android.Fabric;

public class CookbookApplication extends SugarApp {
    public static CookbookApplicationComponent injector;

    @Override
    public void onCreate() {
        super.onCreate();

        injector = DaggerCookbookApplicationComponent.builder()
                .uIModule(new UIModule(this))
                .build();

        Fabric.with(this, new Crashlytics());
    }
}