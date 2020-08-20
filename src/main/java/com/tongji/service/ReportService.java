package com.tongji.service;

import com.tongji.domain.Indicator;
import com.tongji.domain.Record;
import com.tongji.domain.Report;

import java.util.List;

public interface ReportService {

    //新建
    void createReport(String reportID,String templateID,
                      String reporterID,int year,int month,
                      int commitState,int checkState);

    //创建对应的报表的所有记录
    void createRecords(String reportID);

    //
    List<Report> getReportsByStaffID(String staffID);

    //
    void deleteReportByID(String id);

    //
    Report getReportByID(String id);

    //
    void setCommitted(String id);

    //
    Record getRecordByReportIDAndIndicatorID(String reportID, String indicatorID);

    //
    Indicator getIndicatorByID(String indicatorID);

    //
    void updateRecordData(String recordID,String recordData);

    //
    void updateSourceData(String recordID,String srcData);

    //
    void updateReportCheckState(String reportID,int checkState);

    //根据传入的客户的ID返回想要的信息
    List<Report> getDetailedReports(String clientID);

    //单独处理报表，获得要提交的数据
    void accessReport(Report report);
}
