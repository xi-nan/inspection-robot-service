package com.boot.commons.localfile.controller;

import com.boot.commons.core.response.NotResponseBody;
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

/**
 * LocalFileController
 *
 * @author XINAN
 * @date 2020/11/9
 */

@Api(tags = "后台-文件下载相关")
@RestController
@RequestMapping(value = "/sys/file/download")
public class LocalFileDownloadController {

    @Autowired
    HttpServletRequest request;
    @Autowired
    HttpServletResponse response;
    @Autowired
    private LocalFileService service;

    @NotResponseBody
    @ApiOperation("普通文件下载")
    @GetMapping(value = "/{fileId}/public")
    public void download(@PathVariable(value = "fileId") Long fileId) throws UnsupportedEncodingException {
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

    @NotResponseBody
    @ApiOperation("视频播放，支持断点续传拖动播放")
    @GetMapping(value = "/video/{fileId}/public")
    public void player2(@PathVariable(value = "fileId") Long fileId) {
        File file = service.loadFile(fileId);

        response.setContentType("application/octet-stream");
        String fileName = file.getName();
        // 设置文件名
        response.addHeader("Content-Disposition", "attachment;filename=" + fileName);

        long p = 0L;
        long toLength = 0L;
        long contentLength = 0L;
        // 0,从头开始的全文下载；1,从某字节开始的下载（bytes=27000-）；2,从某字节开始到某字节结束的下载（bytes=27000-39000）
        int rangeSwitch = 0;
        long fileLength;
        String rangBytes = "";
        fileLength = file.length();

        // tell the client to allow accept-ranges
        response.reset();
        response.setHeader("Accept-Ranges", "bytes");

        // client requests a file block download start byte
        String range = request.getHeader("Range");
        if (range != null && range.trim().length() > 0 && !"null".equals(range)) {
            response.setStatus(javax.servlet.http.HttpServletResponse.SC_PARTIAL_CONTENT);
            rangBytes = range.replaceAll("bytes=", "");
            if (rangBytes.endsWith("-")) {
                // bytes=270000-
                rangeSwitch = 1;
                p = Long.parseLong(rangBytes.substring(0, rangBytes.indexOf("-")));
                // 客户端请求的是270000之后的字节（包括bytes下标索引为270000的字节）
                contentLength = fileLength - p;
            } else {
                // bytes=270000-320000
                rangeSwitch = 2;
                String temp1 = rangBytes.substring(0, rangBytes.indexOf("-"));
                String temp2 = rangBytes.substring(rangBytes.indexOf("-") + 1);
                p = Long.parseLong(temp1);
                toLength = Long.parseLong(temp2);
                // 客户端请求的是 270000-320000 之间的字节
                contentLength = toLength - p + 1;
            }
        } else {
            contentLength = fileLength;
        }

        // 如果设设置了Content-Length，则客户端会自动进行多线程下载。如果不希望支持多线程，则不要设置这个参数。
        // Content-Length: [文件的总大小] - [客户端请求的下载的文件块的开始字节]
        // response.setHeader("Content-Length", Long.toString(contentLength));

        try (FileInputStream fis = new FileInputStream(file);
             BufferedInputStream bis = new BufferedInputStream(fis);
             OutputStream os = response.getOutputStream()) {
            // 断点开始
            // 响应的格式是:
            // Content-Range: bytes [文件块的开始字节]-[文件的总大小 - 1]/[文件的总大小]
            if (rangeSwitch == 1) {
                String contentRange = "bytes " + p + "-" + (fileLength - 1) + "/" + fileLength;
                response.setHeader("Content-Range", contentRange);
                bis.skip(p);
            } else if (rangeSwitch == 2) {
                String contentRange = range.replace("=", " ") + "/" + fileLength;
                response.setHeader("Content-Range", contentRange);
                bis.skip(p);
            } else {
                String contentRange = "bytes " + "0-" + (fileLength - 1) + "/" + fileLength;
                response.setHeader("Content-Range", contentRange);
            }

            int n;
            long readLength = 0;
            int bsize = 1024;
            byte[] bytes = new byte[bsize];
            if (rangeSwitch == 2) {
                // 针对 bytes=27000-39000 的请求，从27000开始写数据
                while (readLength <= contentLength - bsize) {
                    n = bis.read(bytes);
                    readLength += n;
                    os.write(bytes, 0, n);
                }
                if (readLength <= contentLength) {
                    n = bis.read(bytes, 0, (int) (contentLength - readLength));
                    os.write(bytes, 0, n);
                }
            } else {
                while ((n = bis.read(bytes)) != -1) {
                    os.write(bytes, 0, n);
                }
            }
            os.flush();
        } catch (Exception e) {
            LocalFileErrCodeEnum.E_29503.throwThis(e.toString());
        }
    }

}
