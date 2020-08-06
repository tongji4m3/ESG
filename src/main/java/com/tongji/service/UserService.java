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
}
