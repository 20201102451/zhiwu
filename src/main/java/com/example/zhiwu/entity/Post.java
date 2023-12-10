package com.example.zhiwu.entity;

/**
 * @author 范斌
 * @version 1.0
 */

public class Post {
    private Integer postId;
    private Integer plateId;
    private Integer createId;
    private String postTitle;
    private String postContent;

    @Override
    public String toString() {
        return "Post{" +
                "postId=" + postId +
                ", plateId=" + plateId +
                ", createId=" + createId +
                ", postTitle='" + postTitle + '\'' +
                ", postContent='" + postContent + '\'' +
                '}';
    }

    public Integer getPostId() {
        return postId;
    }

    public void setPostId(Integer postId) {
        this.postId = postId;
    }

    public Integer getPlateId() {
        return plateId;
    }

    public void setPlateId(Integer plateId) {
        this.plateId = plateId;
    }

    public Integer getCreateId() {
        return createId;
    }

    public void setCreateId(Integer createId) {
        this.createId = createId;
    }

    public String getPostTitle() {
        return postTitle;
    }

    public void setPostTitle(String postTitle) {
        this.postTitle = postTitle;
    }

    public String getPostContent() {
        return postContent;
    }

    public void setPostContent(String postContent) {
        this.postContent = postContent;
    }

    public Post() {
    }

    public Post(Integer postId, Integer plateId, Integer createId, String postTitle, String postContent) {
        this.postId = postId;
        this.plateId = plateId;
        this.createId = createId;
        this.postTitle = postTitle;
        this.postContent = postContent;
    }
}
