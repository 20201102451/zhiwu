package com.example.zhiwu.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.zhiwu.entity.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends BaseMapper<User> {

//    User queryOne(String username);
//
//    int insertOne(User user);
//
//    // 返回最高级结点的id
//    List<Integer> queryAllNodesByUsername(@Param("username") String username);
//
//    //根据用户id获得用户最近的五条搜索记录
//    List<String> getUserQuery(@Param("userid")Integer userid);
//    //向数据库添加用户的搜索记录
//    //getUserQueryCount得到当前用户的搜索条数，如果超过五条就修改，小于五条就插入
//    int getUserQueryCount(@Param("userid")Integer userid);
//    void updateUserQuery(@Param("userid")Integer userid,@Param("query")String query,@Param("time")Double time);
//    void addUserQuery(@Param("userid")Integer userid,@Param("query")String query,@Param("time")Double time);
}
