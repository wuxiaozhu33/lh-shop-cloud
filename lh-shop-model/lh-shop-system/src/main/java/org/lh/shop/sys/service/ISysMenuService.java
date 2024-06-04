package org.lh.shop.sys.service;

import org.lh.shop.sys.domain.SysMenu;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * <p>
 * 菜单权限表 服务类
 * </p>
 *
 * @author wuYf
 * @since 2024-06-04
 */
public interface ISysMenuService extends IService<SysMenu> {

    /**
     * 根据用户查询菜单
     * @param userId 用户id
     * @return List of perms
     */
    Set<String> selectMenuPermsByUserId(Long userId);
}
