package com.boot.commons.core.controller;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.boot.commons.core.model.param.PageParam;
import com.boot.commons.core.model.po.BasePo;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * ReadController
 *
 * @author xinan
 * @date 2020/11/20
 */
public class BaseReadController<PO extends BasePo<PO>, DTO, PAGE_PARAM extends PageParam> extends BaseController<PO, DTO> {

    @ApiOperation("获取列表(分页)")
    @PostMapping("/page")
    public IPage<DTO> page(@RequestBody PAGE_PARAM param) {
        return PO().selectPage(param.page(), wrapperPageQuery(param)).convert(it -> it.warpR(dtoClass));
    }

    protected Wrapper<PO> wrapperPageQuery(PAGE_PARAM param) {
        return null;
    }

    @ApiOperation("获取详情 by id")
    @GetMapping("/id/{id}")
    public DTO findById(@PathVariable(value = "id") Long id) {
        return PO().selectById(id).warpR(dtoClass);
    }
}
