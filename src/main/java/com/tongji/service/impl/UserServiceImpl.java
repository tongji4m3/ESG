package com.tongji.service.impl;

import com.tongji.domain.User;
import com.tongji.domain.UserInfo;
import com.tongji.mapper.UserMapper;
import com.tongji.service.UserService;
import com.tongji.utils.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService
{
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RedisUtils redisUtils;

    @Override
    public User getUserByID(String id)
    {
        return userMapper.getUserByID(id);
    }

    @Override
    public User login(String username, String password)
    {
        return userMapper.login(username, password);
    }

    @Override
    public UserInfo getUserInfo(String userID)
    {
        return userMapper.getUserInfo(userID);
    }

    @Override
    public boolean update(UserInfo userInfo)
    {
        String userID = userInfo.getUserID();

        //说明ID为假,不存在这个人
        if(getUserByID(userID)==null) return false;

        if(getUserInfo(userID)==null) //说明个人信息还不存在
            userMapper.addUserInfo(userInfo);
        else
            userMapper.update(userInfo);

        return true;
    }

    @Override
    public void createClient(String userID,String clientID, String username, String password)
    {
        userMapper.createClient(userID,clientID, username,password);
    }

    @Override
    public void deleteClient(String clientID)
    {
        //删除user和userInfo对应的信息
        User user = userMapper.getUserByClientID(clientID);
        if(user==null) return;
        userMapper.deleteClient(user.getUserID());
        userMapper.deleteClientInfo(user.getUserID());
    }

    @Override
    public boolean findUserByUsername(String username)
    {
        User user = userMapper.findUserByUsername(username);
        System.out.println(user);
        return user != null;//用户存在
    }
}
