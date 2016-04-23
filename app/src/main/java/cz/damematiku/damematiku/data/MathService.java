package cz.damematiku.damematiku.data;

import java.util.List;
import java.util.Map;

import cz.damematiku.damematiku.data.model.Chapter;
import cz.damematiku.damematiku.data.model.Section;
import cz.damematiku.damematiku.data.model.Tag;
import retrofit2.Response;
import retrofit2.http.FieldMap;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import rx.Observable;

/**
 * Created by semanticer on 22. 4. 2016.
 */
public interface MathService {
//
//    @GET("subjects/1/sections")
//    Observable<Response<List<Section>>> sections();

    @GET("subjects/1/sections")
    Observable<Response<List<Section>>> sections(@QueryMap Map<String, String> tags);

    @GET("chapters/{chapterId}")
    Observable<Response<Chapter>> chapter(@Path("chapterId") int chapterId);

    @GET("tags")
    Observable<Response<List<Tag>>> tags();



}
