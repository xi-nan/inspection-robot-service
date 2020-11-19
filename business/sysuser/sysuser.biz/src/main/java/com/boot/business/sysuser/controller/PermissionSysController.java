package com.boot.business.sysuser.controller;

import com.boot.business.sysuser.model.dto.PermissionDTO;
import com.boot.business.sysuser.model.param.PermissionSaveParam;
import com.boot.business.sysuser.model.po.SysPermission;
import com.boot.business.sysuser.service.ISysPermissionService;
import com.boot.commons.core.exception.enums.ErrCodeEnum;
import com.boot.commons.core.model.param.ValidGroup;
import com.boot.commons.core.security.LoginUserUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Set;


@Api(tags = "后台-权限")
@RestController
@RequestMapping(value = "/sys/permission")
public class PermissionSysController {

    @Autowired
    private ISysPermissionService permissionService;

    @GetMapping("/list/all")
    @ApiOperation("获取权限列表")
    public List<PermissionDTO> list() {
        return permissionService.getPermissionList(null);
    }

    @GetMapping("/list/self")
    @ApiOperation("查询自己拥有的权限")
    public List<PermissionDTO> getMenu() {
        return permissionService.getPermissionList(LoginUserUtil.getLoginUserId());
    }

    @PostMapping("/add")
    @ApiOperation("【添加】权限菜单")
    // @PreAuthorize("hasAuthority('sys:permission:edit')")
    public void add(@RequestBody @Validated(value = ValidGroup.add.class) PermissionSaveParam param) {
        ErrCodeEnum.E_10021.throwIf(!permissionService.save(new SysPermission().warpT(param)));
    }

    @PostMapping("/edit")
    @ApiOperation("【编辑】权限菜单")
    // @PreAuthorize("hasAuthority('sys:permission:edit')")
    public void edit(@RequestBody @Validated(value = ValidGroup.upd.class) PermissionSaveParam param) {
        ErrCodeEnum.E_10021.throwIf(!permissionService.updateById(new SysPermission().warpT(param)));
    }

    @DeleteMapping("/{ids}")
    @ApiOperation("【删除】权限菜单")
    // @PreAuthorize("hasAuthority('sys:permission:del')")
    public void delByIds(@PathVariable String[] ids) {
        ErrCodeEnum.E_10021.throwIf(!permissionService.removeByIds(Arrays.asList(ids)));
    }

    @GetMapping("/getMenuResource")
    @ApiOperation("获取资源码列表")
    public Set<String> getMenuResource() {
        return permissionService.getMenuResource(LoginUserUtil.getLoginUserId());
    }
}

