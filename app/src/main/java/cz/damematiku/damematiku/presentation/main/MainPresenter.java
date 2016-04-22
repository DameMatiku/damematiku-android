package cz.damematiku.damematiku.presentation.main;

import java.util.List;

import cz.damematiku.damematiku.data.MockMathService;
import cz.damematiku.damematiku.data.model.Section;
import cz.damematiku.damematiku.presentation.common.BasePresenter;
import rx.Observable;

/**
 * Created by semanticer on 22. 4. 2016.
 */
public class MainPresenter extends BasePresenter<MainView> {


    @Override
    public void start() {
        MockMathService service = new MockMathService();
        Observable<List<Section>> sectionsObs = service.sections(1);

        sectionsObs.subscribe(sections -> mapToView(v -> v.showSections(sections)), Throwable::printStackTrace);
    }
}
