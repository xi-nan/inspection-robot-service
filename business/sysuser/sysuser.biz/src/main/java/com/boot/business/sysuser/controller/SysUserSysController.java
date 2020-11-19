package com.boot.business.sysuser.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.boot.business.syslog.model.enums.LogSysUserOperationType;
import com.boot.business.syslog.model.param.LogSysUserSaveParam;
import com.boot.business.syslog.service.LogSysUserFacade;
import com.boot.business.sysuser.model.dto.SysUserDTO;
import com.boot.business.sysuser.model.enums.LoginPlatform;
import com.boot.business.sysuser.model.enums.SysUserErrCodeEnum;
import com.boot.business.sysuser.model.param.SysUserLoginParam;
import com.boot.business.sysuser.model.param.SysUserPageParam;
import com.boot.business.sysuser.model.param.SysUserResetPwdParam;
import com.boot.business.sysuser.model.param.SysUserSaveParam;
import com.boot.business.sysuser.model.po.SysUser;
import com.boot.business.sysuser.service.ISysUserService;
import com.boot.commons.core.exception.enums.ErrCodeEnum;
import com.boot.commons.core.model.param.ValidGroup;
import com.boot.commons.core.redis.RedisComponent;
import com.boot.commons.core.security.JwtTokenUtil;
import com.boot.commons.core.security.JwtUser;
import com.boot.commons.core.security.LoginUserUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.Duration;

/**
 * SysUserSysController
 *
 * @author XINAN
 * @date 2019/7/19
 */

@Api(tags = "后台-管理员用户")
@RestController
@RequestMapping("/sys/admin")
public class SysUserSysController {

    @Autowired
    private ISysUserService service;

    @Autowired
    private RedisComponent redisComponent;

    @Autowired
    private LogSysUserFacade logSysUserFacade;

    @ApiOperation("管理员登陆，客户端登陆token有效期只有15秒(3次心跳周期) 每次发送心跳都会续期15秒")
    @PostMapping("/login")
    public String login(@Valid @RequestBody SysUserLoginParam param) {
        // 先不检验验证码
        //        CaptchaUtil.verify(param.getToken(), param.getCode());
        if (LoginPlatform.DESKTOP_CLIENT.equals(param.getLoginPlatform())) {
            // 设置token有效期为3次心跳周期, 且调用接口不自动续期
            JwtUser user = service.login(param, Duration.ofSeconds(15), false);

            redisComponent.set(LoginPlatform.DESKTOP_CLIENT.name() + "_" + user.getUserType() + "_" + user.getId(), user.getToken(), 5 * 3);
            return user.getToken();
        }
        return service.login(param, Duration.ofHours(6), true).getToken();
    }

    @ApiOperation("获取当前登陆用户信息")
    @GetMapping("/")
    public SysUserDTO loginUserInfo() {
        return service.getDtoById(LoginUserUtil.getLoginUserId());
    }

    @ApiOperation("添加用户")
    @PostMapping("/add")
    // @PreAuthorize("hasAuthority('sys:admin:edit')")
    public void add(@Validated(value = ValidGroup.add.class) @RequestBody SysUserSaveParam param) {
        ErrCodeEnum.E_10021.throwIf(!service.save(param));
        // 记录日志
        String log = StrUtil.format("新增账号:{} 操作人:{}", param.getUsername(), LoginUserUtil.getLoginUser().getUsername());
        logSysUserFacade.saveLog(new LogSysUserSaveParam(log, LogSysUserOperationType.ADD_USER));
    }

    @ApiOperation("保存用户信息")
    @PostMapping("/save")
    // @PreAuthorize("hasAuthority('sys:admin:edit')")
    public void upd(@Validated(value = ValidGroup.upd.class) @RequestBody SysUserSaveParam param) {
        ErrCodeEnum.E_10021.throwIf(!service.save(param));
        // 记录日志
        String log = StrUtil.format("被修改账号:{} 操作人:{}", param.getUsername(), LoginUserUtil.getLoginUser().getUsername());
        logSysUserFacade.saveLog(new LogSysUserSaveParam(log, LogSysUserOperationType.MODIFY_USER));
    }

    @ApiOperation("启用/禁用 用户")
    @PostMapping("/{id}/enabled/{enabled}")
    // @PreAuthorize("hasAuthority('sys:admin:edit')")
    public void enabled(@PathVariable(value = "id") Long id, @PathVariable(value = "enabled") Boolean enabled) {
        ErrCodeEnum.E_10021.throwIf(!service.lambdaUpdate().eq(SysUser::getId, id).set(SysUser::getEnabled, enabled).update());
    }

    @ApiOperation("修改当前登陆账号密码")
    @PostMapping("/pwd")
    // @PreAuthorize("permitAll()")
    public void updPwd(@Valid @RequestBody SysUserResetPwdParam param) {
        ErrCodeEnum.E_10021.throwIf(!service.updPwd(LoginUserUtil.getLoginUserId(), param.getOldPassword(), param.getNewPassword()));
        // 记录日志
        String username = LoginUserUtil.getLoginUser().getUsername();
        String log = StrUtil.format("被修改账号:{} 操作人:{}", username, username);
        logSysUserFacade.saveLog(new LogSysUserSaveParam(log, LogSysUserOperationType.UPD_PASSWORD));
    }

    @ApiOperation("重置密码为 a12345")
    @PostMapping("/{id}/resetPwd")
    // @PreAuthorize("permitAll()")
    public void resetPwd(@PathVariable(value = "id") Long id) {
        ErrCodeEnum.E_10021.throwIf(!service.resetPwd(id));
        // 记录日志
        SysUser user = service.findByIdNotNull(id);
        String log = StrUtil.format("被修改账号:{} 操作人:{}", user.getUsername(), LoginUserUtil.getLoginUser().getUsername());
        logSysUserFacade.saveLog(new LogSysUserSaveParam(log, LogSysUserOperationType.RESET_PASSWORD));
    }

    @ApiOperation("获取管理员列表(分页)")
    @PostMapping("/page")
    // @PreAuthorize("hasAuthority('sys:admin:list')")
    public IPage<SysUserDTO> page(@RequestBody SysUserPageParam param) {
        return service.findDtoPage(param);
    }

    @ApiOperation("获取用户详情 by id")
    @GetMapping("/id/{id}")
    // @PreAuthorize("hasAuthority('sys:admin:list')")
    public SysUserDTO findById(@PathVariable(value = "id") Long id) {
        return service.getDtoById(id);
    }

    @ApiOperation("删除用户")
    @DeleteMapping("/{id}")
    // @PreAuthorize("hasAuthority('sys:admin:del')")
    public void delById(@PathVariable(value = "id") Long id) {
        SysUserErrCodeEnum.E_20103.throwIf(id == 1L);
        SysUser user = service.findByIdNotNull(id);
        ErrCodeEnum.E_10022.throwIf(!user.deleteById());
        // 记录日志
        String log = StrUtil.format("删除账号:{} 操作人:{}", user.getUsername(), LoginUserUtil.getLoginUser().getUsername());
        logSysUserFacade.saveLog(new LogSysUserSaveParam(log, LogSysUserOperationType.DEL_USER));
    }

    @ApiOperation("客户端心跳上报, 返回错误码20104则清除本地token，并弹窗提示")
    @PostMapping("/health")
    public void health() {
        JwtUser user = LoginUserUtil.getLoginUser();
        // 客户端最后登陆token的key
        String key = LoginPlatform.DESKTOP_CLIENT.name() + "_" + user.getUserType() + "_" + user.getId();
        String newest = redisComponent.get(key);
        if (!user.getToken().equals(newest)) {
            // 有新的客户端登陆  退出登陆
            redisComponent.del(JwtTokenUtil.TOKEN_USER_GROUP + user.getToken());
            SysUserErrCodeEnum.E_20104.throwThis();
        }
        // 重置token有效期为3次心跳周期
        redisComponent.expire(JwtTokenUtil.TOKEN_USER_GROUP + user.getToken(), 5 * 3);
        // 额外缓存最新客户端登陆token
        redisComponent.set(key, user.getToken(), 5 * 3);
    }
}
