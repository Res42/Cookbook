package hu.bme.r0uj46.cookbook.utils;

import org.robolectric.RuntimeEnvironment;
import org.robolectric.shadows.ShadowLog;

import hu.bme.r0uj46.cookbook.CookbookApplication;
import hu.bme.r0uj46.cookbook.DaggerTestComponent;
import hu.bme.r0uj46.cookbook.TestModule;

public class TestHelper {

    public static void setTestInjector() {
        ShadowLog.stream = System.out;
        CookbookApplication application = (CookbookApplication) RuntimeEnvironment.application;
        application.injector = DaggerTestComponent.builder().testModule(new TestModule(application.getApplicationContext())).build();
    }
}