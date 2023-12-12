package com.example.zhiwu.dao;

import com.example.zhiwu.entity.Email;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface EmailMapper {
    @Insert("insert into email(sender_id,email_content) values(#{senderId},#{emailContent})")
    boolean addEmail(@Param("senderId") Integer senderId,@Param("emailContent") String emailContent);

    @Select("select * from email where receiver_id is null and sender_id <> #{receiverId} limit 1")
    Email getEmailWithoutSelf(@Param("receiverId") Integer receiverId);

    @Update("update email set receiver_id = #{receiverId} where email_id = #{emailId}")
    boolean updateEmailByLook(@Param("emailId") Integer emailId,@Param("receiverId") Integer receiverId);
}
