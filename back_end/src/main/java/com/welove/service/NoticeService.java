package com.welove.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.welove.common.Result;
import com.welove.dao.NoticeDao;
import com.welove.entity.Notice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NoticeService extends ServiceImpl<NoticeDao, Notice> {

    @Autowired
    private NoticeDao noticeDao;



}