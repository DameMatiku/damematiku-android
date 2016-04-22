package cz.damematiku.damematiku.data.model;

/**
 * Created by semanticer on 22. 4. 2016.
 */
import auto.parcel.AutoParcel;
import android.os.Parcelable;


@AutoGson
@AutoParcel
abstract public class Video implements Parcelable {

    abstract public int id();
    abstract public String title();
    abstract public String youtubeId();

    public static Video create(int id, String title, String youtubeId) {
        return new AutoParcel_Video(id, title, youtubeId);
    }
}
