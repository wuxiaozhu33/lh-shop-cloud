package org.lh.shop.sys.cache;

import cn.hutool.extra.spring.SpringUtil;
import org.lh.shop.common.redis.utils.RedisUtils;
import org.lh.shop.sys.service.ISysDeptService;
import org.lh.shop.sys.service.ISysRoleService;
import org.lh.shop.system.api.domain.SysDept;
import org.lh.shop.system.api.domain.SysRole;

import java.util.List;

/**
 * @author wuYf
 * @date 2024/6/5 10:35
 */
public class SysCache {
    private static final String ROLE_USER_ID = "role:user:id:";
    private static final String DEPT_ID = "dept:id:";

    private static final ISysRoleService roleService = SpringUtil.getBean(ISysRoleService.class);

    private static final ISysDeptService deptService = SpringUtil.getBean(ISysDeptService.class);

    public static List<SysRole> getRolesByUserId(Long userId) {
        return RedisUtils.getAndPutValue("lh:sys:"+ ROLE_USER_ID+userId, () -> roleService.selectRolesByUserId(userId));
    }
    public static SysDept getDept(Long deptId) {
        return RedisUtils.getAndPutValue("lh:sys:"+DEPT_ID+deptId, () -> deptService.getById(deptId));
    }
}
