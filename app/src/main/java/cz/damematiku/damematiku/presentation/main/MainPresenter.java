package cz.damematiku.damematiku.presentation.main;

import android.util.Log;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;

import cz.damematiku.damematiku.data.MathService;
import cz.damematiku.damematiku.data.MockMathService;
import cz.damematiku.damematiku.data.model.Section;
import cz.damematiku.damematiku.presentation.common.BasePresenter;
import okhttp3.ResponseBody;
import retrofit2.Response;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by semanticer on 22. 4. 2016.
 */
public class MainPresenter extends BasePresenter<MainView> {

    private MathService service;

    @Inject
    public MainPresenter(MathService service) {
        this.service = service;
    }

    @Override
    public void start() {
        Observable<Response<List<Section>>> sectionsObs = service.sections();

        sectionsObs
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::handleSections, Throwable::printStackTrace);
    }

    private void handleSections(Response<List<Section>> listResponse) {
        if (listResponse.isSuccessful()) {
            List<Section> sections = listResponse.body();
            mapToView(v -> v.showSections(sections));
        } else {
            ResponseBody eb = listResponse.errorBody();
            try {
                Log.e("MainPresenter", eb.string());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
