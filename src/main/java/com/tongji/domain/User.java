package com.tongji.domain;

import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository
public class User implements Serializable
{
    private String userId;
    private String userAccount;
    private String userPassword;
    private int userAuth;
    private String clientId;

    public User()
    {
    }



    public String getUserId()
    {
        return userId;
    }

    public void setUserId(String userId)
    {
        this.userId = userId;
    }

    public String getUserAccount()
    {
        return userAccount;
    }

    public void setUserAccount(String userAccount)
    {
        this.userAccount = userAccount;
    }

    public String getUserPassword()
    {
        return userPassword;
    }

    public void setUserPassword(String userPassword)
    {
        this.userPassword = userPassword;
    }

    public int getUserAuth()
    {
        return userAuth;
    }

    public void setUserAuth(int userAuth)
    {
        this.userAuth = userAuth;
    }

    public String getClientId()
    {
        return clientId;
    }

    public void setClientId(String clientId)
    {
        this.clientId = clientId;
    }

    @Override
    public String toString()
    {
        return "User{" +
                "userId=" + userId +
                ", userAccount='" + userAccount + '\'' +
                ", userPassword='" + userPassword + '\'' +
                ", userAuth=" + userAuth +
                ", clientId='" + clientId + '\'' +
                '}';
    }
}
