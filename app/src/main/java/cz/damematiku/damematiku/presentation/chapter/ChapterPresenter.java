package cz.damematiku.damematiku.presentation.chapter;

import cz.damematiku.damematiku.data.MockMathService;
import cz.damematiku.damematiku.data.model.Chapter;
import cz.damematiku.damematiku.presentation.common.BasePresenter;

/**
 * Created by semanticer on 22. 4. 2016.
 */
public class ChapterPresenter extends BasePresenter<ChapterView> {

    private Chapter chapter;

    @Override
    public void start() {
        // load detail chapter
        MockMathService service = new MockMathService();
        service.chapter(1).subscribe(chapterDetail -> {
            chapter = chapterDetail;
            mapToView(v -> v.showVideos(chapter.description(), chapter.videos()));
        }, Throwable::printStackTrace);

        mapToView(v -> v.setTitle(chapter));

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
