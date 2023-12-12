package com.example.zhiwu.service.Impl;

import com.example.zhiwu.dao.EmailMapper;
import com.example.zhiwu.entity.Email;
import com.example.zhiwu.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    private EmailMapper emailMapper;

    @Override
    public boolean addEmail(Integer senderId, String emailContent) {
        return emailMapper.addEmail(senderId,emailContent);
    }

    @Override
    public Email getEmailWithoutSelf(Integer receiverId) {
        return emailMapper.getEmailWithoutSelf(receiverId);
    }

    @Override
    public boolean updateEmailByLook(Integer emailId, Integer receiverId) {
        return emailMapper.updateEmailByLook(emailId,receiverId);
    }
}
