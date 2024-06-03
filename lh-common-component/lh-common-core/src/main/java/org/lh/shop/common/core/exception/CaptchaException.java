package org.lh.shop.common.core.exception;


import org.lh.shop.common.core.exception.user.UserException;

/**
 * 验证码错误异常类
 *
 * @author Lion Li
 */
public class CaptchaException extends UserException {
    private static final long serialVersionUID = 1L;

    public CaptchaException() {
        super("user.jcaptcha.error");
    }

    public CaptchaException(String msg) {
        super(msg);
    }
}
