package com.welove.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.welove.entity.Admin;
import com.welove.entity.User;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminDao extends BaseMapper<Admin> {

}
