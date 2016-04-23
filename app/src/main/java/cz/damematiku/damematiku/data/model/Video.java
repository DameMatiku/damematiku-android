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
    abstract public int votes();
    abstract public String description();
    abstract public String youtubeId();
    abstract public Author author();

    public static Video create(int id, String title, int votes, String description, String youtubeId, Author author) {
        return new AutoParcel_Video(id, title, votes, description, youtubeId, author);
    }
}
