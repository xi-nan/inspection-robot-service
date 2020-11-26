package com.boot.commons.localfile.controller;

import com.boot.commons.core.response.NotResponseBody;
import com.boot.commons.localfile.model.dto.LocalFileDTO;
import com.boot.commons.localfile.model.enums.LocalFileErrCodeEnum;
import com.boot.commons.localfile.service.LocalFileService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

/**
 * LocalFileController
 *
 * @author XINAN
 * @date 2020/11/9
 */

@Api(tags = "通用-文件下载相关")
@RestController
@RequestMapping(value = {"/app/file/download", "/sys/file/download"})
public class LocalFileDownloadController {

    @Autowired
    private LocalFileService service;

    @NotResponseBody
    @ApiOperation("获取文件信息")
    @GetMapping(value = "/info/{fileId}/public")
    public LocalFileDTO info(@PathVariable(value = "fileId") Long fileId) {
        return service.findById(fileId);
    }

    @NotResponseBody
    @ApiOperation("普通文件下载")
    @GetMapping(value = "/{fileId}/public")
    public void download(@PathVariable(value = "fileId") Long fileId, HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        File file = service.loadFile(fileId);
        // 配置文件下载
        response.setHeader("content-type", "application/octet-stream");
        response.setContentType("application/octet-stream");
        // 下载文件能正常显示中文
        response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(file.getName(), "UTF-8"));
        // 实现文件下载
        byte[] buffer = new byte[1024];
        try (FileInputStream fis = new FileInputStream(file);
             BufferedInputStream bis = new BufferedInputStream(fis);
             OutputStream os = response.getOutputStream()) {
            int i = bis.read(buffer);
            while (i != -1) {
                os.write(buffer, 0, i);
                i = bis.read(buffer);
            }
            os.flush();
        } catch (Exception e) {
            LocalFileErrCodeEnum.E_29503.throwThis(e.toString());
        }
    }

    @ApiOperation("视频播放，支持断点续传拖动播放")
    @GetMapping(value = "/video/{fileId}/public")
    public void download2(@PathVariable Long fileId, HttpServletRequest request, HttpServletResponse response) {
        String range = request.getHeader("Range");
        // StaticLog.info("current request rang:" + range);
        File file = service.loadFile(fileId);
        //开始下载位置
        long startByte = 0;
        //结束下载位置
        long endByte = file.length() - 1;
        // StaticLog.debug("文件开始位置：{}，文件结束位置：{}，文件总长度：{}", startByte, endByte, file.length());
        if (range != null && range.contains("bytes=") && range.contains("-")) {
            range = range.substring(range.lastIndexOf("=") + 1).trim();
            String[] ranges = range.split("-");
            try {
                //判断range的类型
                if (ranges.length == 1) {
                    //类型一：bytes=-2343
                    if (range.startsWith("-")) {
                        endByte = Long.parseLong(ranges[0]);
                    }
                    //类型二：bytes=2343-
                    else if (range.endsWith("-")) {
                        startByte = Long.parseLong(ranges[0]);
                    }
                } else if (ranges.length == 2) {
                    //类型三：bytes=22-2343
                    startByte = Long.parseLong(ranges[0]);
                    endByte = Long.parseLong(ranges[1]);
                }
            } catch (NumberFormatException e) {
                startByte = 0;
                endByte = file.length() - 1;
                // StaticLog.error("Range Occur Error, Message:{}", e.getLocalizedMessage());
            }
            //要下载的长度
            long contentLength = endByte - startByte + 1;
            //文件名
            String fileName = file.getName();
            //文件类型
            String contentType = request.getServletContext().getMimeType(fileName);
            // 解决下载文件时文件名乱码问题
            byte[] fileNameBytes = fileName.getBytes(StandardCharsets.UTF_8);
            fileName = new String(fileNameBytes, 0, fileNameBytes.length, StandardCharsets.ISO_8859_1);
            //各种响应头设置
            //支持断点续传，获取部分字节内容：
            response.setHeader("Accept-Ranges", "bytes");
            //http状态码要为206：表示获取部分内容
            response.setStatus(HttpServletResponse.SC_PARTIAL_CONTENT);
            response.setContentType(contentType);
            response.setHeader("Content-Type", contentType);
            //inline表示浏览器直接使用，attachment表示下载，fileName表示下载的文件名
            response.setHeader("Content-Disposition", "inline;filename=" + fileName);
            response.setHeader("Content-Length", String.valueOf(contentLength));
            // Content-Range，格式为：[要下载的开始位置]-[结束位置]/[文件总大小]
            response.setHeader("Content-Range", "bytes " + startByte + "-" + endByte + "/" + file.length());

            BufferedOutputStream outputStream = null;
            RandomAccessFile randomAccessFile = null;
            //已传送数据大小
            long transmitted = 0;
            try {
                randomAccessFile = new RandomAccessFile(file, "r");
                outputStream = new BufferedOutputStream(response.getOutputStream());
                byte[] buff = new byte[4096];
                int len = 0;
                randomAccessFile.seek(startByte);
                //坑爹地方四：判断是否到了最后不足4096（buff的length）个byte这个逻辑（(transmitted + len) <= contentLength）要放前面！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！
                //不然会会先读取randomAccessFile，造成后面读取位置出错，找了一天才发现问题所在
                //此处的原作者意思逻辑就是  (len = randomAccessFile.read(buff)) 每次读取4096个字节 eg 文件剩余2000 读4096 意味着 有2096
                //是空的  那么前端解析的时候就会出错  所以此处作者加了(transmitted + len) <= contentLength
                //条件判断
                while ((transmitted + len) <= contentLength && (len = randomAccessFile.read(buff)) != -1) {
                    outputStream.write(buff, 0, len);
                    transmitted += len;
                }
                //处理不足buff.length部分
                if (transmitted < contentLength) {
                    len = randomAccessFile.read(buff, 0, (int) (contentLength - transmitted));
                    outputStream.write(buff, 0, len);
                    transmitted += len;
                }

                outputStream.flush();
                response.flushBuffer();
                randomAccessFile.close();
                // StaticLog.debug("下载完毕：" + startByte + "-" + endByte + "：" + transmitted);
                //捕获此异常表示拥护停止下载
            } catch (IOException e) {
                // e.printStackTrace();
                // StaticLog.debug("用户停止下载：" + startByte + "-" + endByte + "：" + transmitted);
            } finally {
                try {
                    if (randomAccessFile != null) {
                        randomAccessFile.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }///end try
        }
    }


}
