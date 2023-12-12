package com.example.zhiwu.controller;

import com.example.zhiwu.common.Result;
import com.example.zhiwu.entity.Email;
import com.example.zhiwu.entity.User;
import com.example.zhiwu.service.EmailService;
import com.example.zhiwu.service.UserServicePost;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/email")
public class EmailController {
    @Autowired
    private EmailService emailService;

    @Autowired
    private UserServicePost userServicePost;

    @PutMapping("/send")
    public Result addEmail(@RequestBody Email email){
        System.out.println(email.getSenderId());
        System.out.println(email.getEmailContent());
        boolean result = emailService.addEmail(email.getSenderId(), email.getEmailContent());
        if(result){
            return Result.success("发布成功！");
        }else{
            return Result.fail("发布失败！");
        }
    }

    @GetMapping("/get")
    public Result getEmail(@RequestParam("receiverId") Integer receiverId){
        Email email = emailService.getEmailWithoutSelf(receiverId);
        if(email == null){
            return  Result.fail("没查到东西！");
        }
        User sender = userServicePost.getUserById(email.getSenderId());
        boolean result = emailService.updateEmailByLook(email.getEmailId(), receiverId);
        if(result){
            Map<String,String> map = new HashMap<>();
            map.put("emailId",email.getEmailId().toString());
            map.put("senderId",sender.getUserId().toString());
            map.put("senderName", sender.getUserName());
            map.put("emailContent",email.getEmailContent());
            if(map.size() == 0){
                return Result.fail("没查询到有东西！");
            }else {
                return Result.success(map);
            }
        }else{
            return Result.fail("更新表失败！");
        }

    }


}
