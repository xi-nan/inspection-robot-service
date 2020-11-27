package com.boot.commons.localfile.controller;

import cn.hutool.crypto.digest.DigestUtil;
import com.boot.commons.localfile.model.dto.FileExists;
import com.boot.commons.localfile.service.LocalFileService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * LocalFileController
 *
 * @author XINAN
 * @date 2020/11/9
 */

@Api(tags = "通用-文件上传相关")
@RestController
@RequestMapping(value = {"/app/file/upload", "/sys/file/upload"})
public class LocalFileUploadController {

    @Autowired
    private LocalFileService service;

    @ApiOperation("文件上传状态检查")
    @GetMapping(value = "/exist/{md5}")
    public FileExists fileExists(@PathVariable(value = "md5") String md5) {
        return service.fileExists(md5);
    }

    @ApiOperation("分片上传前创建父文件记录，若已存在记录,则会清除已存在的分片数据和文件")
    @PostMapping(value = "/slice/genParent/{md5}")
    public Long createParen(@RequestParam("name") String name,
                            @PathVariable(value = "md5") String md5,
                            @RequestParam("size") Long size,
                            @RequestParam("sliceFileSize") Long sliceFileSize) {
        return service.createParen(name, md5, size, sliceFileSize);
    }

    @ApiOperation("分片文件上传")
    @PostMapping(value = "/slice")
    public void fileSliceUp(@RequestParam("md5") String md5,
                            @RequestParam("file") MultipartFile slice,
                            @RequestParam("index") Integer index,
                            @RequestParam("parentMd5") String parentMd5) throws IOException {
        service.fileSliceUp(md5, slice, index, parentMd5);
    }

    @ApiOperation("分片文件合并")
    @PostMapping(value = "/slice/merge/{md5}")
    public void fileSliceMerge(@PathVariable(value = "md5") String md5) {
        service.fileSliceMerge(md5);
    }

    public static void main(String[] args) {
        System.out.println(DigestUtil.md5Hex(new File("/Users/xinan/tmp/1.mp4")));
    }

    @ApiOperation("普通文件上传")
    @PostMapping(value = "/")
    public Long fileUp(@RequestParam("file") MultipartFile file,
                       @RequestParam("name") String name) throws IOException {
        return service.fileUp(file, name, DigestUtil.md5Hex(file.getInputStream()));
    }

}
