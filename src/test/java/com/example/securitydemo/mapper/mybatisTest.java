package com.example.securitydemo.mapper;

import com.example.securitydemo.domain.User;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description
 * @Date 2022/6/16 17:15
 * @Author CDX
 */

@SpringBootTest
public class mybatisTest {

    @Resource
    private UserMapper userMapper;

    @Resource
    private MenuMapper menuMapper;

    @Test
    public void test1(){
        User user = new User();
        user.setId(2L);
        user.setUserName("admin");
        user.setPassword("123456");
        System.out.println("对象封装完成");
        int insert = userMapper.insert(user);
        System.out.println(insert);
    }
    @Test
    public void test2(){
        List<User> users = userMapper.selectList(null);
        System.out.println(users);
    }

    @Test
    public void test3(){
        String str = "";
        System.out.println(str.length());
        System.out.println(str.isEmpty());
    }

    @Test
    public void selectPermsByUserIdTest(){
        List<String> strings = menuMapper.selectPermsByUserId(1L);
        System.out.println(strings);
    }



}
