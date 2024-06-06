package org.lh.shop.common.satoken.config;

import cn.dev33.satoken.stp.StpInterface;
import org.lh.shop.common.core.enums.UserType;
import org.lh.shop.common.satoken.utils.LoginHelper;
import org.lh.shop.system.api.model.LoginUser;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wuYf
 * @date 2024/6/3 11:20
 */
public class PermissionInterface implements StpInterface {


    @Override
    public List<String> getPermissionList(Object loginId, String loginType) {
        LoginUser loginUser = LoginHelper.getLoginUser();
        UserType userType = UserType.getUserType(loginUser.getUserType());
        if (userType == UserType.SYS_USER) {
            return new ArrayList<>(loginUser.getMenuPermission());
        } else if (userType == UserType.APP_USER) {
            // 其他端 自行根据业务编写
        }
        return new ArrayList<>();
    }

    @Override
    public List<String> getRoleList(Object loginId, String loginType) {
        LoginUser loginUser = LoginHelper.getLoginUser();
        UserType userType = UserType.getUserType(loginUser.getUserType());
        if (userType == UserType.SYS_USER) {
            return new ArrayList<>(loginUser.getRolePermission());
        } else if (userType == UserType.APP_USER) {
            // 其他端 自行根据业务编写
        }
        return new ArrayList<>();
    }
}
