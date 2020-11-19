package com.boot.business.syscode.model.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author XINAN
 */

@Data
public class SysCodeDTO {

    @ApiModelProperty(value = "code")
    private String code;

    @ApiModelProperty(value = "名称")
    private String name;

    @ApiModelProperty(value = "内容")
    private String content;

}
