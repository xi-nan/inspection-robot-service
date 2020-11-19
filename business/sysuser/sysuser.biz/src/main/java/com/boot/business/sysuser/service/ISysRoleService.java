package com.boot.business.sysuser.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.boot.business.sysuser.model.dto.RoleDTO;
import com.boot.business.sysuser.model.param.RoleSaveParam;
import com.boot.business.sysuser.model.po.SysRole;

import java.util.List;

/**
 * <p>
 * 角色表 服务类
 * </p>
 *
 * @author polarbear
 * @since 2019-10-29
 */
public interface ISysRoleService extends IService<SysRole> {


    List<RoleDTO> allRoles();


    RoleDTO getDTOById(Long roleId);

    // /**
    //  * 角色分页
    //  *
    //  * @param roleName
    //  * @param pageParam
    //  * @return
    //  */
    // IPage<SysRole> getRoleList(String roleName, PageParam pageParam);

    /**
     * 后台新增角色
     *
     * @param param
     * @return
     */
    Boolean save(RoleSaveParam param);


    /**
     * 修改角色信息
     *
     * @param param
     * @return
     */
    Boolean update(RoleSaveParam param);

    Boolean deleteRole(Long roleId);

}
