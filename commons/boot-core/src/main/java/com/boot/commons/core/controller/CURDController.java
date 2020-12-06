package com.boot.commons.core.controller;

import com.boot.commons.core.exception.enums.ErrCodeEnum;
import com.boot.commons.core.model.param.ValidGroup;
import com.boot.commons.core.model.po.BasePo;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * CURDController
 *
 * @author xinan
 * @date 2020/11/20
 */
public class CURDController<PO extends BasePo<PO>, DTO, SAVEPARAM> extends ReadController<PO, DTO> {

    @ApiOperation("添加")
    @PostMapping("/add")
    public void add(@Validated(value = ValidGroup.add.class) @RequestBody SAVEPARAM param) {
        ErrCodeEnum.E_10021.throwIf(!newPo().warpT(param).insert());
    }

    @ApiOperation("修改保存")
    @PostMapping("/save")
    public void upd(@Validated(value = ValidGroup.upd.class) @RequestBody SAVEPARAM param) {
        ErrCodeEnum.E_10021.throwIf(!newPo().warpT(param).updateById());
    }

    @ApiOperation("删除 by id")
    @DeleteMapping("/id/{id}")
    public Boolean delById(@PathVariable(value = "id") Long id) {
        return newPo().deleteById(id);
    }


}
