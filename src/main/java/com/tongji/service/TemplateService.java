package com.tongji.service;

import com.tongji.domain.Template;

import java.util.List;

public interface TemplateService
{
    void deleteTemplateByClientID(String clientID);

    void create(String toString, String templateName, String templateInfo, String clientID);

    List<Template> findTemplatesByLike(String s);

    List<Template> selectTemplatesByClient(String clientID);

    boolean deleteTemplate(String templateID);

    boolean findTemplateByID(String templateID);

    boolean findTemplateByName(String templateName);
}
