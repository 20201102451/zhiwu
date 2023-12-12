package com.example.zhiwu.service;

import com.example.zhiwu.entity.MsgList;
import com.example.zhiwu.entity.Post;

import java.util.List;

public interface MsgListService {
    List<MsgList> getAllMsgList();
    List<MsgList> getMsgListByPage(Integer currentCount ,Integer uid);
    boolean addMsgList(MsgList msgList);
}
