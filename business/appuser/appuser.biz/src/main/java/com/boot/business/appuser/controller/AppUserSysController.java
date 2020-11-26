package com.boot.business.appuser.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.boot.business.appuser.model.dto.AppUserDTO;
import com.boot.business.appuser.model.param.AppUserPageParam;
import com.boot.business.appuser.model.param.AppUserSaveParam;
import com.boot.business.appuser.model.po.AppUser;
import com.boot.business.appuser.service.IAppUserService;
import com.boot.business.syslog.model.enums.LogSysUserOperationType;
import com.boot.business.syslog.model.param.LogSysUserSaveParam;
import com.boot.business.syslog.service.LogSysUserFacade;
import com.boot.commons.core.exception.enums.ErrCodeEnum;
import com.boot.commons.core.model.param.ValidGroup;
import com.boot.commons.core.security.LoginUserUtil;
import com.boot.commons.core.security.UserType;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Api(tags = "后台-APP用户")
@RestController
@RequestMapping(value = "/sys/app/user")
public class AppUserSysController {

    @Autowired
    private IAppUserService service;

    @Autowired
    private LogSysUserFacade logSysUserFacade;

    @ApiOperation("获取列表(分页)")
    @PostMapping("/page")
    public IPage<AppUserDTO> page(@RequestBody AppUserPageParam param) {
        return service.findDtoPage(param);
    }

    @ApiOperation("获取用户详情 by id")
    @GetMapping("/id/{id}")
    public AppUserDTO findById(@PathVariable(value = "id") Long id) {
        return service.getDtoById(id);
    }


    @ApiOperation("删除用户 by id")
    @DeleteMapping("/id/{id}")
    public Boolean delById(@PathVariable(value = "id") Long id) {
        return service.removeById(id);
    }

    @ApiOperation("添加用户")
    @PostMapping("/add")
    // @PreAuthorize("hasAuthority('sys:admin:edit')")
    public void add(@Validated(value = ValidGroup.add.class) @RequestBody AppUserSaveParam param) {
        ErrCodeEnum.E_10021.throwIf(!service.save(param));
        // 记录日志
        String log = StrUtil.format("新增账号:{} 操作人:{}", param.getUsername(), LoginUserUtil.getLoginUser().getUsername());
        logSysUserFacade.saveLog(new LogSysUserSaveParam(log, UserType.APP, LogSysUserOperationType.ADD_USER));
    }

    @ApiOperation("修改用户信息")
    @PostMapping("/save")
    // @PreAuthorize("hasAuthority('sys:admin:edit')")
    public void upd(@Validated(value = ValidGroup.upd.class) @RequestBody AppUserSaveParam param) {
        ErrCodeEnum.E_10021.throwIf(!service.save(param));
        // 记录日志
        String log = StrUtil.format("被修改账号:{} 操作人:{}", param.getUsername(), LoginUserUtil.getLoginUser().getUsername());
        logSysUserFacade.saveLog(new LogSysUserSaveParam(log, UserType.APP, LogSysUserOperationType.MODIFY_USER));
    }

    @ApiOperation("启用/禁用 用户")
    @PostMapping("/{id}/enabled/{enabled}")
    // @PreAuthorize("hasAuthority('sys:admin:edit')")
    public void enabled(@PathVariable(value = "id") Long id, @PathVariable(value = "enabled") Boolean enabled) {
        ErrCodeEnum.E_10021.throwIf(!service.lambdaUpdate().eq(AppUser::getId, id).set(AppUser::getEnabled, enabled).update());
    }

    @ApiOperation("重置密码为 a12345")
    @PostMapping("/{id}/resetPwd")
    // @PreAuthorize("permitAll()")
    public void resetPwd(@PathVariable(value = "id") Long id) {
        ErrCodeEnum.E_10021.throwIf(!service.resetPwd(id));
        // 记录日志
        AppUser user = service.findByIdNotNull(id);
        String log = StrUtil.format("被修改账号:{} 操作人:{}", user.getUsername(), LoginUserUtil.getLoginUser().getUsername());
        logSysUserFacade.saveLog(new LogSysUserSaveParam(log, UserType.APP, LogSysUserOperationType.RESET_PASSWORD));
    }

}
