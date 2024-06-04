package org.lh.shop.sys.mapper;

import org.lh.shop.sys.domain.SysMenu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 菜单权限表 Mapper 接口
 * </p>
 *
 * @author wuYf
 * @since 2024-06-04
 */
public interface SysMenuMapper extends BaseMapper<SysMenu> {
    /**
     * 根据用户查询菜单权限
     * @param userId 用户id
     * @return List of perms
     */
    List<String> selectMenuPermsByUserId(Long userId);

    /**
     * 根据角色查询菜单权限
     * @param roleId 角色id
     * @return List of perms
     */
    List<String> selectMenuPermsByRoleId(Long roleId);
}
