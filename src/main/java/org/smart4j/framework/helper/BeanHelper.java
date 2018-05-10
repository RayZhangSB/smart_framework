package org.smart4j.framework.helper;

import org.smart4j.framework.util.ReflectionUtil;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @ClassName BeanHelper
 * @Description:
 * @Author Raymond Zhang
 * @Date 2018/5/6 20:20
 * @Version 1.0
 **/
public final class BeanHelper {
    /*
    bean映射 类和实例的映射
     */
    private static final Map<Class<?>, Object> BEAN_MAP = new HashMap<Class<?>, Object>();


    static {
        Set<Class<?>> beanClassSet = ClassHelper.getBeanClassSet();
        for (Class<?> beanClass : beanClassSet) {
            Object obj = ReflectionUtil.newInstance(beanClass);
            BEAN_MAP.put(beanClass, obj);
        }
    }

    /*
    获取bean映射
     */
    public static Map<Class<?>, Object> getBeanMap() {
        return BEAN_MAP;
    }

    /*
    获取实例
     */
    @SuppressWarnings("unchecked")
    public static <T> T getBean(Class<?> clz) {

        if (!BEAN_MAP.containsKey(clz)) {
            throw new RuntimeException("can't get bean by class:" + clz);

        }
        return (T) BEAN_MAP.get(clz);
    }


    public static void setBean(Class<?> cls, Object obj) {
        BEAN_MAP.put(cls, obj);
    }
}
