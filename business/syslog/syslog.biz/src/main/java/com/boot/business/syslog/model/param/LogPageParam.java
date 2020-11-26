package com.boot.business.syslog.model.param;

import com.boot.commons.core.model.param.PageParam;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class LogPageParam extends PageParam {

    @ApiModelProperty(value = "设备ID")
    private Long equipmentId;

    @ApiModelProperty(value = "日志记录时间段搜索 开始")
    private Long logTimeStart;

    @ApiModelProperty(value = "日志记录时间段搜索 结束")
    private Long logTimeEnd;

}

