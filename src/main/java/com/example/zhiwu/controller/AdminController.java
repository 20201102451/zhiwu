package com.example.zhiwu.controller;

import com.example.zhiwu.common.Result;
import com.example.zhiwu.entity.Admin;
import com.example.zhiwu.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    //登录
    @PostMapping(value = "/login")
    public Result login(@RequestBody Admin admin) {
        return this.adminService.login(admin);
    }
}
