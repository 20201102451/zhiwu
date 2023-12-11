package com.example.zhiwu.dao;

import com.example.zhiwu.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * @author 范斌
 * @version 1.0
 */
@Mapper
public interface UserMapper {
    @Select("select * from user where user_id = #{userId}")
     User getUserById(Integer userId);
}
