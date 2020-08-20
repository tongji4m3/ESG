package com.tongji.mapper;

import com.tongji.domain.Client;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ClientMapper
{
    @Insert("insert into client(clientID,clientName,clientLocation,clientIntro) values(#{clientID},#{clientName},#{clientLocation},#{clientIntro})")
    void create(Client client);

    @Select("select * from client")
    List<Client> findAll();

    @Select("select * from client where clientName=#{clientName}")
    Client findUserByUsername(String clientName);

    @Delete("delete from client where clientID=#{clientID}")
    int delete(String clientID);
}
