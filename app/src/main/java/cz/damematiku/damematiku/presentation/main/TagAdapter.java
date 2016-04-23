package cz.damematiku.damematiku.presentation.main;

import android.content.Context;
import android.widget.ArrayAdapter;

import java.util.List;

import cz.damematiku.damematiku.data.model.Tag;

/**
 * Created by semanticer on 23. 4. 2016.
 */
public class TagAdapter extends ArrayAdapter<Tag> {

    public TagAdapter(Context context, int resource, List<Tag> objects) {
        super(context, resource, objects);
    }
}
