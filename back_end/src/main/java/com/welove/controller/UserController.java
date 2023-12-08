package com.welove.controller;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import com.welove.common.Result;
import com.welove.entity.Admin;
import com.welove.entity.FileWall;
import com.welove.entity.User;
import com.welove.service.FileWallService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;


import com.welove.service.UserService;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

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

    @GetMapping(value = "/{uid}")
    public Result getByUid(@PathVariable("uid") String userId) {
        return this.userService.getByUserId(userId);
    }

    @PostMapping(value = "/save")
    public Result save(@RequestBody User user) {
        return this.userService.saveUser(user);
    }

    @PostMapping("/avatar")
    public String upload(@RequestParam MultipartFile file, @RequestParam String  userId) throws IOException {
        return this.userService.uploadAvatar(file,Integer.parseInt(userId) );
    }
}
