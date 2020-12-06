package com.boot.business.historicaldata.model.po;

import com.boot.business.historicaldata.model.enums.TrajectoryType;
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
@Table(appliesTo = "inspection_trajectory", comment = "巡检轨迹记录", indexes = {
        @Index(name = "IX_InspectionTrajectory_deleted", columnNames = {"deleted"})
})
public class InspectionTrajectory extends BasePo<InspectionTrajectory> {

    @ApiModelProperty(value = "巡检编号")
    @Column(columnDefinition = "BIGINT(20) COMMENT '巡检编号'")
    private Long inspectionId;

    @ApiModelProperty(value = "轨迹类型")
    @Column(columnDefinition = "VARCHAR(512) COMMENT '轨迹类型'")
    private TrajectoryType trajectoryType;

    @ApiModelProperty(value = "轨迹文件ID")
    @Column(columnDefinition = "BIGINT(20) COMMENT '照片文件ID'")
    private Long fileId;


}
