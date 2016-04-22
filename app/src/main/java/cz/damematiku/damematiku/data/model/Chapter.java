package cz.damematiku.damematiku.data.model;

import auto.parcel.AutoParcel;
import android.os.Parcelable;

@AutoGson
@AutoParcel
abstract public class Chapter implements Parcelable {

    abstract public int id();
    abstract public String title();

    public static Chapter create(int id, String title) {
        return new AutoParcel_Chapter(id, title);
    }
}
