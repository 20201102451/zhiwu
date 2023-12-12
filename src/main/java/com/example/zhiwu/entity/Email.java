package com.example.zhiwu.entity;

public class Email {
    private Integer emailId;
    private Integer senderId;
    private String emailContent;
    private Integer receiverId;

    public Email() {
    }

    public Email(Integer emailId, Integer senderId, String emailContent, Integer receiverId) {
        this.emailId = emailId;
        this.senderId = senderId;
        this.emailContent = emailContent;
        this.receiverId = receiverId;
    }

    public Integer getEmailId() {
        return emailId;
    }

    public void setEmailId(Integer emailId) {
        this.emailId = emailId;
    }

    public Integer getSenderId() {
        return senderId;
    }

    public void setSenderId(Integer senderId) {
        this.senderId = senderId;
    }

    public String getEmailContent() {
        return emailContent;
    }

    public void setEmailContent(String emailContent) {
        this.emailContent = emailContent;
    }

    public Integer getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(Integer receiverId) {
        this.receiverId = receiverId;
    }

    @Override
    public String toString() {
        return "Email{" +
                "emailId=" + emailId +
                ", senderId=" + senderId +
                ", emailContent='" + emailContent + '\'' +
                ", receiverId=" + receiverId +
                '}';
    }
}
