package com.example.zhiwu.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.zhiwu.common.Result;
import com.example.zhiwu.dao.UserLoveDao;
import com.example.zhiwu.entity.User;
import com.example.zhiwu.entity.UserLove;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserLoveService extends ServiceImpl<UserLoveDao, UserLove> {

    @Autowired
    private UserLoveDao userLoveDao;
    @Autowired
    private UserService userService;

    public Result getProposeFor(Integer userId) {
        QueryWrapper<UserLove> query = new QueryWrapper<>();
        query.eq("user2_id", userId);
        query.isNull("determine_time");
        List<UserLove> list = this.list(query);
        return Result.success(list);
    }

    public Result agreePropose(UserLove userLove) {
        System.out.println(userLove);
        QueryWrapper<UserLove> query = new QueryWrapper<>();
        query.eq("user1_id", userLove.getUser1Id());
        query.eq("user2_id", userLove.getUser2Id());

        this.saveOrUpdate(userLove, query);

        QueryWrapper<UserLove> deleteQuery = new QueryWrapper<>();
        deleteQuery.eq("user2_id", userLove.getUser2Id());
        deleteQuery.isNull("determine_time");
        this.remove(deleteQuery);

        deleteQuery = new QueryWrapper<>();
        deleteQuery.eq("user1_id", userLove.getUser2Id());
        deleteQuery.isNull("determine_time");
        this.remove(deleteQuery);

        deleteQuery = new QueryWrapper<>();
        deleteQuery.eq("user1_id", userLove.getUser1Id());
        deleteQuery.isNull("determine_time");
        this.remove(deleteQuery);

        deleteQuery = new QueryWrapper<>();
        deleteQuery.eq("user2_id", userLove.getUser1Id());
        deleteQuery.isNull("determine_time");
        this.remove(deleteQuery);

        return Result.success();
    }

    public Result getLover(Integer userId) {
        QueryWrapper<UserLove> query = new QueryWrapper<>();
        query.and(wrapper ->wrapper.eq("user1_id", userId).isNotNull("determine_time"));
        query.or(wrapper ->wrapper.eq("user2_id", userId).isNotNull("determine_time"));
        UserLove userLove = this.getOne(query);

        if (userLove == null) {
            return Result.success(null);
        }
        Integer loverId = userLove.getUser1Id().equals(userId) ? userLove.getUser2Id() : userLove.getUser1Id();

        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.eq("user_id", loverId);
        User lover = userService.getOne(userQueryWrapper);
        return Result.success(lover);

    }
}