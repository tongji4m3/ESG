package com.tongji.mapper;

import com.tongji.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface UserMapper
{
    @Select("select * from user where userID=#{id}")
    User getUserById(String id);

    @Select("select * from user where userAccount=#{username} and userPassword=#{password}")
    User login(String username, String password);
}
