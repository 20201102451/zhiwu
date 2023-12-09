package com.example.zhiwu.controller;

import com.example.zhiwu.common.Result;
import com.example.zhiwu.entity.User;
import com.example.zhiwu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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
