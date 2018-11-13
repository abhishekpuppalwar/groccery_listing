package com.example.helloandroid.dagger.component;

import com.example.helloandroid.dagger.module.AppModule;
import com.example.helloandroid.dagger.module.NetworkModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AppModule.class, NetworkModule.class})
public interface ApplicationComponent {
}
