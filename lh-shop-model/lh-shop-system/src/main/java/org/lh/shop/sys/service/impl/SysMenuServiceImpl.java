package org.lh.shop.sys.service.impl;

import org.lh.shop.sys.domain.SysMenu;
import org.lh.shop.sys.mapper.SysMenuMapper;
import org.lh.shop.sys.service.ISysMenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * <p>
 * 菜单权限表 服务实现类
 * </p>
 *
 * @author wuYf
 * @since 2024-06-04
 */
@Service
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenu> implements ISysMenuService {

    @Override
    public Set<String> selectMenuPermsByUserId(Long userId) {
        List<String> perms = this.baseMapper.selectMenuPermsByUserId(userId);
        return new HashSet<>(perms);
    }
}
