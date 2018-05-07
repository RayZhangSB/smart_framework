package org.smart4j.framework.util;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * @ClassName PropsUtil
 * @Description:
 * @Author Raymond Zhang
 * @Date 2018/5/6 18:03
 * @Version 1.0
 **/
public class PropsUtil {
    public static Properties loadProps(String configFile) {
        Properties prop = new Properties();
        try {
            prop.load(new DataInputStream(new FileInputStream(configFile)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return prop;
    }

    public static String getString(Properties configProps, String name,String defaultStr) {
        String result = configProps.getProperty(name);
        if("".equals(name)){
            return defaultStr;
        }
        return result;
    }
    public static String getString(Properties configProps, String name){
        return PropsUtil.getString(configProps,name,"");
    }
}
