package com.welove.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "user_love") // 一般mybatis plus会根据 类名 找 表名
@ToString
public class UserLove {
    //自增
    //主键
    private Integer user1Id;
    private Integer user2Id;
    private String determineTime;

}
