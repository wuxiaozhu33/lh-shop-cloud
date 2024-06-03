package org.lh.shop.common.satoken.config;

import cn.dev33.satoken.stp.StpInterface;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author wuYf
 * @date 2024/6/3 11:20
 */
@Component
public class PermissionInterface  implements StpInterface {

    @Override
    public List<String> getPermissionList(Object loginId, String loginType) {
//        List<SysMenu> menuList = this.menuService.selectPermsByRoleId(AuthUtil.getUserRoleId());
//        return (List<String>)menuList.stream().map(SysMenu::getPerms).collect(Collectors.toList());
        return new ArrayList<>();
    }

    @Override
    public List<String> getRoleList(Object loginId, String loginType) {
//        String roleCodes = AuthUtil.getUserRoleCode();
//        return ComUtil.toStrList(roleCodes);
        return new ArrayList<>();
    }
}
