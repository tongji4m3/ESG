package com.tongji.domain;

import java.io.Serializable;

public class Indicator implements Serializable
{
    private String indicatorID;
    private String parent;
    private int level;
    private String indicatorName;
    private String description;
    private String ESGID;
    private int indicatorType;
    private int frequency;
    private int yearTotalType;
    private String sourceESGID;
    private String coefficientID;
    private String unitID;

    public Indicator()
    {
    }

    @Override
    public String toString()
    {
        return "Indicator{" +
                "indicatorID='" + indicatorID + '\'' +
                ", parent='" + parent + '\'' +
                ", level=" + level +
                ", indicatorName='" + indicatorName + '\'' +
                ", description='" + description + '\'' +
                ", ESGID='" + ESGID + '\'' +
                ", indicatorType=" + indicatorType +
                ", frequency=" + frequency +
                ", yearTotalType=" + yearTotalType +
                ", sourceESGID='" + sourceESGID + '\'' +
                ", coefficientID='" + coefficientID + '\'' +
                ", unitID='" + unitID + '\'' +
                '}';
    }

    public String getIndicatorID()
    {
        return indicatorID;
    }

    public void setIndicatorID(String indicatorID)
    {
        this.indicatorID = indicatorID;
    }

    public String getParent()
    {
        return parent;
    }

    public void setParent(String parent)
    {
        this.parent = parent;
    }

    public int getLevel()
    {
        return level;
    }

    public void setLevel(int level)
    {
        this.level = level;
    }

    public String getIndicatorName()
    {
        return indicatorName;
    }

    public void setIndicatorName(String indicatorName)
    {
        this.indicatorName = indicatorName;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public String getESGID()
    {
        return ESGID;
    }

    public void setESGID(String ESGID)
    {
        this.ESGID = ESGID;
    }

    public int getIndicatorType()
    {
        return indicatorType;
    }

    public void setIndicatorType(int indicatorType)
    {
        this.indicatorType = indicatorType;
    }

    public int getFrequency()
    {
        return frequency;
    }

    public void setFrequency(int frequency)
    {
        this.frequency = frequency;
    }

    public int getYearTotalType()
    {
        return yearTotalType;
    }

    public void setYearTotalType(int yearTotalType)
    {
        this.yearTotalType = yearTotalType;
    }

    public String getSourceESGID()
    {
        return sourceESGID;
    }

    public void setSourceESGID(String sourceESGID)
    {
        this.sourceESGID = sourceESGID;
    }

    public String getCoefficientID()
    {
        return coefficientID;
    }

    public void setCoefficientID(String coefficientID)
    {
        this.coefficientID = coefficientID;
    }

    public String getUnitID()
    {
        return unitID;
    }

    public void setUnitID(String unitID)
    {
        this.unitID = unitID;
    }
}
