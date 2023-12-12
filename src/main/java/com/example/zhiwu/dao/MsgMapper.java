package com.example.zhiwu.dao;

import com.example.zhiwu.entity.Msg;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface MsgMapper {
    @Select("select * from msg")
    List<Msg> getAllMsg();
    @Select("select * from msg where msg_list_id=#{msg_list_id}")
    List<Msg> getMsgByPage(@Param("msg_list_id") Integer msg_list_id);
    @Insert("insert into msg(sender_id,receiver_id,msg_content,msg_list_id) values (#{senderId},#{receiverId},#{msgContent},#{msgListId})")
    boolean addMsg(Msg msg);
}
