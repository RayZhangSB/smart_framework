package org.smart4j.framework.annotation;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/*
控制器类的方法上使用 类似ResquestMapping
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Action {
    /*
    请求类型和路径--
     */
    String value();
}
