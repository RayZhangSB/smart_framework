package org.smart4j.framework.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * @ClassName CodecUtil
 * @Description:
 * @Author Raymond Zhang
 * @Date 2018/5/7 20:38
 * @Version 1.0
 **/
public final class CodecUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(CodecUtil.class);

    public static String decodeURL(String source) {
        String target;
        try {
            target = URLEncoder.encode(source, "UTF-8");
        } catch (Exception e) {
            LOGGER.error("encode url failed", e);
            throw new RuntimeException();
        }
        return target;
    }


    public static String encodeURL(String source) {
        String target;
        try {
            target = URLDecoder.decode(source, "UTF-8");
        } catch (Exception e) {
            LOGGER.error("decode url failed", e);
            throw new RuntimeException();
        }
        return target;
    }
}
