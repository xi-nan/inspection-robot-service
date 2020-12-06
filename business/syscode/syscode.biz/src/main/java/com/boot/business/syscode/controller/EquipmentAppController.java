package com.boot.business.syscode.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.boot.business.appuser.service.AppUserFacade;
import com.boot.business.syscode.model.dto.EquipmentDTO;
import com.boot.business.syscode.model.param.EquipmentPageParam;
import com.boot.business.syscode.model.po.Equipment;
import com.boot.business.syscode.service.EquipmentService;
import com.boot.commons.core.security.LoginUserUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Api(tags = "客户端-设备管理")
@RestController
@RequestMapping(value = "/app/equipment")
public class EquipmentAppController {

    @Autowired
    private EquipmentService service;

    @Autowired
    private AppUserFacade appUserFacade;

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

    @ApiOperation("获取当前账号绑定的设备列表")
    @GetMapping("/list/own")
    public List<EquipmentDTO> own() {
        return service.lambdaQuery().eq(Equipment::getUserId, LoginUserUtil.getLoginUserId())
                .list().stream().map(service::warpDTO).collect(Collectors.toList());
    }

}
