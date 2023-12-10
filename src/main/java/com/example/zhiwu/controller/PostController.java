package com.example.zhiwu.controller;

import com.example.zhiwu.common.Result;
import com.example.zhiwu.entity.Post;
import com.example.zhiwu.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author 范斌
 * @version 1.0
 */
@RestController
@RequestMapping("/post")
public class PostController {
    @Autowired
    private PostService postService;

    @GetMapping("/get")
    public List<Post> getAllPost(){

        return postService.getAllPost();
    }

    @PostMapping("/add")
    public Result addPost(@RequestBody Post post){
        boolean result = postService.addPost(post);
        if(result){
            return Result.success("发布成功！");
        }else{
            return Result.fail("发布失败！");
        }
    }

    @GetMapping("/getbypage")
    public Result getPostByPage(@RequestParam Integer currentCount){
        System.out.println(currentCount);
        List<Post> postList = postService.getPostByPage(currentCount);
        if(postList.size() == 0){
            return Result.fail("999","没查到东西");
        }else{
            return Result.success(postList);
        }
    }
}
