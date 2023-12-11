package com.example.zhiwu.controller;

import com.example.zhiwu.entity.MsgList;
import com.example.zhiwu.service.MsgListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
