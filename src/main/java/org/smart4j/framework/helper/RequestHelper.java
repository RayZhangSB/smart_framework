package org.smart4j.framework.helper;

import org.smart4j.framework.bean.FormParam;
import org.smart4j.framework.bean.Param;
import org.smart4j.framework.util.ArrayUtil;
import org.smart4j.framework.util.CodecUtil;
import org.smart4j.framework.util.StreamUtil;
import org.smart4j.framework.util.StringUtil;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import java.util.List;

/**
 * @ClassName RequestHelper
 * @Description:
 * @Author Raymond Zhang
 * @Date 2018/5/9 13:17
 * @Version 1.0
 **/
public final class RequestHelper {

    /*
    创建请求对象
     */
    public static Param createParam(HttpServletRequest request) {
        List<FormParam> formParamList = new ArrayList<FormParam>();
        formParamList.addAll(parseParamterNames(request));
        try {
            formParamList.addAll(parseInputStream(request));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new Param(formParamList);


    }

    private static Collection<? extends FormParam> parseInputStream(HttpServletRequest request) throws IOException {
        List<FormParam> formParamList = new ArrayList<FormParam>();
        String body = CodecUtil.decodeURL(StreamUtil.getString(request.getInputStream()));
        if (StringUtil.isNotEmpty(body)) {
            String[] kvs = StringUtil.splitString(body, "&");
            if (ArrayUtil.isNotEmpty(kvs)) {

                for (String kv : kvs) {
                    String[] array = StringUtil.splitString(kv, "=");
                    if (ArrayUtil.isNotEmpty(array) && array.length == 2) {
                        String fieldName = array[0];
                        String fieldValue = array[1];
                        formParamList.add(new FormParam(fieldName, fieldValue));
                    }
                }

            }
        }

        return formParamList;
    }

    private static Collection<? extends FormParam> parseParamterNames(HttpServletRequest request) {
        List<FormParam> formParamList = new ArrayList<FormParam>();
        Enumeration<String> paramNames = request.getParameterNames();
        while (paramNames.hasMoreElements()) {
            String fieldName = paramNames.nextElement();
            String[] fieldValues = request.getParameterValues(fieldName);
            if (ArrayUtil.isNotEmpty(fieldValues)) {
                Object fieldValue;
                if (fieldValues.length == 1) {
                    fieldValue = fieldValues[0];
                } else {
                    StringBuilder sb = new StringBuilder("");
                    for (int i = 0; i < fieldValues.length; i++) {
                        sb.append(fieldValues[i]);
                        if (i != fieldValues.length - 1) {
                            sb.append(StringUtil.SEPARATOR);
                        }
                    }
                    fieldValue = sb.toString();


                }
                formParamList.add(new FormParam(fieldName, fieldValue));
            }
        }


        return formParamList;
    }


}
