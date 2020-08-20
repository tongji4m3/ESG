package com.tongji.domain;

import java.io.Serializable;

public class Report implements Serializable
{
    private String recordID;
    private String templateID;
    private String reporterID;
    private int reportYear;
    private int reportMonth;
    private int commitState;
    private int checkState;

    public Report(){}

    public Report(String recordID, String templateID,
                  String reporterID, int reportYear,
                  int reportMonth, int commitState, int checkState) {
        this.recordID = recordID;
        this.templateID = templateID;
        this.reporterID = reporterID;
        this.reportYear = reportYear;
        this.reportMonth = reportMonth;
        this.commitState = commitState;
        this.checkState = checkState;
    }

    @Override
    public String toString() {
        return "Report{" +
                "recordID='" + recordID + '\'' +
                ", templateID='" + templateID + '\'' +
                ", reporterID='" + reporterID + '\'' +
                ", reportYear=" + reportYear +
                ", reportMonth=" + reportMonth +
                ", commitState=" + commitState +
                ", checkState=" + checkState +
                '}';
    }

    public String getRecordID() {
        return recordID;
    }

    public void setRecordID(String recordID) {
        this.recordID = recordID;
    }

    public String getTemplateID() {
        return templateID;
    }

    public void setTemplateID(String templateID) {
        this.templateID = templateID;
    }

    public String getReporterID() {
        return reporterID;
    }

    public void setReporterID(String reporterID) {
        this.reporterID = reporterID;
    }

    public int getReportYear() {
        return reportYear;
    }

    public void setReportYear(int reportYear) {
        this.reportYear = reportYear;
    }

    public int getReportMonth() {
        return reportMonth;
    }

    public void setReportMonth(int reportMonth) {
        this.reportMonth = reportMonth;
    }

    public int getCommitState() {
        return commitState;
    }

    public void setCommitState(int commitState) {
        this.commitState = commitState;
    }

    public int getCheckState() {
        return checkState;
    }

    public void setCheckState(int checkState) {
        this.checkState = checkState;
    }
}
