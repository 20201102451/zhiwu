package com.example.zhiwu.service.Impl;

import com.example.zhiwu.dao.MsgListMapper;
import com.example.zhiwu.entity.MsgList;
import com.example.zhiwu.entity.Post;
import com.example.zhiwu.service.MsgListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class MsgListServiceImpl implements MsgListService {
    @Autowired
    private MsgListMapper msgListMapper;
    @Override
    public List<MsgList> getAllMsgList(){return msgListMapper.getAllMsgList();}
    @Override
    public List<MsgList> getMsgListByPage(Integer currentCount,Integer uid) {
        return msgListMapper.getMsgListByPage(currentCount,uid);
    }
}
