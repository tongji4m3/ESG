package com.tongji.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tongji.domain.ReturnInfo;
import com.tongji.domain.User;
import com.tongji.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
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

    @ApiOperation(value = "login", notes = "输入用户名密码进行登录,status,msg标识了状态." +
            "如果成功,返回的info中status=1,message=ok,还存储了userId,userAuth" +
            "如果失败,返回status和msg")
    @RequestMapping({"/login"})
    @ResponseBody
    public Object login(String username, String password) throws JsonProcessingException
    {
        User user = userService.login(username,password);
        if(user==null)
        {
            info.setStatus(-1);
            info.setMessage("用户名或密码错误");
            info.setData(null);
        }
        else
        {
            Map<String, Object> map = new HashMap<>();//用于返回实体的部分信息
            map.put("userId", user.getUserId());
            map.put("userAuth", user.getUserAuth());
            info.setStatus(1);
            info.setMessage("ok");
            info.setData(map);
        }
        return mapper.writeValueAsString(info);
    }

    @RequestMapping({"/select"})
    @ResponseBody
    public Object select(String userId) throws JsonProcessingException
    {
        //在修改之前返回用户信息
        return mapper.writeValueAsString(info);
    }

    @RequestMapping({"/update"})
    @ResponseBody
    public Object update(User user) throws JsonProcessingException
    {
        //将用户更新信息存入数据库
        return mapper.writeValueAsString(info);
    }
}
