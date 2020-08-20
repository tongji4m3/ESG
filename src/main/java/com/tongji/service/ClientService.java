package com.tongji.service;

import com.tongji.domain.Client;

import java.util.List;

public interface ClientService
{
    void create(Client client);


    boolean delete(String clientId);

    List<Client> findAll();

    boolean findByName(String clientName);

    String findClientName(String clientID);
}
