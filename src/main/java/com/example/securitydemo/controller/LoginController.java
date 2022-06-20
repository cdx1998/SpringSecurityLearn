package com.example.securitydemo.controller;

import com.example.securitydemo.domain.ResponseResult;
import com.example.securitydemo.domain.User;
import com.example.securitydemo.service.LoginService;
import com.example.securitydemo.service.impl.LoginServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @Description
 * @Date 2022/6/16 16:38
 * @Author CDX
 */

@RestController
@RequestMapping("/user")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @PostMapping("login")
    public ResponseResult login(@RequestBody User user) {
        System.out.println("开始登录验证");
        return loginService.login(user);
    }

    @GetMapping("logout")
    public ResponseResult logout() {
        return loginService.logout();
    }

}
