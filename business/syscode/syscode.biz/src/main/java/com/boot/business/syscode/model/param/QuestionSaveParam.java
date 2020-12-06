package com.boot.business.syscode.model.param;

import com.boot.business.syscode.model.enums.SysCodeEnum;
import com.boot.business.syscode.model.po.SysCode;
import com.boot.commons.core.model.param.ValidGroup;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class QuestionSaveParam {

    @ApiModelProperty(value = "问题code")
    @NotBlank(message = "修改时code不能为空！", groups = {ValidGroup.upd.class})
    private String code;

    @ApiModelProperty(value = "问题")
    @NotBlank(message = "问题 不能为空！")
    private String question;

    @ApiModelProperty(value = "答案")
    @NotBlank(message = "答案 不能为空！")
    private String answer;

    @ApiModelProperty(value = "排序")
    private Long sort;

//    @ApiModelProperty(value = "是否启用")
//    private Boolean enabled = true;

    public SysCode warp() {
//        return new SysCode(code, question, answer, SysCodeEnum.QUESTIONS.name(), sort, enabled);
        return new SysCode(code, question, answer, SysCodeEnum.QUESTIONS.name(), sort);
    }

}

