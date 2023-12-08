package com.welove.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "admin") // 一般mybatis plus会根据 类名 找 表名
@ToString
public class Admin {
    //自增
    //主键
    @TableId(type= IdType.AUTO)
    private Integer adminId;
    private String email;
    private String adminName;
    private String adminPassword;

}
