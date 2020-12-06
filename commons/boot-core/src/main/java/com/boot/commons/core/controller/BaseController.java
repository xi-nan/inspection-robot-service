package com.boot.commons.core.controller;

import cn.hutool.core.util.ReflectUtil;
import com.boot.commons.core.model.po.BasePo;

import java.lang.reflect.ParameterizedType;

/**
 * ReadController
 *
 * @author xinan
 * @date 2020/11/20
 */
public class BaseController<PO extends BasePo<PO>, DTO> {

    protected Class<PO> poClass;
    protected Class<DTO> dtoClass;

    @SuppressWarnings("unchecked")
    public BaseController() {
        poClass = (Class<PO>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        dtoClass = (Class<DTO>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[1];
    }

    protected PO PO() {
        return ReflectUtil.newInstance(poClass);
    }

}
