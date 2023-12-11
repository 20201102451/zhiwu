package com.example.zhiwu.service.Impl;

import com.example.zhiwu.dao.UserMapper;
import com.example.zhiwu.entity.User;
import com.example.zhiwu.service.UserServicePost;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 范斌
 * @version 1.0
 */
@Service
public class UserServicePostImpl implements UserServicePost {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User getUserById(Integer userId) {
        return userMapper.getUserById(userId);
    }
}
