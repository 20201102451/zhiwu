package com.example.zhiwu.entity;

public class Msg {
    private Integer msgId;
    private Integer senderId;
    private Integer receiverId;
    private String msgContent;
    private String  sendTime;
    private Integer msgListId;

    public Msg(Integer msgId, Integer senderId, Integer receiverId, String msgContent, String sendTime, Integer msgListId) {
        this.msgId = msgId;
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.msgContent = msgContent;
        this.sendTime = sendTime;
        this.msgListId = msgListId;
    }

    public Integer getMsgId() {
        return msgId;
    }

    public void setMsgId(Integer msgId) {
        this.msgId = msgId;
    }

    public Integer getSenderId() {
        return senderId;
    }

    public void setSenderId(Integer senderId) {
        this.senderId = senderId;
    }

    public Integer getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(Integer receiverId) {
        this.receiverId = receiverId;
    }

    public String getMsgContent() {
        return msgContent;
    }

    public void setMsgContent(String msgContent) {
        this.msgContent = msgContent;
    }

    public String getSendTime() {
        return sendTime;
    }

    public void setSendTime(String sendTime) {
        this.sendTime = sendTime;
    }

    public Integer getMsgListId() {
        return msgListId;
    }

    public void setMsgListId(Integer msgListId) {
        this.msgListId = msgListId;
    }

    @Override
    public String toString() {
        return "Msg{" +
                "msgId=" + msgId +
                ", senderId=" + senderId +
                ", receiverId=" + receiverId +
                ", msgContent='" + msgContent + '\'' +
                ", sendTime='" + sendTime + '\'' +
                ", msgListId=" + msgListId +
                '}';
    }
}
