package org.lh.shop.sys.service;

import org.lh.shop.system.api.domain.SysUser;

import java.util.Set;

/**
 * @author wuYf
 * @date 2024/6/4 14:51
 */
public interface IPermissionService {
    /**
     * 获取角色数据权限
     *
     * @param userId 用户Id
     * @return 角色权限信息
     */
    Set<String> getRolePermission(Long userId);

    /**
     * 获取菜单数据权限
     *
     * @param userId 用户Id
     * @return 菜单权限信息
     */
    Set<String> getMenuPermission(Long userId);
}
