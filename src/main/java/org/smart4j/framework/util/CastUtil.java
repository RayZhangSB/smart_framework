package org.smart4j.framework.util;

/**
 * @ClassName CastUtil
 * @Description:
 * @Author Raymond Zhang
 * @Date 2018/5/7 19:53
 * @Version 1.0
 **/
public final class CastUtil {
    public static long castLong(Object o) {
        return Long.getLong(String.valueOf(o));
    }

    public static int castInteger(Object o) {
        return Integer.getInteger(String.valueOf(o));
    }
}
