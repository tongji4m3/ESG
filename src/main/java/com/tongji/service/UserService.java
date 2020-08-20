package com.tongji.service;

import com.tongji.domain.User;
import com.tongji.domain.UserInfo;

import java.util.List;

public interface UserService
{
    User getUserByID(String id);

    User login(String username, String password);

    UserInfo getUserInfo(String userID);

    boolean update(UserInfo userInfo);

    void createClient(String userID,String clientID, String username, String password);

    void deleteClient(String clientId);

    boolean findUserByUsername(String username);

    //创建一个数据录入员
    void createEntryStaff(String userID,String clientID,String username,String password);

    //查找在user表中的所有数据录入员的信息
    List<User> getEntryStaffByClientID(String clientID);

    //删除指定的数据录入员
    void deleteEntryStaff(String staffID,String clientID);

    //删除数据录入员相对应的信息
    void deleteEntryStaffInfo(String staffID);

}
