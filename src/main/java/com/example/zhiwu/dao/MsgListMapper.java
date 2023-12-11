package com.example.zhiwu.dao;
import com.example.zhiwu.entity.MsgList;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import java.util.List;
public interface MsgListMapper {
    @Select("select * from msg_list")
    List<MsgList> getAllMsgList();
    @Select("select creater_id=#{uid} or associater_id=#{uid} from msg_list limit #{currentCount},3")
    List<MsgList> getMsgListByPage(Integer currentCount ,Integer uid);

}
