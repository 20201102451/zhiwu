package com.example.zhiwu.dao;
import com.example.zhiwu.entity.MsgList;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import java.util.List;
public interface MsgListMapper {
    @Select("select * from msg_list")
    List<MsgList> getAllMsgList();
    @Select("select * from msg_list where creater_id=#{uid} or associater_id=#{uid} limit #{currentCount},3")
    List<MsgList> getMsgListByPage(@Param("currentCount") Integer currentCount , @Param("uid") Integer uid);
}
