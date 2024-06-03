package org.lh.shop.mybatis.inteceptor;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.lh.shop.auth.model.LoginUser;
import org.lh.shop.auth.utils.AuthUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.Optional;

/**
 * @author wuYf
 * @date 2024/6/3 14:04
 */
public class MyMetaObjectHandler implements MetaObjectHandler {
    private static final Logger log = LoggerFactory.getLogger(MyMetaObjectHandler.class);

    @Override
    public void insertFill(MetaObject metaObject) {
        LoginUser user = Optional.<LoginUser>ofNullable(getLoginUser()).orElse(LoginUser.getNullDefaultUser());
        if (metaObject.hasGetter("createBy")) {
            strictInsertFill(metaObject, "createBy", Long.class, user.getUserId());
        }
        if (metaObject.hasGetter("createTime")) {
            strictInsertFill(metaObject, "createTime", Date.class, new Date());
        }
        if (metaObject.hasGetter("updateBy")) {
            strictInsertFill(metaObject, "updateBy", Long.class, user.getUserId());
        }
        if (metaObject.hasGetter("updateTime")) {
            strictInsertFill(metaObject, "updateTime", Date.class, new Date());
        }
        if (metaObject.hasGetter("deptId")) {
            strictInsertFill(metaObject, "deptId", Long.class, user.getDeptId());
        }
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        Long userId = Optional.<Long>ofNullable(getLoginUserId()).orElse(-1L);
        if (metaObject.hasGetter("updateBy")) {
            strictUpdateFill(metaObject, "updateBy", Long.class, userId);
        }
        if (metaObject.hasGetter("updateTime")) {
            strictUpdateFill(metaObject, "updateTime", Date.class, new Date());
        }
    }

    private LoginUser getLoginUser() {
        try {
            return AuthUtil.getCurrentUser();
        } catch (Exception e) {
            return null;
        }
    }

    private Long getLoginUserId() {
        try {
            return AuthUtil.getUserId();
        } catch (Exception e) {
            log.error("未获取到当前登录用户id", e);
            return null;
        }
    }
}
