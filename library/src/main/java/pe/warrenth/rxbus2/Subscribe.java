package pe.warrenth.rxbus2;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by warrenth on 2018-01-23.
 */

@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Subscribe {
    String eventTag() default "";
    ThreadMode threadMode() default ThreadMode.SINGLE;
}
