package com.example.zhiwu.service.Impl;

import com.example.zhiwu.dao.CommentMapper;
import com.example.zhiwu.entity.Comment;
import com.example.zhiwu.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 范斌
 * @version 1.0
 */
@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentMapper commentMapper;


    @Override
    public boolean addComment(Comment comment) {
        return commentMapper.addComment(comment);
    }

    @Override
    public List<Comment> getCommentByPostId(String postId) {
        return commentMapper.getAllCommentByPostId(postId);
    }
}
