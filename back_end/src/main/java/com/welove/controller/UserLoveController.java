package com.welove.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.welove.common.Result;
import com.welove.entity.UserLove;
import com.welove.service.UserLoveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/user_love")
public class UserLoveController {

    @Autowired
    private UserLoveService userLoveService;

    @GetMapping("/propose_for/{uid}")
    public Result getProposeFor(@PathVariable("uid") String uid) {
        return this.userLoveService.getProposeFor(Integer.parseInt(uid));
    }

    @PostMapping("/agree")
    public Result agree(@RequestBody UserLove userLove) {
        return Result.success(userLoveService.agreePropose(userLove));

    }

    @PostMapping("/save")
    public Result save(@RequestBody UserLove userLove) {
        QueryWrapper<UserLove> query = new QueryWrapper<>();
        query.eq("user1_id", userLove.getUser1Id());
        query.eq("user2_id", userLove.getUser2Id());
        if (userLoveService.getOne(query) == null) {
            userLoveService.save(userLove);
        }
        return Result.success();

    }

    @PostMapping("/")
    public Result delete(@RequestBody UserLove userLove) {
        QueryWrapper<UserLove> query = new QueryWrapper<>();
        query.eq("user1_id", userLove.getUser1Id());
        query.eq("user2_id", userLove.getUser2Id());
        return Result.success(userLoveService.remove(query));
    }

    @GetMapping("/lover/{uid}")
    public Result getLover(@PathVariable("uid") String userId) {
        return this.userLoveService.getLover(Integer.parseInt(userId));
    }
}
