package com.tongji.service.impl;

import com.tongji.domain.User;
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
    public User getUserById(String id)
    {
        return null;
    }

    @Override
    public User login(String username, String password)
    {
        User user=null;
        try
        {
            user=userMapper.login(username, password);
            System.out.println(user);
        }
        catch (Exception e)
        {
        }
        return user;
    }
}
