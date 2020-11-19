package com.boot.business.syscode.model.dto;

import com.boot.business.syscode.model.po.SysCode;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuestionDTO {

    @ApiModelProperty(value = "问题code")
    private String code;

    @ApiModelProperty(value = "问题")
    private String question;

    @ApiModelProperty(value = "答案")
    private String answer;

    @ApiModelProperty(value = "排序")
    private Long sort;

//    @ApiModelProperty(value = "是否启用")
//    private Boolean enabled;

    public static QuestionDTO warp(SysCode sysCode) {
//        return new QuestionDTO(sysCode.getCode(), sysCode.getName(), sysCode.getContent(), sysCode.getSort(), sysCode.getEnabled());
        return new QuestionDTO(sysCode.getCode(), sysCode.getName(), sysCode.getContent(), sysCode.getSort());
    }
}

