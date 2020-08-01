package com.tongji.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tongji.domain.Record;
import com.tongji.domain.Report;
import com.tongji.domain.ReturnInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

//数据录入员权限
@Api(tags = "Reporter")
@Controller
@RequestMapping(value = {"/reporter"}, method = RequestMethod.POST)
@CrossOrigin
public class ReporterController
{
    @Autowired
    ObjectMapper mapper;//返回json数据

    ReturnInfo info = new ReturnInfo();//返回信息

    //-------------报表相关--------------
    @ApiOperation(value = "createReport", notes ="创建一个报表,传入的reporterID为自己登录的id" +
            "模板为自己公司下的某个模板id" +
            "日期为前端所选,例如2020年9月的" +
            "审核,提交状态初始化为0")
    @RequestMapping({"/createReport"})
    @ResponseBody
    public Object createReport(Report report) throws JsonProcessingException
    {
        return mapper.writeValueAsString(info);
    }


    @ApiOperation(value = "selectReports", notes ="返回和自己相关的所有报表," +
            "一开始就展示,然后前端可以对每一项进行删除或者查看详情或者提交操作" +
            "查看详情可以调用showReportDetails方法")
    @RequestMapping({"/selectReports"})
    @ResponseBody
    public Object selectReports(String reporterID) throws JsonProcessingException
    {
        //返回提交状态和审核状态
        //当点击某个报表时,可以查看某个报表的指标数据
        return mapper.writeValueAsString(info);
    }

    @ApiOperation(value = "deleteReport", notes ="删除某个报表")
    @RequestMapping({"/deleteReport"})
    @ResponseBody
    public Object deleteReport(String reportID) throws JsonProcessingException
    {
        return mapper.writeValueAsString(info);
    }

    @ApiOperation(value = "commitReport", notes ="提交某个报表")
    @RequestMapping({"/commitReport"})
    @ResponseBody
    public Object commitReport(String reportID) throws JsonProcessingException
    {
        //把提交状态改为1
        return mapper.writeValueAsString(info);
    }

    //-------------查看报表详细数据----------------
    @ApiOperation(value = "showReportDetails", notes ="查看报表详细数据" +
            "这里一般会返回该报表包含的指标信息,前端可以为每个指标添加一个点击事件" +
            "点击时进入该指标的数据录入界面")
    @RequestMapping({"/showReportDetails"})
    @ResponseBody
    public Object showReportDetails(String reportID) throws JsonProcessingException
    {
        //从报表中找到模板,从而找到所有指标Id,再从指标Id得到了指标详细信息,或者是指标目录树
        return mapper.writeValueAsString(info);
    }


    //----------录入数据-----------
    @ApiOperation(value = "inputData", notes ="为某个报表录入相关的数据")
    @RequestMapping({"/inputData"})
    @ResponseBody
    public Object inputData(Record record) throws JsonProcessingException
    {
        //录入相关的数据
        return mapper.writeValueAsString(info);
    }
}
