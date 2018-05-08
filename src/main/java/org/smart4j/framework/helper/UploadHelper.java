package org.smart4j.framework.helper;

import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.smart4j.framework.bean.FileParam;
import org.smart4j.framework.util.FileUtil;
import org.smart4j.framework.util.StreamUtil;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.List;

/**
 * @ClassName UploadHelper
 * @Description:
 * @Author Raymond Zhang
 * @Date 2018/5/8 19:57
 * @Version 1.0
 **/
public final class UploadHelper {
    private static final Logger LOGGER = LoggerFactory.getLogger(UploadHelper.class);


    private static ServletFileUpload servletFileUpload;

    private static void init(ServletContext servletContext) {
        File repository = (File) servletContext.getAttribute("javax.servlet.context.tempdir");
        servletFileUpload = new ServletFileUpload(new DiskFileItemFactory(DiskFileItemFactory.DEFAULT_SIZE_THRESHOLD, repository));
        int uploadLimit = ConfigHelper.getAppUploadLimit();
        if (uploadLimit != 0) {
            servletFileUpload.setFileSizeMax(uploadLimit * 1024 * 1024);
        }
    }

    public static boolean isMultipart(HttpServletRequest req) {

        return ServletFileUpload.isMultipartContent(req);
    }

    /*
    create request obj
     */
//    public static Param createParam(HttpServletRequest req){
//        List<FormParam> formParamList =new ArrayList<FormParam>();
//        List<FileParam> fileParamList =new ArrayList<FileParam>();
//
//
//
//    }

    /*
    上传文件
     */
    public static void uploadFile(String basePath, FileParam fileParam) {
        try {
            if (fileParam != null) {
                String filePath = basePath + fileParam.getFileName();
                FileUtil.createFile(filePath);
                InputStream inputStream = new BufferedInputStream(fileParam.getInputStream());
                OutputStream outputStream = new BufferedOutputStream(new FileOutputStream(filePath));
                StreamUtil.copyStream(inputStream, outputStream);
            }
        } catch (IOException e) {
            LOGGER.error("upload file failed", e);
            throw new RuntimeException(e);
        }
    }

    /*
    批量
     */
    public static void uploadFile(String basePath, List<FileParam> fileParamList) {

        try {
            if (fileParamList.size() > 0) {
                for (FileParam fileParam : fileParamList) {
                    uploadFile(basePath, fileParam);
                }
            }
        } catch (Exception e) {
            LOGGER.error("upload multi file failed", e);
            throw new RuntimeException(e);
        }


    }

}
