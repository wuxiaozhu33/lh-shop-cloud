package org.lh.shop.common.satoken.utils;

import cn.dev33.satoken.stp.StpUtil;
import org.lh.shop.common.core.exception.ServiceException;
import org.lh.shop.system.api.model.LoginUser;

import java.util.Optional;

/**
 * @author wuYf
 * @date 2024/6/3 14:15
 */
public class AuthUtil {
    public static LoginUser getCurrentUser() {
        LoginUser user = (LoginUser) StpUtil.getSession().get("loginUser");
        if (user == null) {
            throw new ServiceException("为获取到当前登录用户信息");
        }
        return user;
    }
    public static Long getUserId() {
        return Optional.ofNullable((Long) StpUtil.getLoginId()).orElse(-1L);
    }
}
