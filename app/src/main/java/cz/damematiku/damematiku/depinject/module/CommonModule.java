package cz.damematiku.damematiku.depinject.module;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import cz.damematiku.damematiku.data.model.AutoParcelAdapterFactory;
import cz.damematiku.damematiku.depinject.scope.ApplicationScope;
import dagger.Module;
import dagger.Provides;


@Module
public class CommonModule {

    @ApplicationScope
    @Provides
    Gson provideGson() {
        return new GsonBuilder()
                .registerTypeAdapterFactory(new AutoParcelAdapterFactory())
                .create();
    }


}
