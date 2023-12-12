package com.example.zhiwu.controller;

import com.example.zhiwu.entity.Msg;
import com.example.zhiwu.service.MsgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/chat")
public class MsgController {
    @Autowired
    private MsgService msgService;
    @GetMapping("/gets")
    public List<Msg> getAllMsg(){return msgService.getAllMsg();}
}
