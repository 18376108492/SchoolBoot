package com.itdan.imooc.o2o.util;

import com.google.code.kaptcha.Constants;

import javax.servlet.http.HttpServletRequest;

/**
 * 验证码工具类
 */
public class CodeUtil {

    /**
     * 店铺注册验证码校验
     * @param request
     * @return
     */
        public static boolean checkVerifyCode(HttpServletRequest request) {
            String verifyCodeExpected = (String) request.getSession().getAttribute(
                    com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY);
            String verifyCodeActual = HttpServletRequestUtil.getString(request,
                    "verifyCodeActual");
            if (verifyCodeActual == null
                    || !verifyCodeActual.equalsIgnoreCase(verifyCodeExpected)) {
                return false;
            }
            return true;
        }
    }


