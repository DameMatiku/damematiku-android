package cz.damematiku.damematiku.presentation.video;

import javax.inject.Inject;

import cz.damematiku.damematiku.data.MathService;
import cz.damematiku.damematiku.data.model.Video;
import cz.damematiku.damematiku.presentation.common.BasePresenter;

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

    }

    public void setData(Video video) {
        this.video = video;
    }

    public void upvote() {

    }

    public void downvote() {

    }
}
