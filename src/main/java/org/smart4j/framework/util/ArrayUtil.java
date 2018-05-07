package org.smart4j.framework.util;

/**
 * @ClassName ArrayUtil
 * @Description:
 * @Author Raymond Zhang
 * @Date 2018/5/7 19:08
 * @Version 1.0
 **/
public final class ArrayUtil {
    public static boolean isNotEmpty(Object[] objs) {
        return objs.length>0;
   }
    public static boolean isEmpty(Object[] objs) {
        return !isNotEmpty(objs);
    }
}
