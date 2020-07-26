package com.tongji.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tongji.domain.ReturnInfo;
import com.tongji.service.UserService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

//数据录入员权限
@Api(tags = "EntryStaff")
@Controller
@RequestMapping(value = {"/entryStaff"}, method = RequestMethod.POST)
@CrossOrigin
public class EntryStaffController
{
    @Autowired
    ObjectMapper mapper;//返回json数据

    ReturnInfo info = new ReturnInfo();//返回信息
}
