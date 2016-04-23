package cz.damematiku.damematiku.depinject.component;

import cz.damematiku.damematiku.depinject.module.PresenterModule;
import cz.damematiku.damematiku.depinject.scope.ActivityScope;
import cz.damematiku.damematiku.presentation.chapter.ChapterActivity;
import cz.damematiku.damematiku.presentation.main.MainActivity;

import cz.damematiku.damematiku.presentation.video.VideoActivity;
import dagger.Component;


@ActivityScope
@Component(dependencies = {BaseComponent.class}, modules = PresenterModule.class)
public interface ActivityInjectorComponent {
    void inject(MainActivity activity);

    void inject(VideoActivity videoActivity);

    void inject(ChapterActivity chapterActivity);
}
