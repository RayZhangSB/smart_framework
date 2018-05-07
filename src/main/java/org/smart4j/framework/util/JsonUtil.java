package org.smart4j.framework.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @ClassName JsonUtil
 * @Description:
 * @Author Raymond Zhang
 * @Date 2018/5/7 20:48
 * @Version 1.0
 **/
public final class JsonUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(JsonUtil.class);
    private  static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();



    /*
    pojo to json
     */
    public static <T>String toJson(T obj) {
        String json;
        try{
            json = OBJECT_MAPPER.writeValueAsString(obj);
        } catch (Exception e) {
            LOGGER.error("convert POJO to Json failed",e);
            throw new RuntimeException();
        }

        return json;
    }

    /*
   json to pojo
    */
    public static <T>T  fromJson(String json ,Class<T>type) {
        T pojo;
        try{
            pojo = OBJECT_MAPPER.readValue(json,type);
        } catch (Exception e) {
            LOGGER.error("convert   Json  to POJO failed",e);
            throw new RuntimeException();
        }

        return pojo;
    }



}
