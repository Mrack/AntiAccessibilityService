package cn.mrack.myapplication;

import android.app.Application;

public class App extends Application {
    private static final String TAG = "App";
    public static AntiAccessibilityService antiAccessibilityService;

    @Override
    public void onCreate() {
        super.onCreate();
        antiAccessibilityService = new AntiAccessibilityService(this);
        antiAccessibilityService.anti();
    }

}
