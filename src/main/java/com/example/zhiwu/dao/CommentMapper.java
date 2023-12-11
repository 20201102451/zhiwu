package com.example.zhiwu.dao;

import com.example.zhiwu.entity.Comment;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author 范斌
 * @version 1.0
 */
@Mapper
public interface CommentMapper {
    @Insert("insert into Comment(user_id,post_id,comment_content) values(#{userId},#{postId},#{commentContent})")
    boolean addComment(Comment comment);

    @Select("select * from Comment where post_id = #{postId}")
    List<Comment> getAllCommentByPostId(String postId);
}
