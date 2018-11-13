package com.example.helloandroid.dagger.module;

import android.app.Application;
import android.content.Context;

import com.example.helloandroid.presenter.ProductListPresenter;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {
    private Application app;

    public AppModule(Application app) {
        this.app = app;
    }

    @Provides
    @Singleton
    Context provideApplicationContext() {
        return app.getApplicationContext();
    }
}
