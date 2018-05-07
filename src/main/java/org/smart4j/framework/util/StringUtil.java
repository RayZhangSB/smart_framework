package org.smart4j.framework.util;

/**
 * @ClassName StringUtil
 * @Description:
 * @Author Raymond Zhang
 * @Date 2018/5/6 19:18
 * @Version 1.0
 **/
public final class StringUtil {
    public static boolean isNotEmpty(String packageName) {
        return !("".equals(packageName));
    }
}
