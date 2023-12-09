package com.example.zhiwu.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.zhiwu.common.Result;
import com.example.zhiwu.dao.AdminDao;
import com.example.zhiwu.entity.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService extends ServiceImpl<AdminDao, Admin> {

    @Autowired
    private AdminDao adminDao;

    public Result login(Admin admin) {
        QueryWrapper<Admin> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("admin_id", admin.getAdminId());
        queryWrapper.eq("admin_password", admin.getAdminPassword());
        Admin one = getOne(queryWrapper);
        if (one != null) {
            return Result.success(one);
        } else {
            return Result.fail();
        }

    }


}