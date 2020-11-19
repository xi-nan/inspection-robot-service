package com.boot.business.syscode.model.param;

import com.boot.commons.core.model.param.PageParam;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class QuestionPageParam extends PageParam {

    @ApiModelProperty(value = "关键词查找")
    private String searchKey;

}

