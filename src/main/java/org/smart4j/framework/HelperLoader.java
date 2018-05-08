package org.smart4j.framework;

import org.smart4j.framework.helper.*;
import org.smart4j.framework.util.ClassUtil;

/**
 * @ClassName HelperLoader
 * @Description: 初始化所有容器--加载静态块
 * @Author Raymond Zhang
 * @Date 2018/5/7 19:45
 * @Version 1.0
 **/
public class HelperLoader {
    public static void init() {
        Class<?>[] classList = {
                ClassHelper.class,

                BeanHelper.class,
                AopHelper.class,
                IocHelper.class,
                ControllerHelper.class
        };
        for (Class<?> cls : classList) {
            ClassUtil.loadClass(cls.getName());
        }


    }

}
