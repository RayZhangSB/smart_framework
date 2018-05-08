package org.smart4j.framework.util;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

/**
 * @ClassName FileUtil
 * @Description:
 * @Author Raymond Zhang
 * @Date 2018/5/8 20:10
 * @Version 1.0
 **/
public final class FileUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(FileUtil.class);

    public static void createFile(String filePath) {
        File file;
        try {
            file = new File(filePath);
            File parentDir = file.getParentFile();
            if (!parentDir.exists()) {
                FileUtils.forceMkdir(parentDir);
            }


        } catch (Exception e) {
            LOGGER.error("upload file failed", e);
            throw new RuntimeException(e);
        }
    }

}
