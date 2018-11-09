package com.fastie4.etsyclient.presentation.di.component;

import com.fastie4.etsyclient.presentation.App;
import com.fastie4.etsyclient.presentation.di.module.AndroidBindingModule;
import com.fastie4.etsyclient.presentation.di.module.AppModule;
import com.fastie4.etsyclient.presentation.di.module.RetrofitModule;
import com.fastie4.etsyclient.presentation.di.module.RoomModule;

import javax.inject.Singleton;

import dagger.Component;
import dagger.android.AndroidInjection;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;

@SuppressWarnings("WeakerAccess")
@Singleton
@Component(modules = {AndroidSupportInjectionModule.class, AndroidBindingModule.class,
        AppModule.class, RetrofitModule.class, RoomModule.class})
public interface AppComponent extends AndroidInjector<App> {
    @Component.Builder
    abstract class Builder extends AndroidInjector.Builder<App> {
    }
}