package org.smart4j.framework.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * @ClassName StreamUtil
 * @Description:
 * @Author Raymond Zhang
 * @Date 2018/5/7 20:37
 * @Version 1.0
 **/
public final class StreamUtil {
    /*
    从输入流取字符串
     */
    public static String getString(InputStream inputStream) throws IOException {

        StringBuilder sb = new StringBuilder();

        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        String line;
        while ((line = reader.readLine())!=null){
            sb.append(line);
        }
        return sb.toString();

    }
}
