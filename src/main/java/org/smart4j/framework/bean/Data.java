package org.smart4j.framework.bean;

/**
 * @ClassName Data
 * @Description: 模型数据对象
 * @Author Raymond Zhang
 * @Date 2018/5/7 20:01
 * @Version 1.0
 **/
public class Data
{
    //模型数据
    private Object model;
    public  Data(Object model){
        this.model = model;
    }

    public Object getModel() {
        return model;
    }
}
