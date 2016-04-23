package cz.damematiku.damematiku.presentation.chapter;

import android.util.Log;

import java.io.IOException;

import javax.inject.Inject;

import cz.damematiku.damematiku.data.MathService;
import cz.damematiku.damematiku.data.model.Chapter;
import cz.damematiku.damematiku.presentation.common.BasePresenter;
import okhttp3.ResponseBody;
import retrofit2.Response;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by semanticer on 22. 4. 2016.
 */
public class ChapterPresenter extends BasePresenter<ChapterView> {

    private Chapter chapter;
    private MathService service;

    @Inject
    public ChapterPresenter(MathService service) {
        this.service = service;
    }

    @Override
    public void start() {

        service.chapter(chapter.id())
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::handleChapter, Throwable::printStackTrace);

        mapToView(v -> v.setTitle(chapter));

    }

    private void handleChapter(Response<Chapter> chapterResponse) {
        if (chapterResponse.isSuccessful()) {
            Chapter detailChapter = chapterResponse.body();
            chapter = detailChapter;
            mapToView(v -> v.showVideos(chapter.description(), chapter.videos()));
        } else {
            ResponseBody eb = chapterResponse.errorBody();
            try {
                Log.e("ChapterPresenter", eb.string());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void setData(Chapter chapter) {
        this.chapter = chapter;
    }

    public Chapter getChapter() {
        return chapter;
    }

    public void setChapter(Chapter chapter) {
        this.chapter = chapter;
    }
}
