package com.tongji.domain;

import java.io.Serializable;

public class Template implements Serializable
{
    private String templateID;
    private String templateName;
    private String templateInfo;
    private String clientID;

    public Template()
    {
    }

    @Override
    public String toString()
    {
        return "Template{" +
                "templateID='" + templateID + '\'' +
                ", templateName='" + templateName + '\'' +
                ", templateInfo='" + templateInfo + '\'' +
                ", clientID='" + clientID + '\'' +
                '}';
    }

    public String getTemplateID()
    {
        return templateID;
    }

    public void setTemplateID(String templateID)
    {
        this.templateID = templateID;
    }

    public String getTemplateName()
    {
        return templateName;
    }

    public void setTemplateName(String templateName)
    {
        this.templateName = templateName;
    }

    public String getTemplateInfo()
    {
        return templateInfo;
    }

    public void setTemplateInfo(String templateInfo)
    {
        this.templateInfo = templateInfo;
    }

    public String getClientID()
    {
        return clientID;
    }

    public void setClientID(String clientID)
    {
        this.clientID = clientID;
    }

    public Template(String templateID, String templateName, String templateInfo, String clientID)
    {
        this.templateID = templateID;
        this.templateName = templateName;
        this.templateInfo = templateInfo;
        this.clientID = clientID;
    }
}
