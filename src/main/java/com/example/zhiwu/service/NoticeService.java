package com.example.zhiwu.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.zhiwu.dao.NoticeDao;
import com.example.zhiwu.entity.Notice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NoticeService extends ServiceImpl<NoticeDao, Notice> {

    @Autowired
    private NoticeDao noticeDao;



}