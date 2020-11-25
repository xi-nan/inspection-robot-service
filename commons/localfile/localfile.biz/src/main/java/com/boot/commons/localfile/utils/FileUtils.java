package com.boot.commons.localfile.utils;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.IdUtil;
import com.boot.commons.core.properties.SiteProperties;
import com.boot.commons.localfile.model.enums.LocalFileErrCodeEnum;
import org.bytedeco.ffmpeg.avcodec.AVPacket;
import org.bytedeco.ffmpeg.global.avcodec;
import org.bytedeco.ffmpeg.global.avutil;
import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.bytedeco.javacv.FFmpegFrameRecorder;
import org.bytedeco.javacv.FrameGrabber;
import org.bytedeco.javacv.FrameRecorder;
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

    public static void saveFile(MultipartFile source, String saveDir, String saveName) throws IOException {
        FileUtil.mkdir(saveDir);
        File saveFilePath = new File(saveDir, saveName);
        source.transferTo(saveFilePath);
    }

    public static void mergeFile(List<String> sliceFilePaths, String saveDir, String saveName) throws IOException {
        FileUtil.mkdir(saveDir);
        String originalFileSaveDir = saveDir + "original/";
        FileUtil.mkdir(originalFileSaveDir);
        File originalFile = new File(originalFileSaveDir, saveName);
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
        if (originalFile.exists() && "mp4".equals(FileUtil.extName(saveName).toLowerCase())) {
            recode(originalFile.getAbsolutePath(), saveDir + saveName);
        }
    }

    /**
     * 视频转码
     */
    public static void recode(String filePath, String newFilePath) {
        // String filePath = saveDir+saveName;
        // String ext = filePath.substring(filePath.lastIndexOf("."));
        // String newFilePath = filePath.replace(ext, ".mp4");
        try {
            FFmpegFrameGrabber grabber = new FFmpegFrameGrabber(filePath);
            grabber.start();
            FFmpegFrameRecorder recorder = new FFmpegFrameRecorder(newFilePath, grabber.getImageWidth(),
                    grabber.getImageHeight(), grabber.getVideoBitrate());
            recorder.setVideoCodec(avcodec.AV_CODEC_ID_H264);
            recorder.setFormat("mp4");
            recorder.setPixelFormat(avutil.AV_PIX_FMT_YUVJ420P);
            recorder.setFrameRate(grabber.getFrameRate());
            int bitrate = grabber.getVideoBitrate();
            if (bitrate == 0) {
                bitrate = grabber.getAudioBitrate();
            }
            recorder.setVideoBitrate(bitrate);
            recorder.start(grabber.getFormatContext());
            AVPacket packet;
            long dts = 0;
            while ((packet = grabber.grabPacket()) != null) {
                long currentDts = packet.dts();
                if (currentDts >= dts) {
                    recorder.recordPacket(packet);
                }
                dts = currentDts;
            }
            recorder.stop();
            recorder.release();
            grabber.stop();
        } catch (FrameGrabber.Exception | FrameRecorder.Exception e) {
            LocalFileErrCodeEnum.E_29511.throwThis(e.getMessage());
        }
    }
}
