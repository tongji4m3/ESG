package com.tongji.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tongji.domain.*;
import com.tongji.service.ReportService;
import com.tongji.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.UUID;

//数据录入员权限
@Api(tags = "Reporter")
@Controller
@RequestMapping(value = {"/reporter"}, method = RequestMethod.POST)
@CrossOrigin
public class ReporterController
{
    @Autowired
    UserService userService;

    @Autowired
    ReportService reportService;

    @Autowired
    ObjectMapper mapper;//返回json数据

    //-------------报表相关--------------
    @ApiOperation(value = "createReport", notes ="创建一个报表,传入的reporterID为自己登录的id" +
            "模板为自己公司下的某个模板id" +
            "日期为前端所选,例如2020年9月的" +
            "审核,提交状态初始化为0")
    @RequestMapping({"/createReport"})
    @ResponseBody
    public Object createReport(String entryStaffID,String templateID,int year ,int month) throws JsonProcessingException
    {
        //根据数据录入员的id，创建一个新的id
        ReturnInfo info = new ReturnInfo();

        //首先判断传入的数据录入员的id是否符合标准
        User u = userService.getUserByID(entryStaffID);
        if(u == null){
            info.setStatus(-1);
            info.setMessage("数据录入员不存在");
        }else if(u.getUserAuth() != 2){
            info.setStatus(-1);
            info.setMessage("不是数据录入员");
        }else{
            try {
                String reportID = UUID.randomUUID().toString();
                //同时创建多个报表和记录
                reportService.createReport(reportID,templateID,entryStaffID,year,month,0,0);
                reportService.createRecords(reportID);
            }catch (Exception e) {
                info.setStatus(-1);
                info.setMessage("创建失败");
            }
        }
        return mapper.writeValueAsString(info);
    }


    @ApiOperation(value = "selectReports", notes ="返回和自己相关的所有报表," +
            "一开始就展示,然后前端可以对每一项进行删除或者查看详情或者提交操作" +
            "查看详情可以调用showReportDetails方法")
    @RequestMapping({"/selectReports"})
    @ResponseBody
    public Object selectReports(String reporterID) throws JsonProcessingException
    {
        ReturnInfo info = new ReturnInfo();

        List<Report> reports = reportService.getReportsByStaffID(reporterID);
        if(reports.size()==0){
            info.setStatus(-1);
            info.setMessage("该数据录入员没有报表");
        }else{
            try{
                info.putData("reports",reports);
            }catch (Exception e){
                info.setStatus(-1);
                info.setMessage("查询失败");
            }
        }
        return mapper.writeValueAsString(info);
    }

    @ApiOperation(value = "deleteReport", notes ="删除某个报表")
    @RequestMapping({"/deleteReport"})
    @ResponseBody
    public Object deleteReport(String reportID) throws JsonProcessingException
    {
        ReturnInfo info = new ReturnInfo();

        Report r = reportService.getReportByID(reportID);
        if(r==null){
            info.setMessage("报表不存在");
            info.setStatus(-1);
        }else{
            try{
                //试一试这个级联删除
                reportService.deleteReportByID(reportID);
            }catch (Exception e){
                info.setMessage("删除失败");
                info.setStatus(-1);
            }
        }

        return mapper.writeValueAsString(info);
    }

    @ApiOperation(value = "commitReport", notes ="提交某个报表")
    @RequestMapping({"/commitReport"})
    @ResponseBody
    public Object commitReport(String reportID) throws JsonProcessingException
    {
        ReturnInfo info = new ReturnInfo();

        Report r = reportService.getReportByID(reportID);
        if(r==null)
        {
            info.setStatus(-1);
            info.setMessage("报表不存在");
        }else if(r.getCommitState()==1)
        {
            info.setStatus(-1);
            info.setMessage("报表已经提交");
        }else{
            try{
                reportService.setCommitted(reportID);
            }catch (Exception e)
            {
                info.setMessage("提交失败");
                info.setStatus(-1);
            }
        }
        return mapper.writeValueAsString(info);
    }

    //-------------查看报表详细数据----------------
    @ApiOperation(value = "showReportDetails", notes ="查看报表详细数据" +
            "这里一般会返回该报表包含的指标信息,前端可以为每个指标添加一个点击事件" +
            "点击时进入该指标的数据录入界面")
    @RequestMapping({"/showReportDetails"})
    @ResponseBody
    public Object showReportDetails(String reportID,String indicatorID) throws JsonProcessingException
    {
        ReturnInfo info = new ReturnInfo();
        //从报表中找到模板,从而找到所有指标Id,再从指标Id得到了指标详细信息,或者是指标目录树
        Record record = reportService.getRecordByReportIDAndIndicatorID(reportID,indicatorID);
        Indicator indicator =  reportService.getIndicatorByID(indicatorID);
        if(record == null){
            info.setStatus(-1);
            info.setMessage("相关指标不存在");
        }else{
            try{
                info.putData("record",record);
                info.putData("indicator",indicator);
            }catch (Exception e){
                info.setStatus(-1);
                info.setMessage("查询失败");
            }
        }

        return mapper.writeValueAsString(info);
    }


    //----------录入数据-----------
    @ApiOperation(value = "inputData", notes ="为某个报表录入相关的数据")
    @RequestMapping({"/inputData"})
    @ResponseBody
    public Object inputData(String reportID,String indicatorID,String recordData) throws JsonProcessingException
    {
        ReturnInfo info = new ReturnInfo();
        //就是针对某一个报表和相关的指标，然后输入一个值
        Record record = reportService.getRecordByReportIDAndIndicatorID(reportID,indicatorID);
        Indicator indicator = reportService.getIndicatorByID(indicatorID);

        //根据不同的数据类型进行不同的更新方式
        if(record==null){
            info.setStatus(-1);
            info.setMessage("相关指标不存在");
        }else{
            try{
                if(record.getRecordType() == 0) {
                    reportService.updateRecordData(record.getRecordID(),recordData);
                }else if(record.getRecordType() ==1){
                    reportService.updateRecordData(record.getRecordID(),recordData);
                    //派生数据不会处理
                    //遗留问题等待处理

                }
            }catch (Exception e){
                info.setStatus(-1);
                info.setMessage("录入数据失败");
            }
        }
        return mapper.writeValueAsString(info);
    }
}
