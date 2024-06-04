package org.lh.shop.auth.controller;

import lombok.RequiredArgsConstructor;
import org.lh.shop.auth.form.EmailLoginBody;
import org.lh.shop.auth.form.LoginBody;
import org.lh.shop.auth.form.RegisterBody;
import org.lh.shop.auth.form.SmsLoginBody;
import org.lh.shop.auth.service.SysLoginService;
import org.lh.shop.common.core.constant.Constants;
import org.lh.shop.common.core.domin.R;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotBlank;
import java.util.HashMap;
import java.util.Map;

/**
 * token 控制
 *
 * @author Lion Li
 */
@Validated
@RequiredArgsConstructor
@RestController
public class TokenController {

    private final SysLoginService sysLoginService;

    /**
     * 登录方法
     */
    @PostMapping("login")
    public R<Map<String, Object>> login(@Validated @RequestBody LoginBody form) {
        // 用户登录
        String accessToken = sysLoginService.login(form.getUsername(), form.getPassword());
        // 接口返回信息
        Map<String, Object> rspMap = new HashMap<>(2);
        rspMap.put(Constants.ACCESS_TOKEN, accessToken);
        return R.ok(rspMap);
    }

    /**
     * 登出方法
     */
    @DeleteMapping("logout")
    public R<Void> logout() {
        sysLoginService.logout();
        return R.ok();
    }


}
