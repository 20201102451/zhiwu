package com.example.zhiwu.service;

import java.util.List;
import com.example.zhiwu.entity.Msg;
import org.springframework.stereotype.Service;
public interface MsgService {

    List<Msg> getAllMsg();
    List<Msg> getMsgByPage(Integer msg_list_id);
}
