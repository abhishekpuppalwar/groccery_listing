package com.example.helloandroid;

import android.app.Application;
import android.content.Context;

import com.example.helloandroid.dagger.component.ApplicationComponent;
import com.example.helloandroid.dagger.component.DaggerApplicationComponent;
import com.example.helloandroid.dagger.module.AppModule;
import com.facebook.stetho.Stetho;

import androidx.multidex.MultiDex;

public class ShoppingList extends Application {
    private static ApplicationComponent component;
    private static ShoppingList application;

    public ShoppingList() {
        application = this;
    }

    public static Context getAppContext() {
        return application.getApplicationContext();
    }

    public static ApplicationComponent getApplicationComponent() {
        return component;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        application = this;
        component = DaggerApplicationComponent.builder()
                .appModule(new AppModule(this))
                .build();
        configStetho();
    }

    private void configStetho() {
        if (BuildConfig.DEBUG) {
            Stetho.initialize(Stetho.newInitializerBuilder(this)
                    .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
                    .enableWebKitInspector(Stetho.defaultInspectorModulesProvider(this))
                    .build());
        }
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }
}
