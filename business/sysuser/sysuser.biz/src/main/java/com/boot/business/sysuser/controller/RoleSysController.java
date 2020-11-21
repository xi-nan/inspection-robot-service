package com.boot.business.sysuser.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.boot.business.sysuser.model.dto.RoleDTO;
import com.boot.business.sysuser.model.param.RolePageParam;
import com.boot.business.sysuser.model.param.RoleSaveParam;
import com.boot.business.sysuser.model.po.SysRole;
import com.boot.business.sysuser.service.ISysRoleService;
import com.boot.commons.core.exception.enums.ErrCodeEnum;
import com.boot.commons.core.model.param.ValidGroup;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Api(tags = "后台-角色")
@RestController
@RequestMapping(value = "/sys/role")
public class RoleSysController {

    @Autowired
    private ISysRoleService roleService;

    @ApiOperation("获取列表")
    @PostMapping("/list")
    // @PreAuthorize("hasAnyAuthority('sys:role:list','sys:admin:edit')")
    public IPage<RoleDTO> list(@Valid @RequestBody RolePageParam param) {
        return roleService.page(param.page(), Wrappers.<SysRole>lambdaQuery()
                .like(StringUtils.isNotBlank(param.getNameLike()), SysRole::getRoleName, param.getNameLike()))
                .convert(it -> it.warpR(RoleDTO.class));
    }

    @ApiOperation("获取详情")
    @GetMapping("/{id}")
    // @PreAuthorize("hasAnyAuthority('sys:role:list','sys:admin:edit')")
    public RoleDTO getById(@PathVariable("id") Long roleId) {
        return roleService.getDTOById(roleId);
    }

    @ApiOperation("添加角色")
    @PostMapping("/add")
    // @PreAuthorize("hasAuthority('sys:role:edit')")
    public void add(@RequestBody @Validated(value = ValidGroup.add.class) RoleSaveParam param) {
        ErrCodeEnum.E_10021.throwIf(!roleService.save(param), "角色添加失败!");
    }

    @ApiOperation("编辑角色")
    @PostMapping("/update")
    // @PreAuthorize("hasAuthority('sys:role:edit')")
    public void update(@Validated(value = ValidGroup.upd.class)@RequestBody RoleSaveParam param) {
        ErrCodeEnum.E_10021.throwIf(!roleService.update(param));
    }

    @ApiOperation("删除角色")
    @DeleteMapping("/delete/{id}")
    // @PreAuthorize("hasAuthority('sys:role:del')")
    public void delete(@PathVariable("id") Long roleId) {
        ErrCodeEnum.E_10022.throwIf(!roleService.deleteRole(roleId));
    }

}


