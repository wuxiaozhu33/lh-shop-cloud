package org.lh.shop.auth.service;

import cn.dev33.satoken.exception.NotLoginException;
import cn.dev33.satoken.secure.BCrypt;
import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.http.useragent.UserAgent;
import cn.hutool.http.useragent.UserAgentUtil;
import org.lh.shop.auth.properties.UserPasswordProperties;
import org.lh.shop.common.core.constant.CacheConstants;
import org.lh.shop.common.core.constant.Constants;
import org.lh.shop.common.core.enums.DeviceType;
import org.lh.shop.common.core.enums.LoginType;
import org.lh.shop.common.core.enums.UserType;
import org.lh.shop.common.core.exception.user.CaptchaExpireException;
import org.lh.shop.common.core.exception.user.UserException;
import org.lh.shop.common.core.utils.MessageUtils;
import org.lh.shop.common.core.utils.ServletUtils;
import org.lh.shop.common.core.utils.SpringUtils;
import org.lh.shop.common.core.utils.ip.AddressUtils;
import org.lh.shop.common.redis.utils.RedisUtils;
import org.lh.shop.common.satoken.utils.LoginHelper;
import org.lh.shop.system.api.RemoteUserService;
import org.lh.shop.system.api.domain.SysUser;
import org.lh.shop.system.api.model.LoginUser;
import org.lh.shop.system.api.model.XcxLoginUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.time.Duration;
import java.util.function.Supplier;

/**
 * 登录校验方法
 *
 * @author ruoyi
 */
@Service
public class SysLoginService {

    private RemoteUserService remoteUserService;

    @Resource
    private UserPasswordProperties userPasswordProperties;

    /**
     * 登录
     */
    public String login(String username, String password) {
        LoginUser userInfo = remoteUserService.getUserInfo(username);

        checkLogin(LoginType.PASSWORD, username, () -> !BCrypt.checkpw(password, userInfo.getPassword()));
        // 获取登录token
        LoginHelper.loginByDevice(userInfo, DeviceType.PC);

        return StpUtil.getTokenValue();
    }

    /**
     * 登录校验
     */
    private void checkLogin(LoginType loginType, String username, Supplier<Boolean> supplier) {
        String errorKey = CacheConstants.PWD_ERR_CNT_KEY + username;
        String loginFail = Constants.LOGIN_FAIL;
        Integer maxRetryCount = userPasswordProperties.getMaxRetryCount();
        Integer lockTime = userPasswordProperties.getLockTime();

        // 获取用户登录错误次数(可自定义限制策略 例如: key + username + ip)
        Integer errorNumber = RedisUtils.getCacheObject(errorKey);
        // 锁定时间内登录 则踢出
        if (ObjectUtil.isNotNull(errorNumber) && errorNumber.equals(maxRetryCount)) {
            throw new UserException(loginType.getRetryLimitExceed(), maxRetryCount, lockTime);
        }

        if (supplier.get()) {
            // 是否第一次
            errorNumber = ObjectUtil.isNull(errorNumber) ? 1 : errorNumber + 1;
            // 达到规定错误次数 则锁定登录
            if (errorNumber.equals(maxRetryCount)) {
                RedisUtils.setCacheObject(errorKey, errorNumber, Duration.ofMinutes(lockTime));
                throw new UserException(loginType.getRetryLimitExceed(), maxRetryCount, lockTime);
            } else {
                // 未达到规定错误次数 则递增
                RedisUtils.setCacheObject(errorKey, errorNumber);
                throw new UserException(loginType.getRetryLimitCount(), errorNumber);
            }
        }
        // 登录成功 清空错误次数
        RedisUtils.deleteObject(errorKey);
    }

    /**
     * 退出登录
     */
    public void logout() {
        try {
            StpUtil.logout();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
