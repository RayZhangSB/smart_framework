package org.smart4j.framework.bean;

import java.lang.reflect.Method;

/**
 * @ClassName handler
 * @Description: 封装Action  handler类和类上的方法
 * @Author Raymond Zhang
 * @Date 2018/5/7 19:19
 * @Version 1.0
 **/
public class Handler {


    private Class<?> controllerClass;
    private Method actionMethod;


    public Handler(Class<?> controllerClass, Method actionMethod) {
        this.controllerClass = controllerClass;
        this.actionMethod = actionMethod;
    }


    public Class<?> getControllerClass() {
        return controllerClass;
    }

    public Method getActionMethod() {
        return actionMethod;
    }
}
