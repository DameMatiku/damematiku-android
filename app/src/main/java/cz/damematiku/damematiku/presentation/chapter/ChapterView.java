package cz.damematiku.damematiku.presentation.chapter;

import cz.damematiku.damematiku.data.model.Chapter;
import cz.damematiku.damematiku.presentation.common.BaseView;

/**
 * Created by semanticer on 22. 4. 2016.
 */
public interface ChapterView extends BaseView {
    void setTitle(Chapter chapter);
}
