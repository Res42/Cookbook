package hu.bme.r0uj46.cookbook.ui;

public class Presenter<ScreenType> {
    protected ScreenType screen;

    public void attach(ScreenType screen) {
        this.screen = screen;
    }

    public void detach() {
        this.screen = null;
    }
}
