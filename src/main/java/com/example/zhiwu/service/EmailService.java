package com.example.zhiwu.service;

import com.example.zhiwu.entity.Email;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface EmailService {

    /**
     * 发送飞鸽信笺
     * @param senderId
     * @param emailContent
     * @return
     */
    boolean addEmail(Integer senderId, String emailContent);

    /**
     * 查询未被查看过的飞鸽信笺
     * @param receiverId
     * @return
     */
    Email getEmailWithoutSelf(Integer receiverId);

    /**
     * 更新被查看到的飞鸽信笺
     * @param emailId
     * @param receiverId
     * @return
     */
    boolean updateEmailByLook(Integer emailId,Integer receiverId);

}
