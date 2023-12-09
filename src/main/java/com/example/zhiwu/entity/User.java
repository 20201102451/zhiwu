package com.example.zhiwu.entity;

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
@TableName(value = "user") // 一般mybatis plus会根据 类名 找 表名
@ToString
public class User {
    //自增
    //主键
    @TableId(type= IdType.AUTO)
    private Integer userId;
    private String studentId;
    private String userName;
    private String userPassword;

    private Integer sex;
    private String number;
    private Integer height;
    private Double weight;
    private String avatar;
    private String userIntro;
    private String declarationLove;

    private String passwordQuestion;
    private String passwordAnswer;

}
