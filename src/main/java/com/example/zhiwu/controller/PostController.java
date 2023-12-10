package com.example.zhiwu.controller;

import com.example.zhiwu.entity.Post;
import com.example.zhiwu.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
