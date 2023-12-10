package com.example.zhiwu.entity;

/**
 * @author 范斌
 * @version 1.0
 */

public class Post {
    private Integer postId;
    private String plateId;
    private String createId;
    private String postTitle;
    private String postContent;

    public Post(Integer postId, String plateId, String createId, String postTitle, String postContent) {
        this.postId = postId;
        this.plateId = plateId;
        this.createId = createId;
        this.postTitle = postTitle;
        this.postContent = postContent;
    }

    @Override
    public String toString() {
        return "Post{" +
                "postId=" + postId +
                ", plateId='" + plateId + '\'' +
                ", createId='" + createId + '\'' +
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

    public String getPlateId() {
        return plateId;
    }

    public void setPlateId(String plateId) {
        this.plateId = plateId;
    }

    public String getCreateId() {
        return createId;
    }

    public void setCreateId(String createId) {
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
}
