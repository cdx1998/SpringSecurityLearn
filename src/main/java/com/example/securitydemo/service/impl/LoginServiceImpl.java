package com.example.securitydemo.service.impl;

import com.example.securitydemo.domain.LoginUser;
import com.example.securitydemo.domain.ResponseResult;
import com.example.securitydemo.domain.User;
import com.example.securitydemo.service.LoginService;
import com.example.securitydemo.utils.JwtUtil;
import com.example.securitydemo.utils.RedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @Description
 * @Date 2022/6/16 21:05
 * @Author CDX
 */
@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private RedisCache redisCache;

    @Override
    public ResponseResult login(User user) {
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(user.getUserName(),
                                                        user.getPassword());
        Authentication authentication = authenticationManager.authenticate(authenticationToken);
        if( Objects.isNull(authentication) ){
            throw new RuntimeException("用户名或密码错误");
        }
        //使用userId生成token
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        String userId = loginUser.getUser().getId().toString();
        String jwt = JwtUtil.createJWT(userId);

        //将UserDetails存入redis
        redisCache.setCacheObject("login:"+userId,loginUser);

        Map<String,String> map = new HashMap<>();
        map.put("token",jwt);

        return new ResponseResult(200,"登陆成功",map);
    }

    @Override
    public ResponseResult logout() {
        //从SecurityContextHolder中获取到当前登录的userId
        UsernamePasswordAuthenticationToken authentication
                = (UsernamePasswordAuthenticationToken)SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        String userId = loginUser.getUser().getId().toString();

        //根据userId删除Redis中对应的缓存
        redisCache.deleteObject("login:"+userId);
        return new ResponseResult(200,"退出登录成功");
    }
}
