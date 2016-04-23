package cz.damematiku.damematiku;

import android.app.Application;
import android.content.Context;

import cz.damematiku.damematiku.depinject.component.ApplicationComponent;
import cz.damematiku.damematiku.depinject.component.BaseComponent;
import cz.damematiku.damematiku.depinject.component.DaggerApplicationComponent;
import cz.damematiku.damematiku.depinject.component.DaggerBaseComponent;
import cz.damematiku.damematiku.depinject.module.ApplicationModule;

/**
 * Created by semanticer on 23. 4. 2016.
 */
public class MathApplication extends Application {
    private static BaseComponent baseComponent;


    @Override
    public void onCreate() {
        super.onCreate();

        ApplicationComponent applicationComponent = DaggerApplicationComponent
                .builder()
                .applicationModule(new ApplicationModule(this))
                .build();

        baseComponent = DaggerBaseComponent.builder()
                .applicationComponent(applicationComponent)
                .build();
    }

    public static BaseComponent getBaseComponent(){
        return baseComponent;
    }
}
