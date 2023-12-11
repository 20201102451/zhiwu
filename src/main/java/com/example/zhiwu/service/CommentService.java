package com.example.zhiwu.service;

import com.example.zhiwu.entity.Comment;

import java.util.List;

/**
 * @author 范斌
 * @version 1.0
 */
public interface CommentService {
    /**
     * 用户评论
     * @param comment
     * @return
     */
    boolean addComment(Comment comment);

    /**
     * 根据帖子id查询关联评论
     * @param postId
     * @return
     */
    List<Comment> getCommentByPostId(String postId);
}
