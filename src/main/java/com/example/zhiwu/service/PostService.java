package com.example.zhiwu.service;

import com.example.zhiwu.entity.Post;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 范斌
 * @version 1.0
 */

public interface PostService {
    List<Post> getAllPost();
}
