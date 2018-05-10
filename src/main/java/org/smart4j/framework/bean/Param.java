package org.smart4j.framework.bean;

import org.smart4j.framework.util.CastUtil;
import org.smart4j.framework.util.CollectionUtil;
import org.smart4j.framework.util.StringUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName Param
 * @Description: 请求参数
 * @Author Raymond Zhang
 * @Date 2018/5/7 19:51
 * @Version 1.0
 **/
public class Param {
    private Map<String, Object> paramMap;
    private List<FormParam> formParamList;
    private List<FileParam> fileParamList;

    public Param(List<FormParam> formParamList, List<FileParam> fileParamList) {
        this.formParamList = formParamList;
        this.fileParamList = fileParamList;
    }

    public Param(Map<String, Object> paramMap) {
        this.paramMap = paramMap;
    }

    public Param(List<FormParam> formParamList) {
        this(formParamList, null);
    }

    public long getLong(String name) {
        return CastUtil.castLong(paramMap.get(name));
    }

    public int getInteger(String name) {
        return CastUtil.castInteger(paramMap.get(name));
    }

    /* public boolean isEmpty() {
         return paramMap.isEmpty();
     }
 */
    /*
    请求参数映射
     */
    public Map<String, Object> getFieldMap() {
        Map<String, Object> fieldMap = new HashMap<String, Object>();
        if (CollectionUtil.isNotEmpty(formParamList)) {
            for (FormParam formParam : formParamList) {
                String fieldName = formParam.getFieldName();
                Object fieldValue = formParam.getFieldValue();
                if (fieldMap.containsKey(fieldName)) {
                    fieldValue = fieldMap.get(fieldName) + StringUtil.SEPARATOR + fieldValue;
                }

                fieldMap.put(fieldName, fieldValue);
            }
        }
        return fieldMap;
    }


    public Map<String, List<FileParam>> getFileMap() {
        Map<String, List<FileParam>> fileMap = new HashMap<String, List<FileParam>>();
        if (CollectionUtil.isNotEmpty(fileParamList)) {
            for (FileParam fileParam : fileParamList) {
                String fieldName = fileParam.getFieldName();
                List<FileParam> fileParamList;
                if (fileMap.containsKey(fieldName)) {
                    fileParamList = fileMap.get(fieldName);
                } else {
                    fileParamList = new ArrayList<FileParam>();
                }
                fileParamList.add(fileParam);
                fileMap.put(fieldName, fileParamList);
            }
        }
        return fileMap;


    }

    /*
    获取全部上传文件
     */
    public List<FileParam> getFileList(String fieldName) {
        return getFileMap().get(fieldName);
    }

    /*
    获取唯一上传文件
     */
    public FileParam getFile(String fieldName) {
        List<FileParam> fileParamList = getFileList(fieldName);
        if (CollectionUtil.isNotEmpty(fileParamList) && fileParamList.size() == 1) {
            return fileParamList.get(0);
        }
        return null;
    }

    public boolean isEmpty() {
        return CollectionUtil.isNotEmpty(fileParamList) && CollectionUtil.isEmpty(fileParamList);
    }


}
