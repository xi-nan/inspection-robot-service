package com.boot.business.historicaldata.model.po;

import com.boot.commons.core.model.po.BasePo;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.Index;
import org.hibernate.annotations.Table;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * @author XINAN
 */

@EqualsAndHashCode(callSuper = true)
@Data

@Entity
@Table(appliesTo = "inspection_photo", comment = "巡检照片记录", indexes = {
        @Index(name = "IX_InspectionPhoto_deleted", columnNames = {"deleted"})
})
public class InspectionPhoto extends BasePo<InspectionPhoto> {

    @ApiModelProperty(value = "巡检编号")
    @Column(columnDefinition = "BIGINT(20) COMMENT '巡检编号'")
    private Long inspectionId;

    @ApiModelProperty(value = "照片文件ID")
    @Column(columnDefinition = "BIGINT(20) COMMENT '照片文件ID'")
    private Long fileId;

}
