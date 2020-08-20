package com.tongji.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tongji.domain.ReturnInfo;
import com.tongji.domain.User;
import com.tongji.domain.UserInfo;
import com.tongji.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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

    @ApiOperation("输入用户名密码进行登录" +
            "如果成功,返回的info中status=1,message=ok,data中存储用户的userID,userAuth" +
            "如果失败,则返回的info中status和msg,data为null"+
            "userAuth中0代表一级管理员,1代表二级管理员,2代表数据录入员")
    @RequestMapping({"/login"})
    @ResponseBody
    public Object login(String username, String password) throws JsonProcessingException
    {
        ReturnInfo info=new ReturnInfo();//返回信息
        User user = userService.login(username,password);
        if(user==null)
        {
            info.setStatus(-1);
            info.setMessage("用户名或密码错误");
        }
        else
        {
            info.putData("userID", user.getUserID());
            info.putData("userAuth", user.getUserAuth());
        }
        return mapper.writeValueAsString(info);
    }

    @RequestMapping({"/getUserByID"})
    @ApiOperation("对一个用户信息进行修改之前,返回他的所有账号信息和个人信息")
    @ResponseBody
    public Object getUserByID(String userID) throws JsonProcessingException
    {
        ReturnInfo info=new ReturnInfo();//返回信息
        User user = userService.getUserByID(userID);
        if(user==null)
        {
            info.setStatus(-1);
            info.setMessage("用户不存在");
        }
        else
        {
            System.out.println(user.getUserID());
            info.putData("userID", user.getUserID());
            info.putData("userAccount", user.getUserAccount());
            info.putData("userAuth", user.getUserAuth());
            info.putData("clientID", user.getClientID());


            UserInfo userInfo = userService.getUserInfo(userID);
            //这里查询用户信息不一定存在,因在第一次修改前,是不存在个人信息的
            if(userInfo!=null)
            {
                info.putData("userName",userInfo.getUserName());
                info.putData("userPhone",userInfo.getUserPhone());
                info.putData("userEmail",userInfo.getUserEmail());
            }
        }
        return mapper.writeValueAsString(info);
    }

    @RequestMapping({"/update"})
    @ApiOperation("只能修改用户的个人信息,账号相关信息不能修改")
    @ResponseBody
    public Object update(UserInfo userInfo) throws JsonProcessingException
    {
        boolean flag=userService.update(userInfo);
        ReturnInfo info=new ReturnInfo();//返回信息
        if(!flag)
        {
            info.setStatus(-1);
            info.setMessage("账号不存在!");
        }
        //将用户更新信息存入数据库
        return mapper.writeValueAsString(info);
    }
}
