package cz.damematiku.damematiku.presentation.main;

import java.util.List;

import cz.damematiku.damematiku.data.model.Section;
import cz.damematiku.damematiku.data.model.Tag;
import cz.damematiku.damematiku.presentation.common.BaseView;

/**
 * Created by semanticer on 22. 4. 2016.
 */
public interface MainView extends BaseView {
    void showSections(List<Section> sections);

    void showTags(List<Tag> tags);
}
