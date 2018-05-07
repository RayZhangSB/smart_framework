package org.smart4j.framework.util;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @ClassName CollectionUtil
 * @Description:
 * @Author Raymond Zhang
 * @Date 2018/5/7 19:00
 * @Version 1.0
 **/
public final class CollectionUtil {
    public static boolean isNotEmpty(Map<Class<?>, Object> beanMap) {

    return beanMap.size()>0;

    }
    public static boolean isNotEmpty(Set<Class<?>> beanMap) {

        return beanMap.size()>0;
    }
    public static boolean isNotEmpty(List<Class<?>> beanMap) {

        return beanMap.size()>0;
    }

}
