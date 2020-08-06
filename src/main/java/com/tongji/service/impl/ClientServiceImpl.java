package com.tongji.service.impl;

import com.tongji.domain.Client;
import com.tongji.mapper.ClientMapper;
import com.tongji.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientServiceImpl implements ClientService
{
    @Autowired
    private ClientMapper clientMapper;

    @Override
    public void create(Client client)
    {
        clientMapper.create(client);
    }

    @Override
    public boolean delete(String clientID)
    {
        int result=clientMapper.delete(clientID);
        return result == 1;//删除成功
    }

    @Override
    public List<Client> findAll()
    {
        return clientMapper.findAll();
    }

    @Override
    public boolean findByName(String clientName)
    {
        Client client = clientMapper.findUserByUsername(clientName);
        return client != null;//用户存在
    }
}
