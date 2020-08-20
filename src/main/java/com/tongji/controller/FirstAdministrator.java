package com.tongji.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tongji.domain.Client;
import com.tongji.domain.Indicator;
import com.tongji.domain.ReturnInfo;
import com.tongji.domain.Template;
import com.tongji.service.ClientService;
import com.tongji.service.IndicatorService;
import com.tongji.service.TemplateService;
import com.tongji.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;


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
    UserService userService;

    @Autowired
    TemplateService templateService;

    @Autowired
    IndicatorService indicatorService;

    @Autowired
    ObjectMapper mapper;//返回json数据

    @ApiOperation("创建一个新的客户")
    @RequestMapping({"/createClient"})
    @ResponseBody
    public Object createClient(String clientName, String clientLocation, String clientIntro,
                               String username, String password) throws JsonProcessingException
    {
        //往client表中添加client信息,clientID用UUID生成,会往client中加入二级管理员ID,
        //并且往user中添加新的信息,id为上述id,指出userType为二级管理员
        ReturnInfo info = new ReturnInfo();//返回信息
        //先查看用户名和clientName是否重复
        boolean isUserExit = userService.findUserByUsername(username);
        boolean isClientExit = clientService.findByName(clientName);
        if (isUserExit)
        {
            info.setStatus(-1);
            info.setMessage("用户名重复!");
        }
        else if (isClientExit)
        {
            info.setStatus(-1);
            info.setMessage("clientName重复!");
        }
        else
        {
            UUID uuid = UUID.randomUUID();
            try
            {
                Client client = new Client(uuid.toString(), clientName, clientLocation, clientIntro);
                clientService.create(client);
                userService.createClient(UUID.randomUUID().toString(), uuid.toString(), username, password);
            }
            catch (Exception e)
            {
                info.setStatus(-1);
                info.setMessage("创建client失败!");
            }
        }
        return mapper.writeValueAsString(info);
    }

    @ApiOperation("删除client,并且级联删除对应的user,对应的模板")
    @RequestMapping({"/deleteClient"})
    @ResponseBody
    public Object deleteClient(String clientID) throws JsonProcessingException
    {
        //删除client,级联删除对应的user,对应的模板
        ReturnInfo info = new ReturnInfo();
        boolean flag = clientService.delete(clientID);
        //如果客户不存在,就不删除模板
        if (!flag)
        {
            info.setStatus(-1);
            info.setMessage("删除client失败!");
        }
        else
        {
            templateService.deleteTemplateByClientID(clientID);//删除client对应的模板
            userService.deleteClient(clientID);//删除client对应的二级管理员和数据录入员
        }
        return mapper.writeValueAsString(info);
    }

    @ApiOperation("可以通过该方法展示所有的客户,一级管理员可以通过" +
            "对此操作,为相应客户添加模板")
    @RequestMapping({"/selectClients"})
    @ResponseBody
    public Object selectClients() throws JsonProcessingException
    {
        ReturnInfo info = new ReturnInfo();

        List<Client> list = clientService.findAll();
        if (list.size() == 0)
        {
            info.setStatus(-1);
            info.setMessage("client列表为空");
        }
        else
        {
            info.putData("clients", list);
        }
        return mapper.writeValueAsString(info);
    }


    //--------------模板操作--------------------
    @ApiOperation("为特定公司生成模板")
    @RequestMapping({"/createTemplate"})
    @ResponseBody
    public Object createTemplate(String templateName, String templateInfo, String clientID) throws JsonProcessingException
    {
        //模板名称必须唯一
        boolean isExit = templateService.findTemplateByName(templateName);
        ReturnInfo info = new ReturnInfo();//返回信息
        if (isExit)
        {
            info.setStatus(-1);
            info.setMessage("模板名称重复!");
        }
        else
        {
            templateService.create(UUID.randomUUID().toString(), templateName, templateInfo, clientID);
        }
        return mapper.writeValueAsString(info);
    }

    @ApiOperation("在页面上可以直接点击搜索,输入要查找的模板名称(可模糊查询).返回匹配的模板列表,如果要查看某个模板的具体指标信息" +
            "则再点击那个模板,调用selectIndicators(templateID)")
    @RequestMapping({"/selectTemplate"})
    @ResponseBody
    public Object selectTemplate(String templateName) throws JsonProcessingException
    {
        //因为是模糊查询,所以返回的是数组,每个数组都包含下面的项
        //根据模板名称返回模板详情,包括从模板表中查到的模板信息
        ReturnInfo info = new ReturnInfo();
        List<Template> list = templateService.findTemplatesByLike("%" + templateName + "%");
        Map<String, Template> map = new HashMap<>();
        for (Template template : list)
        {
            String clientName = clientService.findClientName(template.getClientID());
            map.put(clientName, template);
        }
        if (list.size()==0)
        {
            info.setStatus(-1);
            info.setMessage("找不到模板");
        }
        else
        {
            info.putData("templates", map);
        }
        return mapper.writeValueAsString(info);
    }

    @ApiOperation("传入模板ID,将之删除")
    @RequestMapping({"/deleteTemplate"})
    @ResponseBody
    public Object deleteTemplate(String templateID) throws JsonProcessingException
    {
        ReturnInfo info = new ReturnInfo();
        boolean isDelete=templateService.deleteTemplate(templateID);
        if(!isDelete)
        {
            info.setStatus(-1);
            info.setMessage("删除模板失败");
        }

        return mapper.writeValueAsString(info);
    }

    @ApiOperation("可以进入页面后调用传用selectClients()得到client信息,对某个client查看详细就点击他" +
            "传入要查找的clientID,得到该公司所有模板的简单信息,后续可以再单独点击每个模板,查看模板里面的详细信息")
    @RequestMapping({"/selectTemplatesByClient"})
    @ResponseBody
    public Object selectTemplatesByClient(String clientID) throws JsonProcessingException
    {
        //客户名称得到客户ID
        //再在模板表中根据clientID得到模板的相关信息,并且返回
        //因为每个客户可能包含不止一个模板,所以返回的是数组
        ReturnInfo info = new ReturnInfo();
        List<Template> list = templateService.selectTemplatesByClient(clientID);
        if(list.size()==0)
        {
            info.setStatus(-1);
            info.setMessage("该公司没有模板");
        }
        else
        {
            info.putData("templates", list);
        }
        return mapper.writeValueAsString(info);
    }


    //------------------指标管理---------------------
    @ApiOperation("返回的是指标目录树,可能只包含指标的粗略信息" +
            "前端可以为每个指标都创建一个面板,点击则调用select方法" +
            "查询该指标的详细信息")
    @RequestMapping({"/selectIndicators"})
    @ResponseBody
    public Object selectIndicators(String templateID) throws JsonProcessingException
    {
        //一个模板指标可能很多,存储的都是5级指标
        // 前端可以为每个指标都创建一个面板,点击则调用指标select方法
        //             查询该指标的详细信息
        //可以根据指标表,查询所有6级指标,再从他们parent字段找对应5级,4级...1级
        //然后返回指标树(需要约定格式,主要是前端通过对应格式在视觉上转为指标树)
        ReturnInfo info = new ReturnInfo();
        List<String> indicatorsID = indicatorService.getIndicatorsID(templateID);
        if(indicatorsID.size()==0)
        {
            info.setStatus(-1);
            info.setMessage("该模板没有指标");
        }
        else
        {
            for (String indicatorID : indicatorsID)
            {
                List<Indicator> indicatorTree = indicatorService.getIndicator(indicatorID);
                info.putData(indicatorID, indicatorTree);
            }
        }
        return mapper.writeValueAsString(info);
    }

    @ApiOperation("选择对应的模板后,前端可以在页面加载就查看默认模板里的所有指标selectIndicators(00001)," +
            "对每个指标,点一下就会加入到该模板之中,只有五级指标才能加入")
    @RequestMapping({"/createIndicator"})
    @ResponseBody
    public Object createIndicator(String templateID, String indicatorID) throws JsonProcessingException
    {
        ReturnInfo info = new ReturnInfo();
        //前端在选择模板时,指定了templateID
        //且在为模板添加指标树时,已经有了indicatorID
        //把templateID,indicatorID加入模板指标表即可
        //在template_indicator中添加
        boolean isTemplateExit=templateService.findTemplateByID(templateID);
        boolean isFiveLevelIndicator = indicatorService.isFiveLevelIndicator(indicatorID);

        if(!isTemplateExit)
        {
            info.setStatus(-1);
            info.setMessage("该模板不存在");
        }
        else if(!isFiveLevelIndicator)
        {
            info.setStatus(-1);
            info.setMessage("必须往模板中加入五级指标");
        }
        else
        {
            indicatorService.addTemplateIndicator(templateID, indicatorID);
        }
        return mapper.writeValueAsString(info);
    }

    @ApiOperation("查询某个指标的详细信息,形成目录树")
    @RequestMapping({"/selectIndicator"})
    @ResponseBody
    public Object selectIndicator(String indicatorID) throws JsonProcessingException
    {
        ReturnInfo info = new ReturnInfo();
        boolean isIndicatorExit = indicatorService.findIndicator(indicatorID);

        if(!isIndicatorExit)
        {
            info.setStatus(-1);
            info.setMessage("指标不存在");
        }
        else
        {
            List<Indicator> indicatorTree = indicatorService.getIndicator(indicatorID);
            info.putData("indicators", indicatorTree);
        }

        return mapper.writeValueAsString(info);
    }

    @ApiOperation("删除某个模板的某个指标")
    @RequestMapping({"/deleteIndicator"})
    @ResponseBody
    public Object deleteIndicator(String templateID, String indicatorID) throws JsonProcessingException
    {
        ReturnInfo info = new ReturnInfo();
        //在template_indicator中删除
        boolean isTemplateExit=templateService.findTemplateByID(templateID);
        boolean isIndicatorExit = indicatorService.findIndicator(indicatorID);

        if(!isTemplateExit)
        {
            info.setStatus(-1);
            info.setMessage("该模板不存在");
        }
        else if(!isIndicatorExit)
        {
            info.setStatus(-1);
            info.setMessage("指标不存在");
        }
        else
        {
            indicatorService.deleteTemplateIndicators(templateID, indicatorID);
        }
        return mapper.writeValueAsString(info);
    }
}
