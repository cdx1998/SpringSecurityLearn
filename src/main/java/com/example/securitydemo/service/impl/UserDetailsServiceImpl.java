package com.example.securitydemo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.securitydemo.domain.LoginUser;
import com.example.securitydemo.domain.User;
import com.example.securitydemo.mapper.MenuMapper;
import com.example.securitydemo.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.rmi.MarshalException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * @Description
 * @Date 2022/6/16 19:28
 * @Author CDX
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Resource
    private UserMapper userMapper;

    @Resource
    private MenuMapper menuMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getUserName,username);
        User user = userMapper.selectOne(wrapper);
        System.out.println(user);
        if( Objects.isNull(user) ){
            throw new RuntimeException("用户名或密码错误！");
        }
        List<String> permissions = menuMapper.selectPermsByUserId(user.getId());
        //封装成UserDetials对象返回
        return new LoginUser(user,permissions);
    }
}
