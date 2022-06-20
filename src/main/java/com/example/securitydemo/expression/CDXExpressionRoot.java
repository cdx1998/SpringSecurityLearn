package com.example.securitydemo.expression;

import com.example.securitydemo.domain.LoginUser;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;

/**
 * @Description
 * @Date 2022/6/19 21:44
 * @Author CDX
 */

@Component("example")
public class CDXExpressionRoot {

    public boolean hasAuthority(String authority) {
        //获取当前用户权限
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        List<String> permissions = loginUser.getPermissions();
        //判断用户权限集合中是否存在authority
        return permissions.contains(authority);
    }
}
