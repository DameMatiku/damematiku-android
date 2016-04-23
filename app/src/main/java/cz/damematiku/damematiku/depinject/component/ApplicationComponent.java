package cz.damematiku.damematiku.depinject.component;

import android.app.Application;

import javax.inject.Singleton;

import cz.damematiku.damematiku.depinject.module.ApplicationModule;
import dagger.Component;


@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {
    Application getApplication();
}
