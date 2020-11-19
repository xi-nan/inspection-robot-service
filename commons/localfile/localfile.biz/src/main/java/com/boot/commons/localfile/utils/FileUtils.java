package com.boot.commons.localfile.utils;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.IdUtil;
import com.boot.commons.core.properties.SiteProperties;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.util.List;

/**
 * UploadUtil
 *
 * @author xinan
 * @date 2020/11/15
 */
public class FileUtils {

    public static String genName(String fileType) {
        return IdUtil.getSnowflake(SiteProperties._this.getWorkerId(), SiteProperties._this.getDataCenterId()).nextIdStr()
                + "." + fileType;
    }

    public static String saveFile(MultipartFile source, String saveDir, String saveName) throws IOException {
        FileUtil.mkdir(saveDir);
        File saveFilePath = new File(saveDir, saveName);
        source.transferTo(saveFilePath);
        return saveFilePath.getAbsolutePath();
    }

    public static String mergeFile(List<String> sliceFilePaths, String saveDir, String saveName) throws IOException {
        FileUtil.mkdir(saveDir);
        File saveFilePath = new File(saveDir, saveName);
        FileChannel out = new FileOutputStream(saveFilePath).getChannel();
        for (String slicePath : sliceFilePaths) {
            File patch = new File(slicePath);
            FileChannel in = new FileInputStream(patch).getChannel();
            in.transferTo(0, in.size(), out);
            in.close();
//            patch.delete();
        }
        out.close();
        return saveName;
    }
}
