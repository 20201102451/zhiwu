package com.example.zhiwu.entity;

/**
 * @author 范斌
 * @version 1.0
 */
public class Comment {
    private Integer commentId;
    private String userId;
    private String postId;
    private String commentContent;

    @Override
    public String toString() {
        return "Comment{" +
                "commentId=" + commentId +
                ", userId='" + userId + '\'' +
                ", postId='" + postId + '\'' +
                ", commentContent='" + commentContent + '\'' +
                '}';
    }

    public Integer getCommentId() {
        return commentId;
    }

    public void setCommentId(Integer commentId) {
        this.commentId = commentId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }

    public String getCommentContent() {
        return commentContent;
    }

    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent;
    }

    public Comment() {
    }

    public Comment(Integer commentId, String userId, String postId, String commentContent) {
        this.commentId = commentId;
        this.userId = userId;
        this.postId = postId;
        this.commentContent = commentContent;
    }
}
