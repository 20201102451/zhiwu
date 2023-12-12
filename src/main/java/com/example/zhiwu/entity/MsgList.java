package com.example.zhiwu.entity;

public class MsgList {
    public MsgList() {
    }

    private Integer msgListId;
    private Integer createrId;
    private Integer associaterId;

    public MsgList(Integer msgListId, Integer createrId, Integer associaterId) {
        this.msgListId = msgListId;
        this.createrId = createrId;
        this.associaterId = associaterId;
    }

    public Integer getMsgListId() {
        return msgListId;
    }

    public void setMsgListId(Integer msgListId) {
        this.msgListId = msgListId;
    }

    public Integer getCreaterId() {
        return createrId;
    }

    public void setCreaterId(Integer createrId) {
        this.createrId = createrId;
    }

    public Integer getAssociaterId() {
        return associaterId;
    }

    public void setAssociaterId(Integer associaterId) {
        this.associaterId = associaterId;
    }

    @Override
    public String toString() {
        return "MsgList{" +
                "msgListId=" + msgListId +
                ", createrId=" + createrId +
                ", associaterId=" + associaterId +
                '}';
    }
}
