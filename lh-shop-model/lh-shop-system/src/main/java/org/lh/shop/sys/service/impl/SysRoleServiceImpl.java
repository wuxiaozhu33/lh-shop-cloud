package org.lh.shop.sys.service.impl;

import org.lh.shop.sys.mapper.SysRoleMapper;
import org.lh.shop.sys.service.ISysRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.lh.shop.system.api.domain.SysRole;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 角色信息表 服务实现类
 * </p>
 *
 * @author wuYf
 * @since 2024-06-04
 */
@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements ISysRoleService {

    @Override
    public List<String> selectRoleKeysByUserId(Long userId) {
        List<SysRole> roles = baseMapper.selectRolesByUserId(userId);
        return roles.stream().map(SysRole::getRoleKey).collect(Collectors.toList());
    }
}
