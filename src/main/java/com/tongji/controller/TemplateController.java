package com.tongji.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tongji.domain.ReturnInfo;
import com.tongji.service.ClientService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Api(tags = "Template")
@Controller
@RequestMapping(value = {"/template"}, method = RequestMethod.POST)
@CrossOrigin
public class TemplateController
{

    @Autowired
    ObjectMapper mapper;//返回json数据

    ReturnInfo info=new ReturnInfo();//返回信息

    @ApiOperation(value = "create", notes ="")
    @RequestMapping({"/create"})
    @ResponseBody
    public Object create(String templateName,String templateIndustry,String templateClient) throws JsonProcessingException
    {
        //直接存储在模板表中,Id自动生成
        return mapper.writeValueAsString(info);
    }

    @ApiOperation(value = "select", notes ="传入要查找的模板名称(可模糊查询)")
    @RequestMapping({"/select"})
    @ResponseBody
    public Object select(String templateName) throws JsonProcessingException
    {
        //因为是模糊查询,所以返回的是数组,每个数组都包含下面的项
        //根据模板名称返回模板详情,包括从模板表中查到的模板信息,还有模板指标表中查询
        //到的指标id,再去指标表中查询对应指标的具体信息
        //一个模板指标可能很多
        // 前端可以为每个指标都创建一个面板,点击则调用select方法
        //             查询该指标的详细信息
        return mapper.writeValueAsString(info);
    }

    @ApiOperation(value = "selectTemplates", notes ="查询某个客户的所有模板,便于添加指标")
    @RequestMapping({"/selectTemplates"})
    @ResponseBody
    public Object selectTemplates(String clientName) throws JsonProcessingException
    {
        return mapper.writeValueAsString(info);
    }

    @ApiOperation(value = "selectByClient", notes ="传入要查找的客户名称(不可模糊查询)")
    @RequestMapping({"/selectByClient"})
    @ResponseBody
    public Object selectByClient(String clientName) throws JsonProcessingException
    {
        //客户名称得到客户Id
        //因为每个客户可能包含不止一个模板,所以返回的是数组,每个数组都包含下面的项
        //再在模板表中根据clientId得到模板的相关信息,并且返回
        return mapper.writeValueAsString(info);
    }
}
