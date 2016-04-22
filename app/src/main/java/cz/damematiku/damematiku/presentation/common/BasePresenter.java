package cz.damematiku.damematiku.presentation.common;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import rx.functions.Action1;

/**
 * Created by semanticer on 22. 4. 2016.
 */
public abstract class BasePresenter<V> implements Presenter<V> {

    private static final String TAG = "BasePresenter";
    private V view;

    @Override
    public void setView(@NonNull V view) {
        if (view == null) throw new IllegalArgumentException("View is null");
        this.view = view;
    }

    /**
     * @return current view for the presenter
     */
    @Nullable
    protected V getView() {
        return view;
    }

    /**
     * Helper method safely executing some method of the view.
     * If the view is null, action is not performed.
     * @param action to be performed on a view
     */
    protected void mapToView(Action1<V> action) {
        if (getView() != null) {
            action.call(getView());
        } else {
            Log.d(TAG, "no view to use");
        }
    }

    @Override
    public void onDetach(){
        view = null;
    }



}
