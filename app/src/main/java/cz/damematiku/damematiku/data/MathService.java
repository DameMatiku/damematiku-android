package cz.damematiku.damematiku.data;

import java.util.List;

import cz.damematiku.damematiku.data.model.Section;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by semanticer on 22. 4. 2016.
 */
public interface MathService {

    @GET("chapters/{chapterId}")
    Observable<Response<List<Section>>> sections(@Path("chapterId") int chapterId);
}
