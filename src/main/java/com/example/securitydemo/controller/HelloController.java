package com.example.securitydemo.controller;

import com.example.securitydemo.domain.ResponseResult;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description
 * @Date 2022/6/16 10:14
 * @Author CDX
 */

@RestController
@RequestMapping
public class HelloController {

    @GetMapping("/hello")
    @PreAuthorize("hasAuthority('system:dept:list')")
    public String hello(){
        return "hello";
    }

    @GetMapping("/testAuth")
    @PreAuthorize("@example.hasAuthority('system:dept:list')")
    public String test1(){
        return "hello";
    }

    @PostMapping("/testCors")
    public ResponseResult testCors(){
        return new ResponseResult(200,"testCors");
    }


}
