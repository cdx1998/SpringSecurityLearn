package com.example.securitydemo.service;

import com.example.securitydemo.domain.ResponseResult;
import com.example.securitydemo.domain.User;
import org.springframework.stereotype.Service;

/**
 * @Description
 * @Date 2022/6/16 16:41
 * @Author CDX
 */

public interface LoginService{
    ResponseResult login(User user);

    ResponseResult logout();

}
