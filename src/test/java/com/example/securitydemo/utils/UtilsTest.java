package com.example.securitydemo.utils;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @Description
 * @Date 2022/6/17 0:35
 * @Author CDX
 */
@SpringBootTest
public class UtilsTest {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Test
    public void test1(){
        String s1 = bCryptPasswordEncoder.encode("123456");
        String s2 = bCryptPasswordEncoder.encode("123456");
        System.out.println("s1 = "+s1);
        System.out.println("s2 = "+s2);
        System.out.println("s1 match " + bCryptPasswordEncoder.matches("123456", s1));
        System.out.println("s2 match " + bCryptPasswordEncoder.matches("123456", s2));
    }


}
