package com.welove.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.welove.common.Result;
import com.welove.dao.AdminDao;
import com.welove.entity.Admin;
import com.welove.entity.FileWall;
import com.welove.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

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