package com.welove.controller;

import com.welove.common.Result;
import com.welove.entity.Admin;
import com.welove.entity.User;
import com.welove.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


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
