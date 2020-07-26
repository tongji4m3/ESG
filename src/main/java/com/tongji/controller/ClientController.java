package com.tongji.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tongji.domain.ReturnInfo;
import com.tongji.domain.User;
import com.tongji.service.ClientService;
import com.tongji.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Api(tags = "Client")
@Controller
@RequestMapping(value = {"/client"}, method = RequestMethod.POST)
@CrossOrigin
public class ClientController
{
    @Autowired
    ClientService clientService;

    @Autowired
    ObjectMapper mapper;//返回json数据

    ReturnInfo info=new ReturnInfo();//返回信息


    @ApiOperation(value = "create", notes ="")
    @RequestMapping({"/create"})
    @ResponseBody
    public Object create(String clientName,String clientLocation,String clientIntro,
                         String username,String password) throws JsonProcessingException
    {
        //往client表中添加client信息,clientId自动生成,会往client中加入二级管理员Id,
        //并且往user中添加新的信息,id为上述id,指出userType为二级管理员
        return mapper.writeValueAsString(info);
    }
    @ApiOperation(value = "delete", notes ="")
    @RequestMapping({"/delete"})
    @ResponseBody
    public Object delete(Integer clientId) throws JsonProcessingException
    {
        //删除client,级联删除对应的user,对应的模板
        return mapper.writeValueAsString(info);
    }
    @ApiOperation(value = "selectClients", notes ="可以通过该方法展示客户,一级管理员可以通过" +
            "对此操作,为相应客户添加模板")
    @RequestMapping({"/selectClients"})
    @ResponseBody
    public Object selectClients() throws JsonProcessingException
    {
        //返回客户信息,具体看情况
        return mapper.writeValueAsString(info);
    }
}
