package next.reflection;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.annotation.ElementType;

// This is the magic line you're missing!
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD) // Optional: restricts this to methods only
public @interface ElapsedTime {
    // You can leave this empty or add elements
}