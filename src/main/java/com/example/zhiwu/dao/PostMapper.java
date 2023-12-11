package com.example.zhiwu.dao;

import com.example.zhiwu.entity.Post;
import org.apache.ibatis.annotations.*;


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
    @Select("select * from post where plate_id = #{plateId} limit #{currentCount},6")
    List<Post> getPostByPage(@Param("plateId") Integer plateId, @Param("currentCount") Integer currentCount);

    @Delete("delete from post where post_id = #{postId}")
    boolean delPostById(@Param("postId") Integer postId);
}
