package com.boot.business.syscode.model.po;

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
@Table(appliesTo = "inspection_track", comment = "巡检轨迹", indexes = {
        @Index(name = "IX_InspectionTrack_deleted", columnNames = {"deleted"})
})
public class InspectionTrack extends BasePo<InspectionTrack> {

    @ApiModelProperty(value = "轨迹名称")
    @Column(columnDefinition = "VARCHAR(512) COMMENT '轨迹名称'")
    private String trackName;

    @ApiModelProperty(value = "坐标数据")
    @Column(columnDefinition = "text COMMENT '坐标数据'")
    private String coordinateData;

    @ApiModelProperty(value = "RFID数据")
    @Column(columnDefinition = "text COMMENT 'RFID数据'")
    private String rfidData;

}
