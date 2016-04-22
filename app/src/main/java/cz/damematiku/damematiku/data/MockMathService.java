package cz.damematiku.damematiku.data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import cz.damematiku.damematiku.data.model.Chapter;
import cz.damematiku.damematiku.data.model.Section;
import cz.damematiku.damematiku.data.model.Video;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by semanticer on 22. 4. 2016.
 */
public class MockMathService {
    public Observable<List<Section>> sections(@Path("chapterId") int chapterId) {
        List<Section> sections = new ArrayList<>();
        List<Chapter> chapters1 = new ArrayList<>(Arrays.asList(Chapter.create(1, "Prvni", null, null), Chapter.create(2, "Druha", null, null)));
        List<Chapter> chapters2 = new ArrayList<>(Arrays.asList(Chapter.create(3, "First", null, null), Chapter.create(3, "Second", null, null)));
        sections.add(Section.create(1, "Section 1", chapters1));
        sections.add(Section.create(2, "Section 2", chapters2));
        return Observable.just(sections);
    }


    public Observable<Chapter> chapter(@Path("chapterId") int chapterId) {
        List<Video> videos = new ArrayList<>();
        videos.add(Video.create(1, "Video 1", "wKJ9KzGQq0w"));
        videos.add(Video.create(2, "Video 2", "wKJ9KzGQq0w"));
        videos.add(Video.create(3, "Video 3", "wKJ9KzGQq0w"));
        videos.add(Video.create(4, "Video 4", "wKJ9KzGQq0w"));
        videos.add(Video.create(5, "Video 5", "wKJ9KzGQq0w"));
        Chapter chapter = Chapter.create(1, "Test Chapter", "Text description", videos);
        return Observable.just(chapter);
    }
}
