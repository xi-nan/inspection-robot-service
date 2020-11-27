package com.boot.commons.localfile.utils;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.IdUtil;
import com.boot.commons.core.properties.SiteProperties;
import com.boot.commons.localfile.model.enums.LocalFileErrCodeEnum;
import org.springframework.web.multipart.MultipartFile;
import ws.schild.jave.*;

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

    public static void saveFile(MultipartFile source, String saveDir, String saveName) throws IOException {
        FileUtil.mkdir(saveDir);
        File saveFilePath = new File(saveDir, saveName);
        source.transferTo(saveFilePath);
    }

    public static void mergeFile(List<String> sliceFilePaths, String saveDir, String saveName) throws IOException {
        FileUtil.mkdir(saveDir);
        // String originalFileSaveDir = saveDir + "original/";
        // FileUtil.mkdir(originalFileSaveDir);
        File originalFile = new File(saveDir, saveName);
        if (!originalFile.exists()) {
            FileChannel out = new FileOutputStream(originalFile).getChannel();
            for (String slicePath : sliceFilePaths) {
                File patch = new File(slicePath);
                FileChannel in = new FileInputStream(patch).getChannel();
                in.transferTo(0, in.size(), out);
                in.close();
            }
            out.close();
        }
        // if (originalFile.exists() && "mp4".equals(FileUtil.extName(saveName).toLowerCase())) {
        //     recode(originalFile.getAbsolutePath(), saveDir + saveName);
        // }
    }

    public static void main(String[] args) {
        recode("/Users/xinan/tmp/B001020201126112507.mp4", "/Users/xinan/tmp/B001020201126112507-1.mp4");
    }

    public static void recode(String sourcePath, String targetPath) {

        File source = new File(sourcePath);
        File target = new File(targetPath);
        AudioAttributes audio = new AudioAttributes();
        audio.setCodec("libmp3lame");//aac
        //比特率是指每秒传送的比特(bit)数。单位为 bps(Bit Per Second)，比特率越高，传送数据速度越快
        audio.setBitRate(64 * 1024);//设置比特率
        audio.setChannels(1);//设置声音频道
        audio.setSamplingRate(22050);//设置节录率
        VideoAttributes video = new VideoAttributes();
        video.setCodec("h264");
        video.setBitRate(512 * 1024);
        //比特率是指每秒传送的比特(bit)数。单位为 bps(Bit Per Second)，比特率越高，传送数据速度越快
        // video.setBitRate(new Integer(800000));//设置比特率
        video.setFrameRate(30);//设置帧率,越大越流畅，越小越卡
        EncodingAttributes attrs = new EncodingAttributes();
        attrs.setFormat("mp4");
        attrs.setAudioAttributes(audio);
        attrs.setVideoAttributes(video);
        Encoder encoder = new Encoder();
        try {
            encoder.encode(new MultimediaObject(source), target, attrs);
        } catch (IllegalArgumentException | EncoderException e) {
            e.printStackTrace();
            LocalFileErrCodeEnum.E_29511.throwThis(sourcePath, targetPath, e.getMessage());
        }
    }
}
