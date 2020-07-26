package com.tongji.domain;

import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

/*
封装了返回信息
 */
@Repository
public class ReturnInfo
{
    private int status;//内部的状态,例如用户名已经存在之类的
    private String msg;//返回的信息,例如"用户名已经存在"
    private Map<String, Object> info;//封装要返回的对象

    //要返回的有用数据放在这
    public void put(String description,Object object)
    {
        info.put(description, object);
    }

    public ReturnInfo(int status, String msg)
    {
        info = new HashMap<>();
        this.status = status;
        this.msg = msg;
    }

    public ReturnInfo()
    {
        info = new HashMap<>();
        status = 1;
        msg = "ok";
    }

    public int getStatus()
    {
        return status;
    }

    public void setStatus(int status)
    {
        this.status = status;
    }

    public Map<String, Object> getInfo()
    {
        return info;
    }

    public void setInfo(Map<String, Object> info)
    {
        this.info = info;
    }

    public String getMsg()
    {
        return msg;
    }

    public void setMsg(String msg)
    {
        this.msg = msg;
    }
}
