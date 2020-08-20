package com.tongji.mapper;

import com.tongji.domain.User;
import com.tongji.domain.UserInfo;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface UserMapper
{
    @Select("select * from user where userID=#{id}")
    User getUserByID(String id);

    @Select("select * from user where clientID=#{clientID}")
    List<User> getUserByClientID(String clientID);

    @Select("select * from user where userAccount=#{username} and userPassword=#{password}")
    User login(String username, String password);

    @Select("select * from userinfo where userID=#{id}")
    UserInfo getUserInfo(String userID);

    @Update("update userinfo set userName=#{userName},userPhone=#{userPhone},userEmail=#{userEmail}  where userID=#{userID}")
    int update(UserInfo userInfo);

    @Insert("insert into user(userID,userAccount,userPassword,userAuth,clientID) values(#{userID},#{username},#{password},1,#{clientID})")
    void createClient(String userID,String clientID, String username, String password);

    @Delete("delete from user where userID=#{userID}")
    void deleteClient(String userID);

    @Delete("delete from userinfo where userID=#{userID}")
    void deleteClientInfo(String userID);

    @Select("select * from user where userAccount=#{username}")
    User findUserByUsername(String username);

    @Insert("insert into userinfo(userID,userName,userPhone,userEmail) values(#{userID},#{userName},#{userPhone},#{userEmail})")
    void addUserInfo(UserInfo userInfo);

    //创建一个数据录入员，此时授权是2
    @Insert("insert into user(userID,userAccount,userPassword,userAuth,clientID) values(#{userID},#{userAccount},#{password},2,#{clientID})")
    void createEntryStaff(String userID,String clientID,String userAccount,String password);

    //根据客户的id，寻找数据录入员,注意此时的用户优先级为2
    @Select("select * from user where clientID=#{clientID} and userAuth=2")
    List<User> getEntryStaffByClientID(String clientID);

    //删除指定的数据录入员
    @Delete("delete from user where userID=#{staffID} and clientID=#{clientID}")
    void deleteEntryStaff(String clientID,String staffID);

    //删除指定的user在Info表中的属性
    @Delete("delete from userinfo where userId=#{staffID}")
    void deleteEntryStaffInfo(String staffID);
}
