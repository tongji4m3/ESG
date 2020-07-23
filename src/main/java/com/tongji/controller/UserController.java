package com.tongji.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tongji.domain.ReturnInfo;
import com.tongji.domain.User;
import com.tongji.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.Map;

@Api(tags = "User")
@Controller
@RequestMapping(value = {"/user"}, method = RequestMethod.POST)
@CrossOrigin
public class UserController
{
    @Autowired
    UserService userService;

    @Autowired
    ObjectMapper mapper;//返回json数据

    ReturnInfo info=new ReturnInfo();//返回信息

    @RequestMapping({"/register"})
    @ResponseBody
    public Object register(String username, String password) throws JsonProcessingException
    {
        info.setMsg("注册成功");
        info.setStatus(1);

        return mapper.writeValueAsString(info);
    }

    @RequestMapping({"/login"})
    @ResponseBody
    public Object login(String username, String password) throws JsonProcessingException
    {
        User user = userService.getUserById(1);
        user.setUsername(username);
        user.setPassword(password);

        return mapper.writeValueAsString(user);
    }
}
