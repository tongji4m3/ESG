package com.tongji.service.impl;

import com.tongji.domain.Indicator;
import com.tongji.domain.Record;
import com.tongji.domain.Report;
import com.tongji.mapper.IndicatorMapper;
import com.tongji.mapper.ReportMapper;
import com.tongji.mapper.TemplateMapper;
import com.tongji.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

@Service
public class ReportServiceImpl implements ReportService {
    @Autowired
    private ReportMapper reportMapper;

    @Autowired
    private TemplateMapper templateMapper;

    @Autowired
    private IndicatorMapper indicatorMapper;

    @Override
    public void createReport(String reportID, String templateID, String reporterID, int year, int month, int commitState, int checkState) {
        reportMapper.createReport(reportID,templateID,reporterID,year,month,commitState,checkState);
    }

    //0表示是定量(number) 1表示定性(text)
    @Override
    public void createRecords(String reportID) {
        //先获取对应的报表的所有信息
        Report report = reportMapper.getReportByID(reportID);
        List<String> indicators = reportMapper.getIndicatorsByTemplateID(report.getTemplateID());
        for (String indicID: indicators) {
            //获得此次要处理的指标的详细信息
            Indicator indicator = reportMapper.getIndicatorDetailedByID(indicID);
            //确定record的类型，是定性的还是定量的
            int type = 0;
            if(indicator.getIndicatorType() == 1){
                type =1;
            }
            reportMapper.createRecord(UUID.randomUUID().toString(),reportID,indicID,type,"","");

        }
    }

    @Override
    public List<Report> getReportsByStaffID(String staffID) {
        return reportMapper.getReportsByReporterID(staffID);
    }

    @Override
    public void deleteReportByID(String id) {
        reportMapper.deleteReportByID(id);
    }

    @Override
    public Report getReportByID(String id) {
        return reportMapper.getReportByID(id);
    }

    @Override
    public void setCommitted(String id) {
        reportMapper.setCommitted(id);
    }

    @Override
    public Record getRecordByReportIDAndIndicatorID(String reportID, String indicatorID) {
        return reportMapper.getRecordByReportIDAndIndicatorID(reportID,indicatorID);
    }

    @Override
    public Indicator getIndicatorByID(String indicatorID) {
        return reportMapper.getIndicatorDetailedByID(indicatorID);
    }

    @Override
    public void updateRecordData(String recordID, String recordData) {
        reportMapper.updateRecordData(recordID,recordData);
    }

    @Override
    public void updateSourceData(String recordID, String srcData) {
        reportMapper.updateSourceData(recordID,srcData);
    }

    @Override
    public void updateReportCheckState(String reportID, int checkState) {
        reportMapper.updateCheckState(reportID,checkState);
    }

    //要返回怎样的信息我也不知道，就先没有做，先留下来这两个接口
    @Override
    public List<Report> getDetailedReports(String clientID) {

        List<Report> reportList = new LinkedList<>();
        //先找到公司里的所有数据录入员的id
        List<String> reporters = reportMapper.getEntryStaffByClientID(clientID);
        //对每一个数据录入员所管理的reports进行一一处理
        for (String reporter :
                reporters) {
            List<Report> reports =  reportMapper.getReportsCommittedByReporterID(reporter);
            //
            reportList.addAll(reports);
        }
        return reportList;

    }

    @Override
    public void accessReport(Report report) {

    }
}
