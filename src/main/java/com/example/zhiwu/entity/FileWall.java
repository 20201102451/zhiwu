package com.example.zhiwu.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("file_wall")
public class FileWall {
    @TableId(type = IdType.AUTO)
    private Integer  fileId;
    private Integer  userId;
    private String  fileUrl;
    private String createTime;
    private String md5;

}
