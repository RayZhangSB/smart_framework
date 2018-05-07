package org.smart4j.framework.bean;

import org.smart4j.framework.util.CastUtil;

import java.util.Map;

/**
 * @ClassName Param
 * @Description: 请求参数
 * @Author Raymond Zhang
 * @Date 2018/5/7 19:51
 * @Version 1.0
 **/
public class Param {
    private Map<String ,Object> paramMap;

    public Param(Map<String, Object> paramMap) {
        this.paramMap = paramMap;
    }

    public  long getLong(String name){
        return CastUtil.castLong(paramMap.get(name));
    }

    public  int getInteger(String name){
        return CastUtil.castInteger(paramMap.get(name));
    }



}
