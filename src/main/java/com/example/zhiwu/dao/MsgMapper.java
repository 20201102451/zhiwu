package com.example.zhiwu.dao;

import com.example.zhiwu.entity.Msg;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface MsgMapper {
    @Select("select * from msg")
    List<Msg> getAllMsg();
    @Select("select creater_id=#{uid} or associater_id=#{uid} from msg_list limit #{currentCount},3")
    List<Msg> getMsgByPage(Integer currentCount , Integer uid);
}
