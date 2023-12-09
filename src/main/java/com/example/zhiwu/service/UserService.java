package com.example.zhiwu.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.zhiwu.common.Result;
import com.example.zhiwu.dao.UserDao;
import com.example.zhiwu.entity.FileWall;
import com.example.zhiwu.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class UserService extends ServiceImpl<UserDao, User> {

    @Autowired
    private UserDao userDao;
    @Autowired
    private FileWallService fileService;



    public IPage<User> findPage(String id, String username, Integer role) {
        IPage<User> page = new Page<>();

        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        if (!"".equals(id)) {
            queryWrapper.eq("id", id);
        }
        if (!"".equals(username)) {
            queryWrapper.eq("username", username);
        }
        if (role != null) {
            queryWrapper.eq("role", role);
        }
        queryWrapper.orderByDesc("username");
        return this.page(page, queryWrapper);
    }


    public Result login(User user) {

        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("student_id", user.getStudentId());
        queryWrapper.eq("user_password", user.getUserPassword());
        User loginUser = getOne(queryWrapper);
        if (loginUser != null) {
            return Result.success(loginUser);
        } else {
            return Result.fail("密码错误");
        }

    }

    public Result register(User user) {
        QueryWrapper<User> query = new QueryWrapper<>();
        query.eq("student_id", user.getStudentId());
        User one = this.getOne(query);
        if (one != null) {
            return Result.fail("学号已注册");
        }
        System.out.println("resigter --> "+user);
        boolean result = this.save(user);
        if (result) {
            return Result.success();
        } else {
            return Result.fail();
        }
    }

    public Result getByUserId(String userId) {
        QueryWrapper<User> query = new QueryWrapper<>();
        query.eq("user_id", userId);
        User one = this.getOne(query);
        System.out.println("get one --> "+one);
        if (one != null) {
            return Result.success(one);
        } else {
            return Result.fail("用户不存在");
        }
    }

    public Result saveUser(User user) {
        QueryWrapper<User> query = new QueryWrapper<>();
        query.eq("user_id", user.getUserId());
        boolean result = this.saveOrUpdate(user, query);
        if (result) {
            return Result.success("保存成功");
        } else {
            return Result.fail();
        }
    }

    public String uploadAvatar(MultipartFile file, Integer userId) throws IOException {
        FileWall avatar = this.fileService.upload(file);
        System.out.println("avatar url--->"+avatar.getFileUrl());
        User user = new User();
        user.setAvatar(avatar.getFileUrl());
        user.setUserId(userId);
        this.updateById(user);
        return avatar.getFileUrl();
    }

}