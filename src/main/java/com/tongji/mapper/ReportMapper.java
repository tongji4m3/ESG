package com.tongji.mapper;

import com.sun.webkit.dom.RectImpl;
import com.tongji.domain.Indicator;
import com.tongji.domain.Record;
import com.tongji.domain.Report;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ReportMapper {

    //向report表中添加一条新的信息
    @Insert("insert into report(reportID,templateID,reporterID,reportYear,reportMonth,commitState,checkState) values(#{reportID},#{templateID},#{reporterID},#{year},#{month},#{commitState},#{checkState})")
    void createReport(String reportID,String templateID,String reporterID,int year,int month,
                      int commitState,int checkState);

    //根据数据录入员的id 返回所有由他所创建的报表
    @Select("select * from report where reporterID=#{reporter}")
    List<Report> getReportsByReporterID(String reporter);

    //删除指定的ID的报表
    @Delete("delete from report where reportID=#{id}")
    void deleteReportByID(String id);

    @Select("select * from report where reportID=#{id}")
    Report getReportByID(String id);

    //将某一个报表的状态更新成为以提交
    @Update("update report set commitState=1 where reportID=#{id}")
    void setCommitted(String id);

    //向record表中插入数据
    @Insert("insert into record(recordID,reportID,indicatorID,recordType,sourceRecordData,recordData) values(#{recordID},#{reportID},#{indicatorID},#{recordType},#{sourceRecordData},#{recordData})")
    void createRecord(String recordID,String reportID,String indicatorID,int recordType,String sourceRecordData,String recordData);

    //根据模板ID获得所有的指标的ID
    @Select("select indicatorID from templateindicator where templateID=#{templateID}")
    List<String> getIndicatorsByTemplateID(String templateID);

    //获得详细的指标信息
    @Select("select * from indicator where indicatorID=#{id}")
    Indicator getIndicatorDetailedByID(String id);

    //根据报表名和相对应的指标来查找record
    @Select("select * from record where reportID=#{reportID} and indicatorID=#{indicatorID}")
    Record getRecordByReportIDAndIndicatorID(String reportID,String indicatorID);

    //更新当前的数据
    @Update("update record set recordData=#{recordData} where recordID=#{recordID}")
    void updateRecordData(String recordID,String recordData);

    @Update("update record set sourceRecordData=#{srcData} where recordID=#{recordID}")
    void updateSourceData(String recordID,String srcData);

    //
    @Update("update report set checkState=#{checkState} where reportID=#{reportID}")
    void updateCheckState(String reportID,int checkState);

    //根据公司名查找出来所有的数据录入员的账号
    @Select("select userID from user where clientID=#{clientID} and userAuth=2")
    List<String> getEntryStaffByClientID(String clientID);

    //找到数据录入员已经提交过的所有表
    @Select("select * from report where reporterID=#{reporter} and commitState=1")
    List<Report> getReportsCommittedByReporterID(String reporter);
}
