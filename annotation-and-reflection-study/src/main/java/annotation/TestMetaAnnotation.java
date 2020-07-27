package annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @program: jnote
 * @description:
 * @author: Unuts
 * @create: 2020-07-22 08:00
 **/

public class TestMetaAnnotation {
}

@Target(value = {ElementType.METHOD, ElementType.TYPE})
@Retention(value = RetentionPolicy.RUNTIME)
@interface MyAnnotation {

    String name() default "sean";

    int age() default 18;

    String[] hobbies() default {"eat", "sleep"};
}
