package cz.damematiku.damematiku.data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import cz.damematiku.damematiku.data.model.Chapter;
import cz.damematiku.damematiku.data.model.Section;
import retrofit2.Response;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by semanticer on 22. 4. 2016.
 */
public class MockMathService {
    public Observable<List<Section>> sections(@Path("chapterId") int chapterId) {
        List<Section> sections = new ArrayList<>();
        List<Chapter> chapters1 = new ArrayList<>(Arrays.asList(Chapter.create(1, "Prvni"), Chapter.create(2, "Druha")));
        List<Chapter> chapters2 = new ArrayList<>(Arrays.asList(Chapter.create(3, "First"), Chapter.create(3, "Second")));
        sections.add(Section.create(1, "Section 1", chapters1));
        sections.add(Section.create(2, "Section 2", chapters2));
        return Observable.just(sections);
    }
}
