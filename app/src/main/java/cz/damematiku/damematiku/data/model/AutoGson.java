package cz.damematiku.damematiku.data.model;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by semanticer on 22. 4. 2016.
 */
@Target(TYPE)
@Retention(RUNTIME)
public @interface AutoGson {
}
