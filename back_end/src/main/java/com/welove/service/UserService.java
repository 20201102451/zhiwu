package com.welove.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.welove.common.Result;
import com.welove.dao.UserDao;
import com.welove.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService extends ServiceImpl<UserDao, User> {

    @Autowired
    private UserDao userDao;




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
            return Result.fail();
        }

    }

    public Result register(User user) {
        System.out.println("resigter --> "+user);
        boolean result = this.save(user);
        if (result) {
            return Result.success();
        } else {
            return Result.fail();
        }
    }

}