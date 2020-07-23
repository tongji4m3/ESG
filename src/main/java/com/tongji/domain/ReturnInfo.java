package com.tongji.domain;

import org.springframework.stereotype.Repository;

/*
封装了返回信息
 */
@Repository
public class ReturnInfo
{
    private int status;//内部的状态,例如用户名已经存在之类的
    private Object object;//封装要返回的对象
    private String msg;//返回的信息,例如"用户名已经存在"

    public ReturnInfo(int status, Object object, String msg)
    {
        this.status = status;
        this.object = object;
        this.msg = msg;
    }

    public ReturnInfo()
    {
    }

    public int getStatus()
    {
        return status;
    }

    public void setStatus(int status)
    {
        this.status = status;
    }

    public Object getObject()
    {
        return object;
    }

    public void setObject(Object object)
    {
        this.object = object;
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
