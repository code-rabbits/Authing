package com.zut.admin.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * @Date 2023-04-14 10:34 星期五
 * @Author: 聂建强
 * @Description:
 */
@RestController
@RequestMapping("/test")
public class TestController {


    @GetMapping("/test")
    public String test(Principal principal){
        String name = principal.getName();

        return "test ok !"+name;
    }
}
