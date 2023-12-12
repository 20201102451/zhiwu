package com.example.zhiwu.controller;

import com.example.zhiwu.common.Result;
import com.example.zhiwu.entity.Msg;
import com.example.zhiwu.entity.User;
import com.example.zhiwu.service.MsgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/chat")
public class MsgController {
    @Autowired
    private MsgService msgService;
    @GetMapping("/getting")
    public List<Msg> getAllMsg(){return msgService.getAllMsg();}
    @GetMapping("/gets")
    public Result getMsgByPage(@RequestParam("msg_list_id") Integer msg_list_id){
        System.out.println(msg_list_id);
        List<Msg> msg = msgService.getMsgByPage(msg_list_id);
        if(msg.size() == 0){
            return Result.fail("999","没查到东西");
        }else{
            return Result.success(msg);
        }
    }
    @PostMapping("/add")
    public Result addMsg(@RequestBody Msg msg){
        boolean result = msgService.addMsg(msg);
        if(result){
            return Result.success("发送成功！");
        }else{
            return Result.fail("发送失败！");
        }
    }
}
