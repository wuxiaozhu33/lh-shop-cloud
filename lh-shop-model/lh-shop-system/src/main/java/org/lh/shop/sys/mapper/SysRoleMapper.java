package org.lh.shop.sys.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.lh.shop.system.api.domain.SysRole;

import java.util.List;

/**
 * <p>
 * 角色信息表 Mapper 接口
 * </p>
 *
 * @author wuYf
 * @since 2024-06-04
 */
public interface SysRoleMapper extends BaseMapper<SysRole> {

    /**
     * 根据用户ID查询角色
     * @param userId 用户id
     * @return list of role
     */
    List<SysRole> selectRolesByUserId(Long userId);
}
