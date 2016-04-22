package cz.damematiku.damematiku.presentation.common;

import android.support.annotation.NonNull;

public interface Presenter<V> {
    void setView(@NonNull V view);
    void onDetach();
    void start();
}
