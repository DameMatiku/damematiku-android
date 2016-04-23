package cz.damematiku.damematiku.presentation.main;

import android.util.Log;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import cz.damematiku.damematiku.data.MathService;
import cz.damematiku.damematiku.data.model.Section;
import cz.damematiku.damematiku.data.model.Tag;
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

    private Tag selectedTag;
    private Tag selectedSubTag;


    @Inject
    public MainPresenter(MathService service) {
        this.service = service;
    }

    @Override
    public void start() {
        Observable<Response<List<Tag>>> tagsObs = service.tags();

        tagsObs
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::handleTags, Throwable::printStackTrace);


        reloadSections(Collections.emptyList());
    }

    private void reloadSections(List<Tag> tags) {
        Observable<Response<List<Section>>> sectionsObs = service.sections(encodeTags(tags));

        sectionsObs
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::handleSections, Throwable::printStackTrace);
    }

    private void handleTags(Response<List<Tag>> listResponse) {
        if (listResponse.isSuccessful()) {
            List<Tag> tags = listResponse.body();
            mapToView(v -> v.showTags(tags));
        } else {
            ResponseBody eb = listResponse.errorBody();
            try {
                Log.e("MainPresenter", eb.string());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
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

    public void tagSelected(Tag tag) {
        selectedTag = tag;
        reloadSections(Collections.singletonList(tag));

        if (tag != null && tag.subtags() != null && tag.subtags().size() > 0) {
            mapToView(v -> v.showSubTag(tag.subtags()));
        } else {
            mapToView(MainView::hideSubTags);
        }
    }

    public void subTagSelected(Tag tag) {
        selectedSubTag = tag;
        reloadSections(Arrays.asList(selectedTag, selectedSubTag));
    }

    public Map<String, String> encodeTags(List<Tag> tags){
        Map<String, String> map = new HashMap<>();
        if (tags == null)
            return map;

        for (Tag t : tags) {
            if (t != null) {
                map.put("tags[]", t.id() + "");
            }
        }
        return map;
    }
}
