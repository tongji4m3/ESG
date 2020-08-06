package com.tongji.domain;

import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository
public class User implements Serializable
{
    private String userID;
    private String userAccount;
    private String userPassword;
    private int userAuth;
    private String clientID;

    public User()
    {
    }

    @Override
    public String toString()
    {
        return "User{" +
                "userID='" + userID + '\'' +
                ", userAccount='" + userAccount + '\'' +
                ", userPassword='" + userPassword + '\'' +
                ", userAuth=" + userAuth +
                ", clientID='" + clientID + '\'' +
                '}';
    }

    public String getUserID()
    {
        return userID;
    }

    public void setUserID(String userID)
    {
        this.userID = userID;
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

    public String getClientID()
    {
        return clientID;
    }

    public void setClientID(String clientID)
    {
        this.clientID = clientID;
    }
}
