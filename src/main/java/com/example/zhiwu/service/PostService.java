package com.example.zhiwu.service;

import com.example.zhiwu.entity.Post;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 范斌
 * @version 1.0
 */

public interface PostService {
    /**
     * 查询所有的帖子
     * @return
     */
    List<Post> getAllPost();

    /**
     * 增加帖子
     * @param post
     * @return
     */
    boolean addPost(Post post);


    /**
     * 根据页数查询帖子
     * @return
     */
    List<Post> getPostByPage(Integer plateId,Integer currentCount);

    /**
     * 根据帖子Id删除帖子
     * @param postId
     * @return
     */
    boolean delPostById(Integer postId);

}
