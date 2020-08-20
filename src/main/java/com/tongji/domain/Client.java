package com.tongji.domain;

import java.io.Serializable;

public class Client implements Serializable
{
    String clientID;
    String clientName;
    String clientLocation;
    String clientIntro;

    @Override
    public String toString()
    {
        return "Client{" +
                "clientID='" + clientID + '\'' +
                ", clientName='" + clientName + '\'' +
                ", clientLocation='" + clientLocation + '\'' +
                ", clientIntro='" + clientIntro + '\'' +
                '}';
    }

    public Client(String clientID, String clientName, String clientLocation, String clientIntro)
    {
        this.clientID = clientID;
        this.clientName = clientName;
        this.clientLocation = clientLocation;
        this.clientIntro = clientIntro;
    }

    public String getClientID()
    {
        return clientID;
    }

    public void setClientID(String clientID)
    {
        this.clientID = clientID;
    }

    public String getClientName()
    {
        return clientName;
    }

    public void setClientName(String clientName)
    {
        this.clientName = clientName;
    }

    public String getClientLocation()
    {
        return clientLocation;
    }

    public void setClientLocation(String clientLocation)
    {
        this.clientLocation = clientLocation;
    }

    public String getClientIntro()
    {
        return clientIntro;
    }

    public void setClientIntro(String clientIntro)
    {
        this.clientIntro = clientIntro;
    }
}
