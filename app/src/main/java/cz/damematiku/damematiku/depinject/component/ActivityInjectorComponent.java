package cz.damematiku.damematiku.depinject.component;

import cz.damematiku.damematiku.depinject.module.PresenterModule;
import cz.damematiku.damematiku.depinject.scope.ActivityScope;
import cz.damematiku.damematiku.presentation.main.MainActivity;

import dagger.Component;


@ActivityScope
@Component(dependencies = {BaseComponent.class}, modules = PresenterModule.class)
public interface ActivityInjectorComponent {
    void inject(MainActivity activity);

}
