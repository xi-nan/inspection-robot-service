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
@Table(appliesTo = "history_inspection", comment = "历史巡检", indexes = {
        @Index(name = "IX_HistoryInspection_deleted", columnNames = {"deleted"})
})
public class HistoryInspection extends BasePo<HistoryInspection> {

    @ApiModelProperty(value = "设备ID")
    @Column(columnDefinition = "BIGINT(20) COMMENT '设备ID'")
    private Long equipmentId;

    @ApiModelProperty(value = "巡检开始时间")
    @Column(columnDefinition = "BIGINT(13) COMMENT '巡检开始时间'")
    private Long startTime;

    @ApiModelProperty(value = "巡检结束时间")
    @Column(columnDefinition = "BIGINT(13) COMMENT '巡检结束时间'")
    private Long endTime;

}
