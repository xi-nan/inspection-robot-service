package com.boot.commons.localfile.model.po;

import com.boot.commons.core.model.po.BasePo;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.Table;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * FilePojo
 *
 * @author xinan
 * @date 2020/11/15
 */

@EqualsAndHashCode(callSuper = true)
@Data

@Entity
@Table(appliesTo = "local_file", comment = "本地保存文件信息")
public class LocalFile extends BasePo<LocalFile> {

    @ApiModelProperty(value = "文件名")
    @Column(columnDefinition = "VARCHAR(512) COMMENT '文件名'")
    private String name;

    @ApiModelProperty(value = "文件保存磁盘名称")
    @Column(columnDefinition = "VARCHAR(512) COMMENT '文件保存磁盘名称'")
    private String saveName;

    @ApiModelProperty(value = "文件保存目录")
    @Column(columnDefinition = "VARCHAR(512) COMMENT '文件保存目录'")
    private String saveDir;

    @ApiModelProperty(value = "文件MD5")
    @Column(columnDefinition = "TEXT COMMENT '文件MD5'")
    private String md5;

    @ApiModelProperty(value = "文件大小")
    @Column(columnDefinition = "BIGINT(20) COMMENT '文件大小'")
    private Long size;

    @ApiModelProperty(value = "分片编号,从1开始")
    @Column(columnDefinition = "BIGINT(20) COMMENT '分片编号,从1开始'")
    private Integer sliceIndex;

    @ApiModelProperty(value = "分片文件大小,最后一片允许小于该值")
    @Column(columnDefinition = "BIGINT(20) COMMENT '分片文件大小,最后一片允许小于该值'")
    private Long sliceFileSize;

    @ApiModelProperty(value = "分片总数量")
    @Column(columnDefinition = "BIGINT(20) COMMENT '分片总数量'")
    private Integer sliceAmount;

    @ApiModelProperty(value = "父级ID")
    @Column(columnDefinition = "BIGINT(20) COMMENT '父级ID'")
    private Long parentId;

    @ApiModelProperty(value = "原始文件ID,用于转码等操作后记录原始文件信息")
    @Column(columnDefinition = "BIGINT(20) COMMENT '原始文件ID,用于转码等操作后记录原始文件信息'")
    private Long originalId;

    public void setSaveDir(String saveDir) {
        this.saveDir = saveDir + (!saveDir.endsWith("/") ? "/" : "");
    }
}
