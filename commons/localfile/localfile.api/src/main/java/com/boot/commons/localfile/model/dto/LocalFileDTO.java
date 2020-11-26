package com.boot.commons.localfile.model.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class LocalFileDTO {

    @ApiModelProperty(value = "文件ID")
    private Long id;

    @ApiModelProperty(value = "文件名")
    private String name;

    @ApiModelProperty(value = "文件保存磁盘名称")
    private String saveName;

    @ApiModelProperty(value = "文件保存目录")
    private String saveDir;

    @ApiModelProperty(value = "文件MD5")
    private String md5;

    @ApiModelProperty(value = "文件大小")
    private Long size;

    @ApiModelProperty(value = "原始文件ID,用于转码等操作后记录原始文件信息")
    private Long originalId;

}
