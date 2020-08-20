package com.tongji.domain;

import java.util.HashMap;
import java.util.Map;

public class ReturnInfo
{
    private Integer status;
    private String message;
    private Map<String,Object> data=new HashMap<>();

    public ReturnInfo()
    {
        status = 1;
        message = "ok";
    }

    public ReturnInfo(Integer status, String message)
    {
        this.status = status;
        this.message = message;
    }

    public Integer getStatus()
    {
        return status;
    }

    public void setStatus(Integer status)
    {
        this.status = status;
    }

    public String getMessage()
    {
        return message;
    }

    public void setMessage(String message)
    {
        this.message = message;
    }

    public Object getData()
    {
        return data;
    }

    public void putData(String name,Object data)
    {
        this.data.put(name,data);
    }
}
