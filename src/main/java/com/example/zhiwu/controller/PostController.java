package com.example.zhiwu.controller;

import com.example.zhiwu.common.Result;
import com.example.zhiwu.entity.Post;
import com.example.zhiwu.entity.User;
import com.example.zhiwu.service.PostService;
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
@RequestMapping("/post")
public class PostController {
    @Autowired
    private PostService postService;

    @Autowired
    private UserServicePost userServicePost;
//    @GetMapping("/get")
//    public List<Post> getAllPost(){
//
//        return postService.getAllPost();
//    }

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
    public Result getPostByPage(@RequestParam("currentCount") Integer currentCount,@RequestParam("plateId") Integer plateId){
        System.out.println(currentCount);
        System.out.println(plateId);
        List<Post> postList = postService.getPostByPage(plateId,currentCount);
        List postList2 = new ArrayList<>();
        for (Post post: postList) {
            User creater = userServicePost.getUserById(Integer.parseInt(post.getCreateId()));
            Map<String, String> mapPost = new HashMap<>();
            mapPost.put("postId",post.getPostId().toString());
            mapPost.put("plateId",post.getPlateId());
            mapPost.put("createId",post.getCreateId());
            mapPost.put("postTitle",post.getPostTitle());
            mapPost.put("postContent",post.getPostContent());
            mapPost.put("createName",creater.getUserName());
            postList2.add(mapPost);
        }
        if(postList.size() == 0){
            return Result.fail("999","没查到东西");
        }else{
            return Result.success(postList2);
        }
    }

    @DeleteMapping("/del")
    public Result delPostById(@RequestParam("postId") Integer postId){
        boolean result = postService.delPostById(postId);
        if(result){
            return Result.success("删除成功！");
        }else{
            return Result.fail("删除失败！");
        }
    }
}
