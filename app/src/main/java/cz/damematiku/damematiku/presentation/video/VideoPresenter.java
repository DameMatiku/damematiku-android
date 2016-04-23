package cz.damematiku.damematiku.presentation.video;

import javax.inject.Inject;

import cz.damematiku.damematiku.data.MathService;
import cz.damematiku.damematiku.data.model.Video;
import cz.damematiku.damematiku.presentation.common.BasePresenter;
import retrofit2.Response;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by semanticer on 22. 4. 2016.
 */
public class VideoPresenter extends BasePresenter<VideoView> {

    private Video video;
    private MathService mathService;

    @Inject
    public VideoPresenter(MathService mathService){
        this.mathService = mathService;
    }

    @Override
    public void start() {

        mathService.videos(video.id())
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::handleVideo, Throwable::printStackTrace);

    }

    private void handleVideo(Response<Video> videoResponse) {
        if (videoResponse.isSuccessful()) {
            video = videoResponse.body();
            mapToView(v -> v.showVideo(video));
        } else {

        }

    }

    public void setData(Video video) {
        this.video = video;
    }

    public void upvote() {

    }

    public void downvote() {

    }


    public String getViewId(){
        return video.youtubeId();
    }
}
