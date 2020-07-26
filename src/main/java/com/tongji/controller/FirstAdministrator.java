package com.tongji.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tongji.domain.Indicator;
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


//一级管理员权限
@Api(tags = "FirstAdministrator")
@Controller
@RequestMapping(value = {"/firstAdministrator"}, method = RequestMethod.POST)
@CrossOrigin
public class FirstAdministrator
{

    @Autowired
    ClientService clientService;

    @Autowired
    ObjectMapper mapper;//返回json数据

    ReturnInfo info=new ReturnInfo();//返回信息


    @ApiOperation(value = "createClient", notes ="")
    @RequestMapping({"/createClient"})
    @ResponseBody
    public Object createClient(String clientName,String clientLocation,String clientIntro,
                         String username,String password) throws JsonProcessingException
    {
        //往client表中添加client信息,clientId自动生成,会往client中加入二级管理员Id,
        //并且往user中添加新的信息,id为上述id,指出userType为二级管理员
        return mapper.writeValueAsString(info);
    }

    @ApiOperation(value = "deleteClient", notes ="")
    @RequestMapping({"/deleteClient"})
    @ResponseBody
    public Object deleteClient(String clientId) throws JsonProcessingException
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


    //--------------模板操作--------------------
    @ApiOperation(value = "createTemplate", notes ="")
    @RequestMapping({"/createTemplate"})
    @ResponseBody
    public Object createTemplate(String templateName,String templateIndustry,String templateClient) throws JsonProcessingException
    {
        //直接存储在模板表中,Id自动生成
        return mapper.writeValueAsString(info);
    }

    @ApiOperation(value = "selectTemplate", notes ="传入要查找的模板名称(可模糊查询)")
    @RequestMapping({"/selectTemplate"})
    @ResponseBody
    public Object selectTemplate(String templateName) throws JsonProcessingException
    {
        //因为是模糊查询,所以返回的是数组,每个数组都包含下面的项
        //根据模板名称返回模板详情,包括从模板表中查到的模板信息,还有模板指标表中查询
        //到的指标id,再去指标表中查询对应指标的具体信息
        //一个模板指标可能很多
        // 前端可以为每个指标都创建一个面板,点击则调用指标select方法
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

    @ApiOperation(value = "selectTemplatesByClient", notes ="传入要查找的客户名称(不可模糊查询)")
    @RequestMapping({"/selectTemplatesByClient"})
    @ResponseBody
    public Object selectTemplatesByClient(String clientName) throws JsonProcessingException
    {
        //客户名称得到客户Id
        //因为每个客户可能包含不止一个模板,所以返回的是数组,每个数组都包含下面的项
        //再在模板表中根据clientId得到模板的相关信息,并且返回
        return mapper.writeValueAsString(info);
    }

    //------------------指标管理---------------------
    @ApiOperation(value = "selectIndicators",
            notes ="返回的是指标目录树,可能只包含指标的粗略信息" +
                    "前端可以为每个指标都创建一个面板,点击则调用select方法" +
                    "查询该指标的详细信息")

    @RequestMapping({"/selectIndicators"})
    @ResponseBody
    public Object selectIndicators(String templateId) throws JsonProcessingException
    {
        //可以根据指标表,查询所有6级指标,再从他们parent字段找对应5级,4级...1级
        //然后返回指标树(需要约定格式,主要是前端通过对应格式在视觉上转为指标树)
        return mapper.writeValueAsString(info);
    }

    @ApiOperation(value = "createIndicator", notes ="")
    @RequestMapping({"/createIndicator"})
    @ResponseBody
    public Object createIndicator(String templateId,String indicatorId) throws JsonProcessingException
    {
        //前端在选择模板时,指定了templateId
        //且在为模板添加指标树时,已经有了indicatorId
        //把templateId,indicatorId加入模板指标表即可
        return mapper.writeValueAsString(info);
    }

    @ApiOperation(value = "selectIndicator", notes ="查询某个指标的详细信息")
    @RequestMapping({"/selectIndicator"})
    @ResponseBody
    public Object selectIndicator(String indicatorId) throws JsonProcessingException
    {
        return mapper.writeValueAsString(info);
    }

    @ApiOperation(value = "deleteIndicator", notes ="删除某个指标,实现级联删除")
    @RequestMapping({"/deleteIndicator"})
    @ResponseBody
    public Object deleteIndicator(String templateId,String indicatorId) throws JsonProcessingException
    {
        //和select差不多的操作
        //但是要级联删除,所以删除某个指标时,去indicator查找他的子类
        //得到相应的id,再在template_indicator中删除
        return mapper.writeValueAsString(info);
    }

    @ApiOperation(value = "updateIndicator", notes ="更新某个指标的具体信息")
    @RequestMapping({"/updateIndicator"})
    @ResponseBody
    public Object updateIndicator(Indicator indicator) throws JsonProcessingException
    {
        //更新指标的具体内容
        return mapper.writeValueAsString(info);
    }
}
