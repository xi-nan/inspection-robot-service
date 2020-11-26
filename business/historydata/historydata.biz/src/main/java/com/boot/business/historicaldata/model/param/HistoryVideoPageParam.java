package com.boot.business.historicaldata.model.param;

import com.boot.commons.core.model.param.PageParam;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class HistoryVideoPageParam extends PageParam {

    @ApiModelProperty(value = "设备ID")
    private Long equipmentId;

    @ApiModelProperty(value = "视频名称搜索")
    private String videoNameLike;

    @ApiModelProperty(value = "上传时间段搜索 开始")
    private Long upTimeStart;

    @ApiModelProperty(value = "上传时间段搜索 结束")
    private Long upTimeEnd;

}

