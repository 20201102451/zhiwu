package com.example.zhiwu.service.Impl;

import com.example.zhiwu.entity.Msg;
import com.example.zhiwu.dao.MsgMapper;
import com.example.zhiwu.service.MsgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class MsgServiceImpl implements MsgService {
    @Autowired
    private MsgMapper msgMapper;
    @Override
    public List<Msg> getAllMsg(){return msgMapper.getAllMsg();}
    @Override
    public List<Msg> getMsgByPage(Integer msg_list_id){
        return msgMapper.getMsgByPage(msg_list_id);
    }
    @Override
    public boolean addMsg(Msg msg){
        return msgMapper.addMsg(msg);
    }
}
