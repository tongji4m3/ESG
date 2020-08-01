package com.tongji.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tongji.domain.ReturnInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

//二级管理者权限
@Api(tags = "SecondAdministrator")
@Controller
@RequestMapping(value = {"/secondAdministrator"}, method = RequestMethod.POST)
@CrossOrigin
public class SecondAdministrator
{
    @Autowired
    ObjectMapper mapper;//返回json数据
    ReturnInfo info=new ReturnInfo();//返回信息

    //---------管理数据录入员-------------------
    @ApiOperation(value = "createEntryStaff", notes ="新建一个数据录入员")
    @RequestMapping({"/createEntryStaff"})
    @ResponseBody
    public Object createEntryStaff(String clientId,String username, String password) throws JsonProcessingException
    {
        //往user表增加字段并且标识是数据录入员
        return mapper.writeValueAsString(info);
    }

    @ApiOperation(value = "selectEntryStaff", notes ="选择一个数据录入员")
    @RequestMapping({"/selectEntryStaff"})
    @ResponseBody
    public Object selectEntryStaff(String clientId) throws JsonProcessingException
    {
        //返回对应公司的数据录入员
        return mapper.writeValueAsString(info);
    }

    @ApiOperation(value = "deleteEntryStaff", notes ="删除数据录入员")
    @RequestMapping({"/deleteEntryStaff"})
    @ResponseBody
    public Object deleteEntryStaff(String clientId,String entryStaffId) throws JsonProcessingException
    {
        //删除对应公司的数据录入员
        return mapper.writeValueAsString(info);
    }

    //----------审核报表-----------

    @ApiOperation(value = "getReports", notes ="得到所有已经提交的报表")
    @RequestMapping({"/getReports"})
    @ResponseBody
    public Object getReports(String clientId) throws JsonProcessingException
    {
        //从clientId查询所有template,再查所有report,并且返回提交过的
        //需要哪些信息得看需求
        return mapper.writeValueAsString(info);
    }

    @ApiOperation(value = "checkReport", notes ="点击某个报表时,审核已经提交的报表")
    @RequestMapping({"/checkReport"})
    @ResponseBody
    public Object checkReport(String reportID) throws JsonProcessingException
    {
        //把审核状态改为1
        return mapper.writeValueAsString(info);
    }
}
