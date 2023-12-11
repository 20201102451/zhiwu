package com.example.zhiwu.controller;

import com.example.zhiwu.common.Result;
import com.example.zhiwu.entity.Comment;
import com.example.zhiwu.entity.User;
import com.example.zhiwu.service.CommentService;
import com.example.zhiwu.service.UserServicePost;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 范斌
 * @version 1.0
 */

@RestController
@RequestMapping("/comment")
public class CommentController {
    @Autowired
    private CommentService commentService;

    @Autowired
    private UserServicePost userServicePost;

    @PostMapping("/add")
    public Result addComment(@RequestBody Comment comment) {
        boolean result = commentService.addComment(comment);
        if (result) {
            return Result.success("发布成功！");
        } else {
            return Result.fail("发布失败！");
        }
    }

    @GetMapping("/get")
    public Result getAllCommentByPostId(@RequestParam String postId) {
        List<Comment> commentList = commentService.getCommentByPostId(postId);
        ArrayList<Map> commentList2 = new ArrayList<>();
        for (Comment comment: commentList) {
            User user = userServicePost.getUserById(Integer.parseInt(comment.getUserId()));
            Map<String, String> commentMap = new HashMap<>();
            commentMap.put("commentId",comment.getCommentId().toString());
            commentMap.put("postId",comment.getPostId());
            commentMap.put("userId",comment.getUserId());
            commentMap.put("userName",user.getUserName());
            commentMap.put("commentContent", comment.getCommentContent());
            commentList2.add(commentMap);
        }
        if(commentList2.size() == 0){
            return Result.fail("999","没查到东西");
        }else{
            return Result.success(commentList2);
        }
    }

}
