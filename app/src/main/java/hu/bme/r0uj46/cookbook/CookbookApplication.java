package hu.bme.r0uj46.cookbook;

import com.crashlytics.android.Crashlytics;
import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.Tracker;
import com.orm.SugarApp;

import hu.bme.r0uj46.cookbook.ui.UIModule;
import io.fabric.sdk.android.Fabric;

public class CookbookApplication extends SugarApp {
    public static CookbookApplicationComponent injector;

    private static GoogleAnalytics sAnalytics;
    private static Tracker sTracker;

    @Override
    public void onCreate() {
        super.onCreate();

        injector = DaggerCookbookApplicationComponent.builder()
                .uIModule(new UIModule(this))
                .build();

        sAnalytics = GoogleAnalytics.getInstance(this);
        Fabric.with(this, new Crashlytics());
    }

    /**
     * Gets the default {@link Tracker} for this Application.
     * @return tracker
     */
    synchronized public Tracker getDefaultTracker() {
        // To enable debug logging use: adb shell setprop log.tag.GAv4 DEBUG
        if (sTracker == null) {
            sTracker = sAnalytics.newTracker(R.xml.global_tracker);
        }

        return sTracker;
    }
}