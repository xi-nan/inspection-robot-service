package com.boot.commons.localfile.model.dto;

import com.boot.commons.localfile.model.po.LocalFile;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * FileExists
 *
 * @author xinan
 * @date 2020/11/15
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FileExists {

    @ApiModelProperty(value = "文件ID")
    private Long id;

    @ApiModelProperty(value = "文件名")
    private String name;

    @ApiModelProperty(value = "文件状态:-1: 不存在;0: 部分已上传,1: 分片已全部上传，但还未合并,2: 已存在")
    private Integer status;

    @ApiModelProperty(value = "已上传的文件分片索引")
    private List<Integer> sliceIndex;

    @ApiModelProperty(value = "分片文件大小,最后一片允许小于该值")
    private Long sliceFileSize;

    @ApiModelProperty(value = "分片总数量")
    private Integer sliceAmount = 1;

    @ApiModelProperty(value = "文件保存目录")
    private String saveDir;

    @ApiModelProperty(value = "文件保存磁盘名称")
    private String saveName;

    @ApiModelProperty(value = "原始文件ID,用于转码等操作后记录原始文件信息")
    private Long originalId;

    public static FileExists nonExistent() {
        FileExists fileExists = new FileExists();
        fileExists.setStatus(-1);
        return fileExists;
    }

    public static FileExists exists(LocalFile file, Integer status) {
        FileExists fileExists = new FileExists();
        fileExists.setId(file.getId());
        fileExists.setStatus(status);
        fileExists.setName(file.getName());
        fileExists.setSliceFileSize(file.getSliceFileSize());
        fileExists.setSliceAmount(file.getSliceAmount());
        fileExists.setSaveDir(file.getSaveDir());
        fileExists.setSaveName(file.getSaveName());
        return fileExists;
    }

    public static FileExists partExistent(LocalFile file, List<Integer> sliceIndex) {
        FileExists fileExists = new FileExists();
        fileExists.setId(file.getId());
        fileExists.setStatus(0);
        fileExists.setSliceIndex(sliceIndex);
        fileExists.setName(file.getName());
        fileExists.setSliceFileSize(file.getSliceFileSize());
        fileExists.setSliceAmount(file.getSliceAmount());
        fileExists.setSaveDir(file.getSaveDir());
        fileExists.setSaveName(file.getSaveName());
        return fileExists;
    }
}
