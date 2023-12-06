package com.welove.controller;

import com.welove.common.Result;
import com.welove.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import com.welove.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    //登录
    @PostMapping(value = "/login")
    public Result login(@RequestBody User user) {
        return this.userService.login(user);
    }


    //注册
    @PostMapping(value = "/register")
    public Result register(@RequestBody User user) {
        return this.userService.register(user);
    }




}
