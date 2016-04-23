package cz.damematiku.damematiku.data.model;

import auto.parcel.AutoParcel;
import android.os.Parcelable;
import android.support.annotation.Nullable;

import java.util.List;

@AutoGson
@AutoParcel
abstract public class Chapter implements Parcelable {

    abstract public int id();
    abstract public String name();
    abstract public @Nullable String description();
    abstract public @Nullable List<Video> videos();

    public static Chapter create(int id, String name, @Nullable String description, @Nullable List<Video> videos) {
        return new AutoParcel_Chapter(id, name, description, videos);
    }
}
