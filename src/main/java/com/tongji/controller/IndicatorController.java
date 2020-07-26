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

@Api(tags = "Indicator")
@Controller
@RequestMapping(value = {"/indicator"}, method = RequestMethod.POST)
@CrossOrigin
public class IndicatorController
{
    @Autowired
    ObjectMapper mapper;//返回json数据

    ReturnInfo info=new ReturnInfo();//返回信息

    @ApiOperation(value = "selectIndicators",
            notes ="返回的是指标目录树,可能只包含指标的粗略信息" +
                    "前端可以为每个指标都创建一个面板,点击则调用select方法" +
                    "查询该指标的详细信息")

    @RequestMapping({"/selectIndicators"})
    @ResponseBody
    public Object selectIndicators(Integer templateId) throws JsonProcessingException
    {
        //可以根据指标表,查询所有6级指标,再从他们parent字段找对应5级,4级...1级
        //然后返回指标树(需要约定格式,主要是前端通过对应格式在视觉上转为指标树)
        return mapper.writeValueAsString(info);
    }

    @ApiOperation(value = "create", notes ="")
    @RequestMapping({"/create"})
    @ResponseBody
    public Object create(Integer templateId,Integer indicatorId) throws JsonProcessingException
    {
        //前端在选择模板时,指定了templateId
        //且在为模板添加指标树时,已经有了indicatorId
        //把templateId,indicatorId加入模板指标表即可
        return mapper.writeValueAsString(info);
    }

    @ApiOperation(value = "select", notes ="")
    @RequestMapping({"/select"})
    @ResponseBody
    public Object select(Integer templateId,Integer indicatorId) throws JsonProcessingException
    {
        //前端在选择模板时,指定了templateId
        //且在为模板添加指标树时,已经有了indicatorId
        //把templateId,indicatorId加入模板指标表即可
        return mapper.writeValueAsString(info);
    }

    @ApiOperation(value = "delete", notes ="删除某个指标,实现级联删除")
    @RequestMapping({"/delete"})
    @ResponseBody
    public Object delete(Integer templateId,Integer indicatorId) throws JsonProcessingException
    {
        //和select差不多的操作
        //但是要级联删除,
        return mapper.writeValueAsString(info);
    }
}
