package com.boot.commons.core.controller;

import cn.hutool.core.util.ReflectUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.boot.commons.core.model.param.PageParam;
import com.boot.commons.core.model.po.BasePo;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.lang.reflect.ParameterizedType;

/**
 * ReadController
 *
 * @author xinan
 * @date 2020/11/20
 */
public class ReadController<PO extends BasePo<PO>, DTO> {

    protected Class<PO> poClass;
    protected Class<DTO> dtoClass;

    @SuppressWarnings("unchecked")
    public ReadController() {
        poClass = (Class<PO>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        dtoClass = (Class<DTO>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[1];
    }

    @ApiOperation("获取列表(分页)")
    @PostMapping("/page")
    public IPage<DTO> findVoteNewsByPage(@RequestBody PageParam param) {
        return newPo().selectPage(param.page(), null).convert(it -> it.warpR(dtoClass));
    }

    protected PO newPo() {
        return ReflectUtil.newInstance(poClass);
    }

    @ApiOperation("获取详情 by id")
    @GetMapping("/id/{id}")
    public DTO findById(@PathVariable(value = "id") Long id) {
        return newPo().selectById(id).warpR(dtoClass);
    }
}
