package com.tongji.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tongji.domain.Report;
import com.tongji.domain.ReturnInfo;
import com.tongji.domain.User;
import com.tongji.service.ClientService;
import com.tongji.service.ReportService;
import com.tongji.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.jws.soap.SOAPBinding;
import java.util.List;
import java.util.UUID;

//二级管理者权限
@Api(tags = "SecondAdministrator")
@Controller
@RequestMapping(value = {"/secondAdministrator"}, method = RequestMethod.POST)
@CrossOrigin
public class SecondAdministrator
{
    //对user的功能自动注入
    @Autowired
    UserService userService;

    @Autowired
    ReportService reportService;

    @Autowired
    ClientService clientService;

    @Autowired
    ObjectMapper mapper;//返回json数据


    //---------管理数据录入员-------------------
    @ApiOperation(value = "createEntryStaff", notes ="二级管理员新建一个数据录入员")
    @RequestMapping({"/createEntryStaff"})
    @ResponseBody
    public Object createEntryStaff(String clientId, String username, String password)
            throws JsonProcessingException
    {

        //新建返回消息
        ReturnInfo info = new ReturnInfo();

        //userName就是用户的账户
        boolean isExist = userService.findUserByUsername(username);

        if(isExist){
            info.setStatus(-1);
            info.setMessage("用户名重复");
        }else{
            try {
                userService.createEntryStaff(UUID.randomUUID().toString(),clientId,username,password);
            }catch (Exception e){
                info.setStatus(-1);
                info.setMessage("创建数据录入员失败");
            }
        }

        return mapper.writeValueAsString(info);
    }

    @ApiOperation(value = "selectEntryStaff", notes ="选择一个数据录入员")
    @RequestMapping({"/selectEntryStaff"})
    @ResponseBody
    public Object selectEntryStaff(String clientId) throws JsonProcessingException
    {
        ReturnInfo info = new ReturnInfo();

        List<User> entryStaff = userService.getEntryStaffByClientID(clientId);

        if(entryStaff.size() == 0){
            info.setStatus(-1);
            info.setMessage("找不到数据录入员");
        }else{

            info.putData("entryStaff",entryStaff);
        }

        return mapper.writeValueAsString(info);
    }

    @ApiOperation(value = "deleteEntryStaff", notes ="删除数据录入员")
    @RequestMapping({"/deleteEntryStaff"})
    @ResponseBody
    public Object deleteEntryStaff(String clientId,String entryStaffId) throws JsonProcessingException
    {
        ReturnInfo info = new ReturnInfo();
        //首先检查要删除的ID是否存在
        User u = userService.getUserByID(entryStaffId);
        if(u == null){
            info.setStatus(-1);
            info.setMessage("要删除的数据录入员不存在");
        }else{
            //删除user表和userInfo表中的数据录入员的信息
            userService.deleteEntryStaff(entryStaffId,clientId);

            userService.deleteEntryStaffInfo(entryStaffId);
        }

        return mapper.writeValueAsString(info);
    }

    //----------审核报表-----------

    @ApiOperation(value = "getReports", notes ="得到所有已经提交的报表")
    @RequestMapping({"/getReports"})
    @ResponseBody
    public Object getReports(String clientId) throws JsonProcessingException
    {
        ReturnInfo info = new ReturnInfo();

        List<User> l = userService.getEntryStaffByClientID(clientId);
        if(l.size() ==0 ){
            info.setStatus(-1);
            info.setMessage("该公司没有报表");
        }else {
            try {
                List<Report> r = reportService.getDetailedReports(clientId);
                info.putData("reports",r);
            }catch (Exception e){
                info.setStatus(-1);
                info.setMessage("获取失败");
            }
        }
        return mapper.writeValueAsString(info);
    }

    @ApiOperation(value = "checkReport", notes ="点击某个报表时,审核已经提交的报表")
    @RequestMapping({"/checkReport"})
    @ResponseBody
    //0表示未审核 1表示审核通过  2表示什么未通过
    public Object checkReport(String reportID,int checkState) throws JsonProcessingException
    {
        ReturnInfo info = new ReturnInfo();
        //
        Report report = reportService.getReportByID(reportID);
        if(report == null){
            info.setStatus(-1);
            info.setMessage("报表不存在");
        }else{
            //更新状态
            try{
                reportService.updateReportCheckState(reportID,checkState);
            }catch (Exception e){
                info.setMessage("审核提交失败");
                info.setStatus(-1);
            }
        }
        return mapper.writeValueAsString(info);
    }
}
