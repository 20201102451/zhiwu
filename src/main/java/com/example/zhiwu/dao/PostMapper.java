package com.example.zhiwu.dao;

import com.example.zhiwu.entity.Post;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;


import java.util.List;

/**
 * @author 范斌
 * @version 1.0
 */
@Mapper
public interface PostMapper {
    @Select("select * from post")
    List<Post> getAllPost();
}
