package cz.damematiku.damematiku.data.model;

import auto.parcel.AutoParcel;
import android.os.Parcelable;


@AutoGson
@AutoParcel
abstract public class Author implements Parcelable {

    abstract public String name();
    abstract public String avatarUrl();

    public static Author create(String name, String avatarUrl) {
        return new AutoParcel_Author(name, avatarUrl);
    }
}
