package org.smart4j.framework.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @ClassName ReflectionUtil
 * @Description:
 * @Author Raymond Zhang
 * @Date 2018/5/6 20:03
 * @Version 1.0
 **/
public final class ReflectionUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(ReflectionUtil.class);

    /*
    create instance
     */
    public static Object newInstance(Class<?> clz) {
        Object instance;
        try {
            instance = clz.newInstance();
        } catch (Exception e) {
            LOGGER.error("create instance failed", e);
            throw new RuntimeException();
        }
        return instance;
    }

    public static Object invokeMethod(Object obj, Method method, Object... args) {
        Object res;
        method.setAccessible(true);
        try {
            res = method.invoke(obj, args);
        } catch (Exception e) {
            LOGGER.error("invoke method failed", e);
            throw new RuntimeException();
        }
        return res;

    }


    public static void setField(Object obj, Field field, Object value) {

        try {
            field.setAccessible(true);
            field.set(obj, value);
        } catch (Exception e) {
            LOGGER.error("set field failed", e);
            throw new RuntimeException();
        }
    }

}
