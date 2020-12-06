package com.boot.commons.localfile.service;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.digest.DigestUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.boot.commons.core.exception.enums.ErrCodeEnum;
import com.boot.commons.core.lock.LockAction;
import com.boot.commons.core.properties.SiteProperties;
import com.boot.commons.localfile.mapper.LocalFileMapper;
import com.boot.commons.localfile.model.dto.FileExists;
import com.boot.commons.localfile.model.dto.LocalFileDTO;
import com.boot.commons.localfile.model.enums.LocalFileErrCodeEnum;
import com.boot.commons.localfile.model.po.LocalFile;
import com.boot.commons.localfile.utils.FileUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class LocalFileService extends ServiceImpl<LocalFileMapper, LocalFile> implements LocalFileFacade {

    /**
     * 分片上传前创建父文件记录
     * 若已存在记录,则会清除已存在的分片数据和文件
     *
     * @return
     */
    @LockAction(key = "localFile_#md5")
    public Long createParen(String name, String md5, Long size, Long sliceFileSize) {
        FileExists fileExists = this.fileExists(md5);
        LocalFileErrCodeEnum.E_29505.throwIf(fileExists.getStatus() == 2);
        if (fileExists.getStatus() >= 0) {
            // 删除分片文件数据库记录
            super.remove(Wrappers.<LocalFile>lambdaQuery().eq(LocalFile::getParentId, fileExists.getId()));
            // 删除分片文件存放目录
            FileUtil.del(fileExists.getSaveDir() + "slice_" + md5);
        }
        LocalFile save = new LocalFile();
        save.setId(fileExists.getId());
        save.setName(name);
        save.setMd5(md5);
        save.setSize(size);
        save.setSliceFileSize(sliceFileSize);
        save.setSliceAmount(Math.toIntExact(size / sliceFileSize) + 1);
        save.setSaveDir(SiteProperties._this.getFileUploadDir() + DateUtil.today());
        save.setSaveName(FileUtils.genName(FileUtil.extName(name)));
        super.saveOrUpdate(save);
        return save.getId();
    }

    public FileExists fileExists(String md5) {
        if (StrUtil.isBlank(md5)) {
            return FileExists.nonExistent();
        }
        LocalFile file = super.getOne(Wrappers.<LocalFile>lambdaQuery().eq(LocalFile::getMd5, md5));
        if (file == null) {
            return FileExists.nonExistent();
        }
        if (FileUtil.exist(file.getSaveDir() + file.getSaveName())) {
            return FileExists.exists(file, 2);
        }
        if (file.getSliceAmount() <= 1) {
            // 普通上传的文件
            return FileExists.nonExistent();
        }
        // 分片上传的文件
        // 已上传的分片编号
        List<Integer> hadIndex = super.list(Wrappers.<LocalFile>lambdaQuery().eq(LocalFile::getParentId, file.getId()))
                .stream().filter(it -> FileUtil.exist(it.getSaveDir() + it.getSaveName()))
                .map(LocalFile::getSliceIndex).collect(Collectors.toList());
        if (file.getSliceAmount() > hadIndex.size()) {
            // 完成部分上传
            return FileExists.partExistent(file, hadIndex);
        } else {
            return FileExists.exists(file, 1);
        }

    }

    @LockAction(key = "localFile_#md5")
    @Transactional(rollbackFor = Exception.class)
    public void fileSliceUp(String md5, MultipartFile slice, Integer index, String parentMd5) throws IOException {
        LocalFile file = super.getOne(Wrappers.<LocalFile>lambdaQuery().eq(LocalFile::getMd5, md5));
        if (file != null && FileUtil.exist(file.getSaveDir() + file.getSaveName())) {
            // 该分片已上传并保存
            return;
        }
        LocalFile parentFile = super.getOne(Wrappers.<LocalFile>lambdaQuery().eq(LocalFile::getMd5, parentMd5));
        LocalFileErrCodeEnum.E_29506.throwIf(null == parentFile);
        Integer sliceAmount = parentFile.getSliceAmount();
        LocalFileErrCodeEnum.E_29507.throwIf(index > sliceAmount, "编号最大为 " + sliceAmount);
        long fileSize = slice.getSize();
        Long sliceFileSize = parentFile.getSliceFileSize();
        LocalFileErrCodeEnum.E_29508.throwIf(fileSize > sliceFileSize
                        || (index < sliceAmount && fileSize < sliceFileSize),
                "分片文件大小应为 " + sliceFileSize + "字节，实际接收到的文件大小为" + fileSize + "字节");
        String saveDir = parentFile.getSaveDir() + "slice_" + parentMd5;
        String saveName = parentFile.getSliceFileSize() + ".slice_" + index;
        // 保存分片文件到磁盘
        FileUtils.saveFile(slice, saveDir, saveName);
        // 保存分片文件数据库记录
        LocalFile save = new LocalFile();
        save.setName(saveName);
        save.setMd5(md5);
        save.setSize(fileSize);
        save.setSliceFileSize(fileSize);
        save.setSliceIndex(index);
        save.setSliceAmount(1);
        save.setSaveDir(saveDir);
        save.setSaveName(saveName);
        save.setParentId(parentFile.getId());
        super.save(save);
    }

    @LockAction(key = "localFile_#md5")
    @Transactional(rollbackFor = Exception.class)
    public void fileSliceMerge(String md5) {
        FileExists fileExists = this.fileExists(md5);
        LocalFileErrCodeEnum.E_29509.throwIf(fileExists.getStatus() != 1);
        List<String> sliceFilePaths = super.list(Wrappers.<LocalFile>lambdaQuery().eq(LocalFile::getParentId, fileExists.getId()))
                .stream().map(po -> po.getSaveDir() + po.getSaveName()).collect(Collectors.toList());
        try {
            FileUtils.mergeFile(sliceFilePaths, fileExists.getSaveDir(), fileExists.getSaveName());
            // 删除分片文件数据库记录
            super.remove(Wrappers.<LocalFile>lambdaQuery().eq(LocalFile::getParentId, fileExists.getId()));
            // 删除分片文件存放目录
            FileUtil.del(fileExists.getSaveDir() + "slice_" + md5);
        } catch (IOException e) {
            e.printStackTrace();
            LocalFileErrCodeEnum.E_29510.throwThis(e.getMessage());
        }
    }

    @LockAction(key = "localFile_#md5")
    public Long fileUp(MultipartFile file, String name, String md5) throws IOException {
        FileExists fileExists = this.fileExists(md5);
        if (fileExists.getStatus() == 2) {
            return fileExists.getId();
        }
        String saveDir = SiteProperties._this.getFileUploadDir() + DateUtil.today();
        String saveName = FileUtils.genName(FileUtil.extName(name));
        long fileSize = file.getSize();
        FileUtils.saveFile(file, saveDir, saveName);
        // 保存文件数据库记录
        LocalFile save = new LocalFile();
        save.setName(name);
        save.setMd5(md5);
        save.setSize(fileSize);
        save.setSliceFileSize(fileSize);
        save.setSliceIndex(1);
        save.setSliceAmount(1);
        save.setSaveDir(saveDir);
        save.setSaveName(saveName);
        super.save(save);
        return save.getId();
    }

    @Override
    public File loadFile(Long fileId) {
        LocalFile file = super.getById(fileId);
        LocalFileErrCodeEnum.E_29504.throwIf(null == file || !FileUtil.exist(file.getSaveDir() + file.getSaveName()));
        return new File(file.getSaveDir() + file.getSaveName());
    }

    @Override
    public LocalFileDTO findById(Long fileId) {
        LocalFile localFile = super.getById(fileId);
        if (localFile == null) {
            return null;
        }
        return localFile.warpR(LocalFileDTO.class);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void recodeVideo(Long fileId, Function<Long, Boolean> func) {
        LocalFile newFile = super.lambdaQuery().eq(LocalFile::getOriginalId, fileId).one();
        if (null == newFile) {
            LocalFile file = super.getById(fileId);
            LocalFileErrCodeEnum.E_29504.throwIf(null == file || !FileUtil.exist(file.getSaveDir() + file.getSaveName()));
            // 使用原始文件进行转码
            while (file.getOriginalId() != null) {
                file = super.getById(file.getOriginalId());
            }
            String saveName = FileUtils.genName(FileUtil.extName(file.getName()));
            FileUtils.recode(file.getSaveDir() + file.getSaveName(), file.getSaveDir() + saveName);
            newFile = new LocalFile();
            newFile.setName(file.getName());
            newFile.setSaveName(saveName);
            newFile.setSaveDir(file.getSaveDir());
            newFile.setMd5(DigestUtil.md5Hex(new File(file.getSaveDir() + saveName)));
            newFile.setSize(new File(file.getSaveDir() + saveName).length());
            newFile.setOriginalId(file.getId());
            newFile.insert();
        }
        ErrCodeEnum.E_10021.throwIf(null == newFile.getId() || !func.apply(newFile.getId()));
    }
}
