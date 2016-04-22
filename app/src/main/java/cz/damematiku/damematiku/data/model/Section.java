package cz.damematiku.damematiku.data.model;

/**
 * Created by semanticer on 22. 4. 2016.
 */
import auto.parcel.AutoParcel;
import android.os.Parcelable;


//@AutoGson
@AutoParcel
abstract public class Section implements Parcelable {

    abstract public String text();

    public static Section create(String text) {
        return new AutoParcel_Section(text);
    }
}
