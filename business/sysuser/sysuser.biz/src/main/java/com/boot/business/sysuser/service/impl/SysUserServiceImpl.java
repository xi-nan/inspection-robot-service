package com.boot.business.sysuser.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.boot.business.sysuser.mapper.SysUserMapper;
import com.boot.business.sysuser.model.dto.SysUserDTO;
import com.boot.business.sysuser.model.enums.SysUserErrCodeEnum;
import com.boot.business.sysuser.model.param.SysUserLoginParam;
import com.boot.business.sysuser.model.param.SysUserPageParam;
import com.boot.business.sysuser.model.param.SysUserSaveParam;
import com.boot.business.sysuser.model.po.SysRole;
import com.boot.business.sysuser.model.po.SysUser;
import com.boot.business.sysuser.model.po.SysUserRole;
import com.boot.business.sysuser.service.*;
import com.boot.commons.core.exception.enums.ErrCodeEnum;
import com.boot.commons.core.security.JwtTokenUtil;
import com.boot.commons.core.security.JwtUser;
import com.boot.commons.core.security.UserType;
import io.jsonwebtoken.lang.Collections;
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
import java.util.List;
import java.util.stream.Collectors;

/**
 * SysUserServiceImpl
 *
 * @author XINAN
 * @date 2019/7/19
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements ISysUserService, UserDetailsService, SysUserFacade {

    @Autowired
    @Qualifier("adminAuthenticationManager")
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ISysUserRoleService userRoleService;

    @Autowired
    private ISysRoleService roleService;

    @Autowired
    private ISysPermissionService permissionService;

    @Override
    public JwtUser loadUserByUsername(String username) {
        SysUser user = this.findByName(username);
        if (null != user) {
            JwtUser jwtUser = new JwtUser();
            jwtUser.setId(user.getId());
            jwtUser.setName(user.getUsername());
            jwtUser.setPwd(user.getPassword());
            jwtUser.setUserType(UserType.ADMIN);
            jwtUser.setPermissions(permissionService.getMenuResource(user.getId()));
            jwtUser.setEnabled(user.getEnabled());
            return jwtUser;
        }
        throw new UsernameNotFoundException(username);
    }

    @Override
    public JwtUser login(SysUserLoginParam param, Duration expiration, Boolean autoRenewal) {
        Authentication authenticate = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(param.getUsername(), param.getPassword()));
        return jwtTokenUtil.generateToken((JwtUser) authenticate.getPrincipal(), expiration, autoRenewal);
    }

    @Override
    public SysUser findByName(String name) {
        return this.getOne(Wrappers.<SysUser>lambdaQuery().eq(SysUser::getUsername, name), false);
    }

    @Override
    public SysUserDTO getDtoById(Long id) {
        return this.warpDTO(this.findByIdNotNull(id));
    }

    @Override
    public SysUser findByIdNotNull(Long id) {
        SysUser user = super.getById(id);
        SysUserErrCodeEnum.E_20102.throwIf(null == user, id);
        return user;
    }

    @Override
    public Boolean checkPwd(Long id, String oldPassword) {
        return passwordEncoder.matches(oldPassword, this.findByIdNotNull(id).getPassword());
    }

    @Override
    public Boolean resetPwd(Long id, String oldPassword, String newPassword) {
        SysUser user = this.findByIdNotNull(id);
        SysUserErrCodeEnum.E_20101.throwIf(!passwordEncoder.matches(oldPassword, user.getPassword()));
        // 清除用户登录token缓存
        jwtTokenUtil.cleanToken4UserId(UserType.ADMIN.name(), id);
        //将密码进行加密操作
        user.setPassword(passwordEncoder.encode(newPassword));
        return user.updateById();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean save(SysUserSaveParam param) {

        SysUser user = new SysUser().warpT(param);
        SysUser byName = this.findByName(param.getUsername());
        SysUserErrCodeEnum.E_20100.throwIf(null != byName && (null == param.getId() || !byName.getId().equals(param.getId())),
                String.format("用户名[%s]已被使用", param.getUsername()));
        if (StrUtil.isNotBlank(param.getPassword())) {
            //将密码进行加密操作
            user.setPassword(passwordEncoder.encode(param.getPassword()));
        }

        ErrCodeEnum.E_10021.throwIf(!super.saveOrUpdate(user), "保存用户信息失败");
        // 绑定角色
        userRoleService.bindUserRole(user.getId(), param.getRoleId());
        // 清除用户登录token缓存
        jwtTokenUtil.cleanToken4UserId(UserType.ADMIN.name(), user.getId());
        return true;
    }

    @Override
    public IPage<SysUserDTO> findDtoPage(SysUserPageParam param) {
        List<Long> users = null;
        if (null != param.getRoleId()) {
            users = new SysUserRole().selectList(Wrappers.<SysUserRole>lambdaQuery().eq(SysUserRole::getRoleId, param.getRoleId()))
                    .stream().map(SysUserRole::getUserId).collect(Collectors.toList());
            if (CollUtil.isEmpty(users)) {
                return new Page<>();
            }
        }

        return super.page(param.page(), Wrappers.<SysUser>lambdaQuery()
                .like(StringUtils.isNotBlank(param.getUsernameLike()), SysUser::getUsername, param.getUsernameLike())
                .in(null != users, SysUser::getId, users)
        ).convert(this::warpDTO);
    }

    private SysUserDTO warpDTO(SysUser user) {
        if (null == user) {
            return null;
        }
        SysUserDTO dto = user.warpR(SysUserDTO.class);
        List<SysUserRole> userRoleList = userRoleService.findByUserId(user.getId());
        if (!Collections.isEmpty(userRoleList)) {
            // 暂时只绑定一个角色 可修改绑定多个
            SysRole role = roleService.getById(userRoleList.get(0).getRoleId());
            if (null != role) {
                dto.setRoleId(role.getId());
                dto.setRoleName(role.getRoleName());
            }
        }
        return dto;
    }

    @Override
    public Long[] searchUser(String searchKey) {
        if (StrUtil.isBlank(searchKey)) {
            return new Long[0];
        }
        return super.list(Wrappers.<SysUser>lambdaQuery()
                .like(SysUser::getUsername, searchKey)
                .or()
                .like(SysUser::getNickName, searchKey)
                .or()
                .like(SysUser::getMobile, searchKey))
                .stream().map(SysUser::getId).toArray(Long[]::new);
    }
}