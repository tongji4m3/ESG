package com.tongji.domain;

import java.io.Serializable;

public class Record implements Serializable
{
    private String recordID;
    private String reportID;
    private String indicatorID;
    private int recordType;
    private String sourceRecordData;
    private String recordData;

    public Record(){

    }

    public Record(String recordID, String reportID, String indicatorID, int recordType, String sourceRecordData, String recordData) {
        this.recordID = recordID;
        this.reportID = reportID;
        this.indicatorID = indicatorID;
        this.recordType = recordType;
        this.sourceRecordData = sourceRecordData;
        this.recordData = recordData;
    }

    @Override
    public String toString() {
        return "Record{" +
                "recordID='" + recordID + '\'' +
                ", reportID='" + reportID + '\'' +
                ", indicatorID='" + indicatorID + '\'' +
                ", recordType=" + recordType +
                ", sourceRecordData='" + sourceRecordData + '\'' +
                ", recordData='" + recordData + '\'' +
                '}';
    }

    public String getRecordID() {
        return recordID;
    }

    public void setRecordID(String recordID) {
        this.recordID = recordID;
    }

    public String getReportID() {
        return reportID;
    }

    public void setReportID(String reportID) {
        this.reportID = reportID;
    }

    public String getIndicatorID() {
        return indicatorID;
    }

    public void setIndicatorID(String indicatorID) {
        this.indicatorID = indicatorID;
    }

    public int getRecordType() {
        return recordType;
    }

    public void setRecordType(int recordType) {
        this.recordType = recordType;
    }

    public String getSourceRecordData() {
        return sourceRecordData;
    }

    public void setSourceRecordData(String sourceRecordData) {
        this.sourceRecordData = sourceRecordData;
    }

    public String getRecordData() {
        return recordData;
    }

    public void setRecordData(String recordData) {
        this.recordData = recordData;
    }
}
