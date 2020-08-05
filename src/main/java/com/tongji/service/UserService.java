package com.tongji.service;

import com.tongji.domain.User;

import java.util.List;

public interface UserService
{
    User getUserById(String id);

    User login(String username, String password);
}
