package com.tongji.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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

@Api(tags = "ESG的测试")
@Controller
@RequestMapping(value = {"/user"}, method = RequestMethod.POST)
@CrossOrigin
public class UserController
{
    @Autowired
    UserService userService;

    @RequestMapping({"/register"})
    @ResponseBody
    public Object register() throws JsonProcessingException
    {
        User user = userService.getUserById(1);
        //返回json数据
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(user);
    }

    @RequestMapping({"/login"})
    @ResponseBody
    public Object login(Integer id) throws JsonProcessingException
    {
        System.out.println(id);
        User user = userService.getUserById(1);
        //返回json数据
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(user);
    }
}
