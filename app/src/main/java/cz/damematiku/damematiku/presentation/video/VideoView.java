package cz.damematiku.damematiku.presentation.video;

import cz.damematiku.damematiku.data.model.Video;
import cz.damematiku.damematiku.presentation.common.BaseView;

/**
 * Created by semanticer on 22. 4. 2016.
 */
public interface VideoView extends BaseView {
    void showVideo(Video video);
}
