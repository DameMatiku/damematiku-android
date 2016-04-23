package cz.damematiku.damematiku.data.model;

/**
 * Created by semanticer on 22. 4. 2016.
 */
import auto.parcel.AutoParcel;
import android.os.Parcelable;
import java.util.List;


@AutoGson
@AutoParcel
abstract public class Section implements Parcelable {

    abstract public int id();
    abstract public String name();
    abstract public List<Chapter> chapters();

    public static Section create(int id, String name, List<Chapter> chapters) {
        return new AutoParcel_Section(id, name, chapters);
    }
}
