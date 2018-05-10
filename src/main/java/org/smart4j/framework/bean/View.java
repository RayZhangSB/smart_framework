package org.smart4j.framework.bean;

import java.util.Map;

/**
 * @ClassName View
 * @Description:
 * @Author Raymond Zhang
 * @Date 2018/5/7 19:58
 * @Version 1.0
 **/
public class View {
    //视图路径
    private String path;
    //模型数据
    private Map<String, Object> model;

    public View(String path, Map<String, Object> model) {
        this.path = path;
        this.model = model;
    }

    public View addMODEL(String key, Object value) {
        model.put(key, value);
        return this;
    }

    public String getPath() {
        return path;
    }

    public Map<String, Object> getModel() {
        return model;
    }
}
