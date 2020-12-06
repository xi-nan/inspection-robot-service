package com.boot.commons.localfile.utils;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.IdUtil;
import com.boot.commons.core.properties.SiteProperties;
import com.boot.commons.localfile.model.enums.LocalFileErrCodeEnum;
import org.springframework.web.multipart.MultipartFile;
import ws.schild.jave.Encoder;
import ws.schild.jave.EncoderException;
import ws.schild.jave.MultimediaObject;
import ws.schild.jave.encode.AudioAttributes;
import ws.schild.jave.encode.EncodingAttributes;
import ws.schild.jave.encode.VideoAttributes;
import ws.schild.jave.info.AudioInfo;
import ws.schild.jave.info.MultimediaInfo;
import ws.schild.jave.info.VideoInfo;

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
        recode("/Users/xinan/tmp/1331983697628364800.mp4", "/Users/xinan/tmp/1331983697628364800-1.mp4");
        // try {
        //     DefaultFFMPEGLocator locator = new DefaultFFMPEGLocator();
        //     System.out.println("ffmpeg executable found in <" + locator.getExecutablePath() + ">");
        //     ProcessWrapper executor = locator.createExecutor();
        //     executor.addArgument(" -i /Users/xinan/tmp/1334509718286106624.m4v");
        //     executor.addArgument(" -vcodec copy");
        //     executor.addArgument(" /Users/xinan/tmp/1334509718286106624-1.mp4");
        //     executor.execute();
        //     executor.close();
        // } catch (IOException e) {
        //     e.printStackTrace();
        // }
    }

    public static void recode(String sourcePath, String targetPath) {
        try {
            MultimediaObject source = new MultimediaObject(new File(sourcePath));
            MultimediaInfo sourceInfo = source.getInfo();
            AudioInfo audioInfo = sourceInfo.getAudio();
            VideoInfo videoInfo = sourceInfo.getVideo();
            File target = new File(targetPath);
            AudioAttributes audio = null;
            if (audioInfo != null) {
                audio = new AudioAttributes();
                audio.setCodec("ac3");//aac
                //比特率是指每秒传送的比特(bit)数。单位为 bps(Bit Per Second)，比特率越高，传送数据速度越快
                audio.setBitRate(audioInfo.getBitRate());//设置比特率
                audio.setChannels(audioInfo.getChannels());//设置声音频道
                audio.setSamplingRate(audioInfo.getSamplingRate());//设置节录率
            }
            VideoAttributes video = new VideoAttributes();
            video.setCodec("copy");
            //比特率是指每秒传送的比特(bit)数。单位为 bps(Bit Per Second)，比特率越高，传送数据速度越快
            video.setBitRate(videoInfo.getBitRate() <= 0 ? 512 * 1024 : videoInfo.getBitRate());
            //设置帧率,越大越流畅，越小越卡
            video.setFrameRate(Float.valueOf(videoInfo.getFrameRate()).intValue());
            EncodingAttributes attrs = new EncodingAttributes();
            attrs.setOutputFormat("mp4");
            attrs.setAudioAttributes(audio);
            attrs.setVideoAttributes(video);
            Encoder encoder = new Encoder();
            encoder.encode(source, target, attrs);
        } catch (IllegalArgumentException | EncoderException e) {
            e.printStackTrace();
            LocalFileErrCodeEnum.E_29511.throwThis(sourcePath, targetPath, e.getMessage());
        }
    }
}
