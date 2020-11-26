package com.boot.business.appuser.service.impl;

import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.digest.DigestUtil;
import cn.hutool.log.StaticLog;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.boot.business.appuser.mapper.AppUserMapper;
import com.boot.business.appuser.model.dto.AppUserDTO;
import com.boot.business.appuser.model.enums.AppUserErrCodeEnum;
import com.boot.business.appuser.model.param.AppUserLoginParam;
import com.boot.business.appuser.model.param.AppUserPageParam;
import com.boot.business.appuser.model.param.AppUserSaveParam;
import com.boot.business.appuser.model.po.AppUser;
import com.boot.business.appuser.service.IAppUserService;
import com.boot.commons.core.exception.enums.ErrCodeEnum;
import com.boot.commons.core.security.JwtTokenUtil;
import com.boot.commons.core.security.JwtUser;
import com.boot.commons.core.security.UserType;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;

/**
 * AppUserServiceImpl
 *
 * @author XINAN
 * @date 2019/7/19
 */
@Service
public class AppUserServiceImpl extends ServiceImpl<AppUserMapper, AppUser> implements IAppUserService, UserDetailsService {

    @Autowired
    @Qualifier("appAuthenticationManager")
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public JwtUser loadUserByUsername(String username) {
        AppUser user = this.findByName(username);
        if (null != user) {
            JwtUser jwtUser = new JwtUser();
            jwtUser.setId(user.getId());
            jwtUser.setName(user.getUsername());
            jwtUser.setPwd(user.getPassword());
            jwtUser.setUserType(UserType.APP);
            jwtUser.setEnabled(user.getEnabled());
            return jwtUser;
        }
        throw new UsernameNotFoundException(username);
    }

    @Override
    public JwtUser login(AppUserLoginParam param, Duration expiration, Boolean autoRenewal) {
        Authentication authenticate = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(param.getUsername(), param.getPassword()));
        return jwtTokenUtil.generateToken((JwtUser) authenticate.getPrincipal(), expiration, autoRenewal);
    }

    @Override
    public AppUser findByName(String name) {
        return this.getOne(Wrappers.<AppUser>lambdaQuery().eq(AppUser::getUsername, name), false);
    }

    @Override
    public AppUser findByIdNotNull(Long id) {
        AppUser user = super.getById(id);
        AppUserErrCodeEnum.E_20102.throwIf(null == user, id);
        return user;
    }

    @Override
    public Boolean checkPwd(Long id, String oldPassword) {
        return passwordEncoder.matches(oldPassword, this.findByIdNotNull(id).getPassword());
    }

    @Override
    public Boolean updPwd(Long id, String oldPassword, String newPassword) {
        AppUser user = this.findByIdNotNull(id);
        AppUserErrCodeEnum.E_20101.throwIf(!passwordEncoder.matches(oldPassword, user.getPassword()));
        // 清除用户登录token缓存
        jwtTokenUtil.cleanToken4UserId(UserType.ADMIN.name(), id);
        //将密码进行加密操作
        user.setPassword(passwordEncoder.encode(newPassword));
        return user.updateById();
    }

    @Override
    public Boolean resetPwd(Long id) {
        AppUser user = this.findByIdNotNull(id);
        // 清除用户登录token缓存
        jwtTokenUtil.cleanToken4UserId(UserType.ADMIN.name(), id);
        //将密码进行加密操作
        user.setPassword(passwordEncoder.encode(DigestUtil.md5Hex("a12345")));
        return user.updateById();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean save(AppUserSaveParam param) {

        AppUser user = new AppUser().warpT(param);
        AppUser byName = this.findByName(param.getUsername());
        AppUserErrCodeEnum.E_20100.throwIf(null != byName && (null == param.getId() || !byName.getId().equals(param.getId())),
                String.format("用户名[%s]已被使用", param.getUsername()));
        if (StrUtil.isNotBlank(param.getPassword())) {
            //将密码进行加密操作
            StaticLog.debug(DigestUtil.md5Hex(param.getPassword()));
            user.setPassword(passwordEncoder.encode(DigestUtil.md5Hex(param.getPassword())));
        }

        ErrCodeEnum.E_10021.throwIf(!super.saveOrUpdate(user), "保存用户信息失败");
        // 清除用户登录token缓存
        jwtTokenUtil.cleanToken4UserId(UserType.ADMIN.name(), user.getId());
        return true;
    }

    @Override
    public IPage<AppUserDTO> findDtoPage(AppUserPageParam param) {
        return super.page(param.page(), Wrappers.<AppUser>lambdaQuery()
                .like(StringUtils.isNotBlank(param.getUsernameLike()), AppUser::getUsername, param.getUsernameLike())
                .like(StringUtils.isNotBlank(param.getMobileLike()), AppUser::getMobile, param.getMobileLike())
        ).convert(this::warpDTO);
    }

    @Override
    public AppUserDTO getDtoById(Long id) {
        return this.warpDTO(this.findByIdNotNull(id));
    }

    private AppUserDTO warpDTO(AppUser user) {
        if (null == user) {
            return null;
        }
        AppUserDTO dto = user.warpR(AppUserDTO.class);
        return dto;
    }

    @Override
    public Long[] searchUser(String searchKey) {
        if (StrUtil.isBlank(searchKey)) {
            return new Long[0];
        }
        return super.list(Wrappers.<AppUser>lambdaQuery()
                .like(AppUser::getUsername, searchKey)
                .or()
                .like(AppUser::getNickName, searchKey)
                .or()
                .like(AppUser::getMobile, searchKey))
                .stream().map(AppUser::getId).toArray(Long[]::new);
    }

}