package com.example.zhiwu.controller;

import com.example.zhiwu.common.Result;
import com.example.zhiwu.entity.Msg;
import com.example.zhiwu.entity.MsgList;
import com.example.zhiwu.service.MsgListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/chat")
public class MsgListController {
    @Autowired
    private MsgListService msgListService;
    @GetMapping("/get")
    public List<MsgList> getAllMsgList(){
        return msgListService.getAllMsgList();
    }
    @GetMapping("/getbypage")
    public Result getMsgListByPage(@RequestParam("currentCount") Integer currentCount,@RequestParam("uid") Integer uid){
        System.out.println(currentCount);
        System.out.println(uid);
        List<MsgList> msgList = msgListService.getMsgListByPage(currentCount, uid);
        if(msgList.size() == 0){
            return Result.fail("999","没查到东西");
        }else{
            return Result.success(msgList);
        }
    }
    @PostMapping("addList")
    public Result addMsgList(@RequestBody MsgList msgList){
        boolean result = msgListService.addMsgList(msgList);
        if(result){
            return Result.success("发送成功！");
        }else{
            return Result.fail("发送失败！");
        }
    }

}
