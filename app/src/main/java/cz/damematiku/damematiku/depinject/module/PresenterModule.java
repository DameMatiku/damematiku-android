package cz.damematiku.damematiku.depinject.module;

import cz.damematiku.damematiku.data.MathService;
import cz.damematiku.damematiku.depinject.scope.ActivityScope;
import cz.damematiku.damematiku.presentation.main.MainPresenter;
import dagger.Module;
import dagger.Provides;

/**
 * Created by semanticer on 23. 4. 2016.
 */
@Module
public class PresenterModule {

    @Provides
    @ActivityScope
    MainPresenter providesMain(MathService mathService){
        return new MainPresenter(mathService);
    }
}
