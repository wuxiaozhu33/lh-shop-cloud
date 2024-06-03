package org.lh.shop.auth.service;

import org.lh.shop.auth.model.LoginUser;
import org.lh.shop.common.core.exception.user.UserException;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author wuYf
 * @date 2024/6/3 15:11
 */
@FeignClient(name = "lh-shop-system", path = "/sys")
public interface RemoteUserService {
    /**
     * 根据用户名查询用户信息
     * @param username 用户名
     * @return LoginUser
     * @throws UserException 用户异常
     */
    @GetMapping("/remote/getUserInfo")
    LoginUser getUserInfo(String username) throws UserException;

    /**
     * 根据用户id查询用户名
     * @param userId 用户id
     * @return String
     */
    String selectUserNameById(Long userId);
}
