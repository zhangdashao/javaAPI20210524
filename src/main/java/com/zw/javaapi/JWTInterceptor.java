package com.zw.javaapi;

import com.auth0.jwt.exceptions.AlgorithmMismatchException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.zw.javaapi.Utils.JWTUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class JWTInterceptor  implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String token = request.getHeader("token");
        if(StringUtils.isEmpty(token)){
//            throw new MyException("token不能为空");

            throw new RuntimeException("无token，请重新登录");
        }
        try {
            JWTUtils.verify(token);
        } catch (SignatureVerificationException e) {
//            log.error("无效签名！ 错误 ->", e);
            return false;
        } catch (TokenExpiredException e) {
//            log.error("token过期！ 错误 ->", e);
            return false;
        } catch (AlgorithmMismatchException e) {
//            log.error("token算法不一致！ 错误 ->", e);
            return false;
        } catch (Exception e) {
//            log.error("token无效！ 错误 ->", e);
            return false;
        }
        return true;
    }
}
