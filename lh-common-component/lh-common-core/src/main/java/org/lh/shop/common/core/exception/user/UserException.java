package org.lh.shop.common.core.exception.user;


import org.lh.shop.common.core.exception.base.BaseException;

/**
 * 用户信息异常类
 *
 * @author Lion Li
 */
public class UserException extends BaseException {
    private static final long serialVersionUID = 1L;

    public UserException(String code, Object... args) {
        super("user", code, args, null);
    }
}
