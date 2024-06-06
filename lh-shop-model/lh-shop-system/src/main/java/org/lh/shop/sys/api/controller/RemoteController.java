package org.lh.shop.sys.api.controller;

import org.lh.shop.sys.service.ISysUserService;
import org.lh.shop.system.api.model.LoginUser;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author wuYf
 * @date 2024/6/4 11:21
 */
@RestController
@RequestMapping("/remote/sys")
public class RemoteController {
    @Resource
    private ISysUserService userService;
    @GetMapping("/getUserInfo")
    public LoginUser getUserInfo(String username) {
        return userService.getUserInfo(username);
    }
}
