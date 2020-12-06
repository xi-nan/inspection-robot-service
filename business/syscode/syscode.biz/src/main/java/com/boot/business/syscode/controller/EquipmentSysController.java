package com.boot.business.syscode.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.boot.business.appuser.service.AppUserFacade;
import com.boot.business.syscode.model.dto.EquipmentDTO;
import com.boot.business.syscode.model.param.EquipmentPageParam;
import com.boot.business.syscode.model.param.EquipmentSaveParam;
import com.boot.business.syscode.model.po.Equipment;
import com.boot.business.syscode.service.EquipmentService;
import com.boot.commons.core.exception.enums.ErrCodeEnum;
import com.boot.commons.core.model.param.ValidGroup;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Api(tags = "后台-设备管理")
@RestController
@RequestMapping(value = "/sys/equipment")
public class EquipmentSysController {

    @Autowired
    private EquipmentService service;

    @Autowired
    private AppUserFacade appUserFacade;

    @ApiOperation("添加设备")
    @PostMapping("/add")
    public void add(@RequestBody EquipmentSaveParam param) {
        service.saveOrUpdate(new Equipment().warpT(param));
    }

    @ApiOperation("修改设备")
    @PostMapping("/edit")
    public void upd(@RequestBody @Validated(value = ValidGroup.upd.class) EquipmentSaveParam param) {
        service.saveOrUpdate(new Equipment().warpT(param));
    }

    @ApiOperation("删除设备")
    @DeleteMapping("/{id}")
    public void delByIds(@PathVariable Long id) {
        ErrCodeEnum.E_10022.throwIf(!service.removeById(id));
    }

    @GetMapping("/details/{id}")
    @ApiOperation("查看详情")
    public EquipmentDTO getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @ApiOperation("获取设备列表(分页)")
    @PostMapping("/page")
    public IPage<EquipmentDTO> page(@RequestBody EquipmentPageParam param) {
        Long[] users = null;
        if (StrUtil.isNotBlank(param.getUserSearch())) {
            users = appUserFacade.searchUser(param.getUserSearch());
        }
        return service.lambdaQuery()
                .like(param.getId() != null, Equipment::getId, param.getId())
                .in(users != null, Equipment::getUserId, users)
                .page(param.page()).convert(service::warpDTO);
    }

}
