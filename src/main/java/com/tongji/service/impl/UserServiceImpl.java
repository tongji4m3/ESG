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
    public User getUserById(Integer id)
    {
        return userMapper.getUserById(id);
    }
}
