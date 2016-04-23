package cz.damematiku.damematiku.data.model;

import auto.parcel.AutoParcel;
import android.os.Parcelable;

import java.util.List;


@AutoGson
@AutoParcel
abstract public class Tag implements Parcelable {

    abstract public int id();
    abstract public String name();
    abstract public List<Tag> subtags();

    public static Tag create(int id, String name, List<Tag> subtags) {
        return new AutoParcel_Tag(id, name, subtags);
    }

}
