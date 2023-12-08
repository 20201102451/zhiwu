package com.welove.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.welove.entity.UserLove;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

@Repository
public interface UserLoveDao extends BaseMapper<UserLove> {
}
