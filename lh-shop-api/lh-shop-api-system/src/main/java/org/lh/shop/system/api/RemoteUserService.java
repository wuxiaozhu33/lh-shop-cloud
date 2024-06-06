package org.lh.shop.system.api;


import org.lh.shop.system.api.model.LoginUser;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 用户服务
 *
 * @author Lion Li
 */
@FeignClient(name = "lh-shop-system")
public interface RemoteUserService {

    /**
     * 通过用户名查询用户信息
     * @param username 用户名
     * @return 结果
     */
    @GetMapping("/remote/sys/getUserInfo")
    LoginUser getUserInfo(@RequestParam(name = "username") String username);


}
