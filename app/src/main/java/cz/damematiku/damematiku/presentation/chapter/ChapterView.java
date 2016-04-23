package cz.damematiku.damematiku.presentation.chapter;

import java.util.List;

import cz.damematiku.damematiku.data.model.Chapter;
import cz.damematiku.damematiku.data.model.Video;
import cz.damematiku.damematiku.presentation.common.BaseView;

/**
 * Created by semanticer on 22. 4. 2016.
 */
public interface ChapterView extends BaseView {
    void setTitle(Chapter chapter);
    void showVideos(String chapterDescription ,List<Video> videos);
}
