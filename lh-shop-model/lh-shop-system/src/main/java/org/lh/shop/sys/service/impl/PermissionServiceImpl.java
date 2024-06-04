package org.lh.shop.sys.service.impl;

import org.lh.shop.common.satoken.utils.LoginHelper;
import org.lh.shop.sys.service.IPermissionService;
import org.lh.shop.sys.service.ISysMenuService;
import org.lh.shop.sys.service.ISysRoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.Set;

/**
 * @author wuYf
 * @date 2024/6/4 14:52
 */
@Service
public class PermissionServiceImpl implements IPermissionService {

    @Resource
    private ISysRoleService roleService;

    @Resource
    private ISysMenuService menuService;

    @Override
    public Set<String> getRolePermission(Long userId) {
        Set<String> roles = new HashSet<String>();
        // 管理员拥有所有权限
        if (LoginHelper.isAdmin(userId)) {
            roles.add("admin");
        } else {
            roles.addAll(roleService.selectRoleKeysByUserId(userId));
        }
        return roles;
    }

    @Override
    public Set<String> getMenuPermission(Long userId) {
        Set<String> perms = new HashSet<String>();
        // 管理员拥有所有权限
        if (LoginHelper.isAdmin(userId)) {
            perms.add("*:*:*");
        } else {
            perms.addAll(menuService.selectMenuPermsByUserId(userId));
        }
        return perms;
    }
}
