package com.boot.business.sysuser.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.boot.business.sysuser.mapper.SysUserRoleMapper;
import com.boot.business.sysuser.model.po.SysUserRole;
import com.boot.business.sysuser.service.ISysUserRoleService;
import com.boot.commons.core.exception.enums.ErrCodeEnum;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysUserRoleServiceImpl extends ServiceImpl<SysUserRoleMapper, SysUserRole> implements ISysUserRoleService {

    @Override
    public List<SysUserRole> findByUserId(Long userId) {
        return this.list(Wrappers.<SysUserRole>lambdaQuery().eq(SysUserRole::getUserId, userId));
    }

    @Override
    public List<SysUserRole> findByRoleId(Long roleId) {
        return this.list(Wrappers.<SysUserRole>lambdaQuery().eq(SysUserRole::getRoleId, roleId));
    }

    @Override
    public Boolean bindUserRole(Long userId, Long roleId) {
        ErrCodeEnum.E_10001.throwIf(null == userId || null == roleId, "用户绑定角色缺少参数");
        SysUserRole save = new SysUserRole();
        save.setUserId(userId);
        save.setRoleId(roleId);
        SysUserRole userRole = super.getOne(Wrappers.<SysUserRole>lambdaQuery().eq(SysUserRole::getUserId, userId));
        if (null != userRole) {
            save.setId(userRole.getId());
        }
        return super.saveOrUpdate(save);
    }
}
