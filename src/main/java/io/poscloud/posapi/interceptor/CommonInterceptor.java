package io.poscloud.posapi.interceptor;

import io.poscloud.posapi.authenticator.OneTimePassword;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@Component
public class CommonInterceptor implements HandlerInterceptor {

    OneTimePassword oneTimePassword;

    @Autowired
    public CommonInterceptor(OneTimePassword oneTimePassword) {
        this.oneTimePassword = oneTimePassword;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        return true;

//        String taxId = request.getHeader("tax-id");
//
//        if (null == taxId || taxId.length() != 10 || !taxId.matches("[0-9]+")) {
//            log.error("invalid tax-id.");
//            return false;
//        }
//
//        if (!oneTimePassword.isOtpValid(request.getHeader("otp"), taxId)) {
//            log.error("invalid otp.");
//            return false;
//        }
//
//        return true;
    }
}
