package org.lh.shop.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.lh.shop.system.api.domain.SysRole;

import java.util.List;

/**
 * <p>
 * 角色信息表 服务类
 * </p>
 *
 * @author wuYf
 * @since 2024-06-04
 */
public interface ISysRoleService extends IService<SysRole> {
    /**
     * 根据用户ID查询角色
     * @param userId 用户id
     * @return list of key
     */
    List<String> selectRoleKeysByUserId(Long userId);

    /**
     * 根据用户ID查询角色
     * @param userId 用户id
     * @return list or role
     */
    List<SysRole> selectRolesByUserId(Long userId);
}
