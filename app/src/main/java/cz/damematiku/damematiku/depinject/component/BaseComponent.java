package cz.damematiku.damematiku.depinject.component;

import android.content.res.Resources;

import cz.damematiku.damematiku.data.MathService;

import cz.damematiku.damematiku.depinject.module.ApiModule;
import cz.damematiku.damematiku.depinject.module.CommonModule;
import cz.damematiku.damematiku.depinject.scope.ApplicationScope;
import dagger.Component;
import okhttp3.OkHttpClient;


@ApplicationScope
@Component(modules = {CommonModule.class, ApiModule.class}, dependencies = ApplicationComponent.class)
public interface BaseComponent {

    OkHttpClient getOkHttpClient();

    MathService getApiService();

}
