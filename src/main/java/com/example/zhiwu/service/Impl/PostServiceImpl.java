package com.example.zhiwu.service.Impl;

import com.example.zhiwu.entity.Post;
import com.example.zhiwu.dao.PostMapper;
import com.example.zhiwu.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 范斌
 * @version 1.0
 */
@Service
public class PostServiceImpl implements PostService {
    @Autowired
    private PostMapper postMapper;

    @Override
    public List<Post> getAllPost() {
        return postMapper.getAllPost();
    }

    @Override
    public boolean addPost(Post post) {

        return postMapper.addPost(post);
    }

    @Override
    public List<Post> getPostByPage(Integer plateId,Integer currentCount) {
        return postMapper.getPostByPage(plateId,currentCount);
    }

    @Override
    public boolean delPostById(Integer postId) {
        return postMapper.delPostById(postId);
    }
}
