package com.example.zhiwu.dao;

import com.example.zhiwu.entity.Post;
import org.apache.ibatis.annotations.Insert;
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

    @Insert("insert into post(plate_id,create_id,post_title,post_content) values(#{plateId},#{createId},#{postTitle},#{postContent})")
    boolean addPost(Post post);
    @Select("select * from post limit #{currentCount},3")
    List<Post> getPostByPage(Integer currentCount);
}
