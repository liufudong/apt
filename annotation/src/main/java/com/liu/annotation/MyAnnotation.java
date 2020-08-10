package com.liu.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.CLASS)       //表示编译时的注解
@Target(ElementType.TYPE)
public @interface MyAnnotation {
    //    int [] value() default {1,2,4};
    String text() default "";
}
