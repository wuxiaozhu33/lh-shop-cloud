package org.lh.shop.sys.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.lh.shop.mybatis.page.PageQuery;
import com.baomidou.mybatisplus.extension.service.IService;
import org.lh.shop.sys.dto.SysUserDto;
import org.lh.shop.system.api.domain.SysUser;
import org.lh.shop.system.api.model.LoginUser;

import java.util.List;

/**
 * <p>
 * 用户信息表 服务类
 * </p>
 *
 * @author wuYf
 * @since 2024-06-04
 */
public interface ISysUserService extends IService<SysUser> {

    /**
     * 分页查询用户列表
     * @param userDto 入参
     * @param pageQuery 分页参数
     * @return page of user
     */
    IPage<SysUser> pageUser(SysUserDto userDto, PageQuery pageQuery);

    /**
     * 查询用户列表
     * @param userDto 入参
     * @return list of user
     */
    List<SysUser> listUser(SysUserDto userDto);

    /**
     * 根据用户名查询用户信息
     * @param username 登录用户名
     * @return SysUser
     */
    LoginUser getUserInfo(String username);
}
