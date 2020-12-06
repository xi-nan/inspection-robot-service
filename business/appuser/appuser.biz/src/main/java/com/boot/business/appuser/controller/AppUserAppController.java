package com.boot.business.appuser.controller;

import cn.hutool.core.util.StrUtil;
import com.boot.business.appuser.model.dto.AppUserDTO;
import com.boot.business.appuser.model.enums.AppUserErrCodeEnum;
import com.boot.business.appuser.model.param.AppUserLoginParam;
import com.boot.business.appuser.model.param.AppUserUpdPwdParam;
import com.boot.business.appuser.service.IAppUserService;
import com.boot.business.syslog.model.enums.LogSysUserOperationType;
import com.boot.business.syslog.model.param.LogSysUserSaveParam;
import com.boot.business.syslog.service.LogSysUserFacade;
import com.boot.commons.core.exception.enums.ErrCodeEnum;
import com.boot.commons.core.redis.RedisComponent;
import com.boot.commons.core.security.JwtTokenUtil;
import com.boot.commons.core.security.JwtUser;
import com.boot.commons.core.security.LoginUserUtil;
import com.boot.commons.core.security.UserType;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.Duration;

@Api(tags = "客户端-用户")
@RestController
@RequestMapping(value = "/app/user")
public class AppUserAppController {

    @Autowired
    private IAppUserService service;

    @Autowired
    private RedisComponent redisComponent;

    @Autowired
    private LogSysUserFacade logSysUserFacade;

    @ApiOperation("获取当前登陆用户信息")
    @GetMapping("/info")
    public AppUserDTO findById() {
        return service.getDtoById(LoginUserUtil.getLoginUserId());
    }

    @ApiOperation("客户端登陆")
    @PostMapping("/login")
    public String login(@Valid @RequestBody AppUserLoginParam param) {
        // 设置token有效期为3次心跳周期, 且调用接口不自动续期
        JwtUser user = service.login(param, Duration.ofHours(6), true);
        redisComponent.set("health_" + user.getUserType() + "_" + user.getId(), user.getToken(), 5 * 3);
        return user.getToken();
    }

    @ApiOperation("修改密码")
    @PostMapping("/pwd")
    public void updPwd(@Valid @RequestBody AppUserUpdPwdParam param) {
        ErrCodeEnum.E_10021.throwIf(!service.updPwd(LoginUserUtil.getLoginUserId(), param.getOldPassword(), param.getNewPassword()));
        // 记录日志
        String username = LoginUserUtil.getLoginUser().getUsername();
        String log = StrUtil.format("被修改账号:{} 操作人:{}", username, username);
        logSysUserFacade.saveLog(new LogSysUserSaveParam(log, UserType.APP, LogSysUserOperationType.UPD_PASSWORD));
    }

    @ApiOperation("客户端心跳上报, 返回错误码20104则清除本地token，并弹窗提示")
    @PostMapping("/health")
    public void health() {
        JwtUser user = LoginUserUtil.getLoginUser();
        // 客户端最后登陆token的key
        String key = "health_" + user.getUserType() + "_" + user.getId();
        String newest = redisComponent.get(key);
        if (!user.getToken().equals(newest)) {
            // 有新的客户端登陆  退出登陆
            redisComponent.del(JwtTokenUtil.TOKEN_USER_GROUP + user.getToken());
            AppUserErrCodeEnum.E_20104.throwThis();
        }
        // 额外缓存最新客户端登陆token
        redisComponent.set(key, user.getToken(), 5 * 3);
    }
}
