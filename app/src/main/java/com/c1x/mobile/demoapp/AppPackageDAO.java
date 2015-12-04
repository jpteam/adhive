package com.c1x.mobile.demoapp;

import android.graphics.drawable.Drawable;

/**
 * Created by arindamnath on 21/06/15.
 */
public class AppPackageDAO {

    private String appName;
    private Drawable icon;
    private String packageName;

    public AppPackageDAO(String appName, Drawable icon, String packageName) {
        this.appName = appName;
        this.icon = icon;
        this.packageName = packageName;
    }

    public String getAppName() {
        return appName;
    }

    public String getPackageName() {
        return packageName;
    }

    public Drawable getIcon() {
        return icon;
    }
}
