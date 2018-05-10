package org.smart4j.framework.helper;

import org.smart4j.framework.annotation.Controller;
import org.smart4j.framework.annotation.Service;
import org.smart4j.framework.util.ClassUtil;

import java.lang.annotation.Annotation;
import java.util.HashSet;
import java.util.Set;

/**
 * @ClassName ClassHelper
 * @Description: 类操作助手类
 * @Author Raymond Zhang
 * @Date 2018/5/6 19:51
 * @Version 1.0
 **/
public final class ClassHelper {
    private static final Set<Class<?>> CLASS_SET;


    static {
        String basePackage = ConfigHelper.getAppBasePackage();
        CLASS_SET = ClassUtil.getClassSet(basePackage);
    }

    /*
    Get the all classes for provided package
     */
    public static Set<Class<?>> getClassSet() {
        return CLASS_SET;
    }

    /*
    Get the all classes defined( Service) for provided package
     */
    public static Set<Class<?>> getServiceClassSet() {
        Set<Class<?>> classSet = new HashSet<Class<?>>();
        for (Class<?> cls : CLASS_SET) {
            if (cls.isAnnotationPresent(Service.class)) ;
            classSet.add(cls);
        }
        return classSet;
    }

    /*
    Get the all classes defined( Controller) for provided package
     */
    public static Set<Class<?>> getControllerClassSet() {
        Set<Class<?>> classSet = new HashSet<Class<?>>();
        for (Class<?> cls : CLASS_SET) {
            if (cls.isAnnotationPresent(Controller.class)) ;
            classSet.add(cls);
        }
        return classSet;
    }

    /*
   Get the all classes defined( Controller or Service .etc) for provided package
    */
    public static Set<Class<?>> getBeanClassSet() {
        Set<Class<?>> beanClassSet = new HashSet<Class<?>>();
        beanClassSet.addAll(getServiceClassSet());
        beanClassSet.addAll(getControllerClassSet());
        return beanClassSet;
    }

    /*
    get son class and impl
     */
    public static Set<Class<?>> getClassSetBySuper(Class<?> superClass) {
        Set<Class<?>> classSet = new HashSet<Class<?>>();
        for (Class<?> cls : CLASS_SET) {
            if (superClass.isAssignableFrom(cls) && !superClass.equals(cls)) {
                classSet.add(cls);
            }
        }
        return classSet;
    }

    public static Set<Class<?>> getClassSetByAnnotation(Class<? extends Annotation> annotationCls) {
        Set<Class<?>> classSet = new HashSet<Class<?>>();
        for (Class<?> cls : CLASS_SET) {
            if (cls.isAnnotationPresent(annotationCls)) {
                classSet.add(cls);
            }
        }
        return classSet;


    }

}
