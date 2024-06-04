package org.lh.shop.sys.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.lh.shop.common.core.enums.UserStatus;
import org.lh.shop.common.core.exception.user.UserException;
import org.lh.shop.common.core.utils.StringUtils;
import org.lh.shop.mybatis.page.PageQuery;
import org.lh.shop.sys.dto.SysUserDto;
import org.lh.shop.sys.mapper.SysUserMapper;
import org.lh.shop.sys.service.IPermissionService;
import org.lh.shop.sys.service.ISysUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.lh.shop.system.api.domain.SysUser;
import org.lh.shop.system.api.model.LoginUser;
import org.lh.shop.system.api.model.RoleDTO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 用户信息表 服务实现类
 *
 * @author wuYf
 * @since 2024-06-04
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements ISysUserService {

    @Resource
    private IPermissionService permissionService;

    @Override
    public IPage<SysUser> pageUser(SysUserDto userDto, PageQuery pageQuery) {
        LambdaQueryWrapper<SysUser> queryWrapper = new LambdaQueryWrapper<>();
        setQueryWrapper(userDto, queryWrapper);
        return this.page(pageQuery.build(), queryWrapper);
    }

    @Override
    public List<SysUser> listUser(SysUserDto userDto) {
        LambdaQueryWrapper<SysUser> queryWrapper = new LambdaQueryWrapper<>();
        setQueryWrapper(userDto, queryWrapper);
        return this.list(queryWrapper);
    }

    @Override
    public LoginUser getUserInfo(String username) {
        SysUser sysUser = this.getOne(Wrappers.<SysUser>lambdaQuery().eq(SysUser::getUserName, username));

        if (ObjectUtil.isNull(sysUser)) {
            throw new UserException("user.not.exists", username);
        }
        if (UserStatus.DISABLE.getCode().equals(sysUser.getStatus())) {
            throw new UserException("user.blocked", username);
        }
        return buildLoginUser(sysUser);
    }

    /**
     * 构建登录用户
     */
    private LoginUser buildLoginUser(SysUser user) {
        LoginUser loginUser = new LoginUser();
        loginUser.setUserId(user.getUserId());
        loginUser.setDeptId(user.getDeptId());
        loginUser.setUsername(user.getUserName());
        loginUser.setPassword(user.getPassword());
        loginUser.setUserType(user.getUserType());
        loginUser.setMenuPermission(permissionService.getMenuPermission(user.getUserId()));
        loginUser.setRolePermission(permissionService.getRolePermission(user.getUserId()));
        loginUser.setDeptName(ObjectUtil.isNull(user.getDept()) ? "" : user.getDept().getDeptName());
        List<RoleDTO> roles = BeanUtil.copyToList(user.getRoles(), RoleDTO.class);
        loginUser.setRoles(roles);
        return loginUser;
    }

    private void setQueryWrapper(SysUserDto userDto, LambdaQueryWrapper<SysUser> queryWrapper) {
        queryWrapper.like(StringUtils.isNotBlank(userDto.getUserName()), SysUser::getUserName, userDto.getUserName());
        queryWrapper.eq(ObjectUtil.isNotEmpty(userDto.getUserId()), SysUser::getUserId, userDto.getUserId());
        queryWrapper.eq(StringUtils.isNotBlank(userDto.getUserType()), SysUser::getUserType, userDto.getUserType());
        queryWrapper.eq(SysUser::getStatus, 0);
    }
}
