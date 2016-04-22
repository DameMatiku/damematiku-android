package cz.damematiku.damematiku.presentation.video;

import cz.damematiku.damematiku.data.model.Video;
import cz.damematiku.damematiku.presentation.common.BasePresenter;

/**
 * Created by semanticer on 22. 4. 2016.
 */
public class VideoPresenter extends BasePresenter<VideoView> {

    private Video video;

    @Override
    public void start() {

    }

    public void setData(Video video) {
        this.video = video;
    }
}
