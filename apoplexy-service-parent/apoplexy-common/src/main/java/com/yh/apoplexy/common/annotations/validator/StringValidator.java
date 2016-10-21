/**
 *
 */
package com.yh.apoplexy.common.annotations.validator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 字符串校验
 *
 * @author
 */
@Inherited
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface StringValidator {

    boolean nullable() default true;

    String pattern() default "";

    int minLength() default 0;

    int maxLength() default Integer.MAX_VALUE;

    boolean chinese() default true;

    boolean letter() default true;

    boolean number() default true;

}
