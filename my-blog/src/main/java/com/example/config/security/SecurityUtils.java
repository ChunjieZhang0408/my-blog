package com.example.config.security;

import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * @Desc SecurityUtils
 * @Author ZhangChunjie
 * @Date 2020/1/12 13:06
 * @Version 1.0
 */

public class SecurityUtils {

    public static Authentication getAuthentication(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication;
    }

    public static CustomUserDetails currentUserInfo(){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof CustomUserDetails){
            CustomUserDetails customUserDetails = (CustomUserDetails)principal;
            return customUserDetails;
        }
        throw new AuthenticationServiceException("账户信息获取失败");
    }
}
