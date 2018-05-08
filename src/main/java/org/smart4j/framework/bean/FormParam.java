package org.smart4j.framework.bean;

/**
 * @ClassName FormParam
 * @Description:
 * @Author Raymond Zhang
 * @Date 2018/5/8 19:54
 * @Version 1.0
 **/
public class FormParam {

    private String fieldName;
    private Object fieldValue;

    public FormParam(String fieldName, Object fieldValue) {
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
    }

    public String getFieldName() {
        return fieldName;
    }

    public Object getFieldValue() {
        return fieldValue;
    }
}
