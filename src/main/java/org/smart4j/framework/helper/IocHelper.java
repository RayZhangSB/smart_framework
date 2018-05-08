package org.smart4j.framework.helper;

import org.smart4j.framework.annotation.Inject;
import org.smart4j.framework.util.ArrayUtil;
import org.smart4j.framework.util.CollectionUtil;
import org.smart4j.framework.util.ReflectionUtil;

import java.lang.reflect.Field;
import java.util.Map;

/**
 * @ClassName IocHelper
 * @Description:
 * @Author Raymond Zhang
 * @Date 2018/5/7 18:57
 * @Version 1.0
 **/
public final class IocHelper {


    static {
        Map<Class<?>,Object> beanMap = BeanHelper.getBeanMap();
        if(CollectionUtil.isNotEmpty(beanMap)){
            for(Map.Entry<Class<?>,Object> entry:beanMap.entrySet()){
                Class<?> beanClass = entry.getKey();
                Object beanInstance = entry.getValue();
                //get all fields
                Field[] fields = beanClass.getDeclaredFields();

                if(ArrayUtil.isNotEmpty(fields)){
                    for(Field f: fields){
                        if(f.isAnnotationPresent(Inject.class)){
                            Class<?> injectClass = f.getType();
                            Object injectBean = beanMap.get(injectClass);
                            if(injectBean != null){
                                ReflectionUtil.setField(beanInstance,f,injectBean);



                            }
                        }
                    }
                }

            }
        }
    }
}
