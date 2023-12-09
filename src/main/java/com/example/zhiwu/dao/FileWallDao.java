package com.example.zhiwu.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.zhiwu.entity.FileWall;
import org.springframework.stereotype.Repository;

//@Mapper  这个注解统一写到 MybatisPlusConfig里  不用每个Dao都写
@Repository
public interface FileWallDao extends BaseMapper<FileWall> {


}
