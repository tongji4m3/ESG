package com.tongji.service.impl;

import com.tongji.domain.Template;
import com.tongji.mapper.IndicatorMapper;
import com.tongji.service.TemplateService;
import com.tongji.mapper.TemplateMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TemplateServiceImpl implements TemplateService
{
    @Autowired
    private TemplateMapper templateMapper;

    @Override
    public void deleteTemplateByClientID(String clientID)
    {
        //先找到该公司的所有模板
        List<Template> templates = templateMapper.selectTemplatesByClient(clientID);
        //再删除所有模板
        for (Template template : templates)
        {
            deleteTemplate(template.getTemplateID());
        }
    }

    @Override
    public void create(String templateID, String templateName, String templateInfo, String clientID)
    {
        templateMapper.create(templateID,templateName,templateInfo,clientID);
    }

    @Override
    public List<Template> findTemplatesByLike(String likeName)
    {
        return templateMapper.findTemplatesByLike(likeName);
    }

    @Override
    public List<Template> selectTemplatesByClient(String clientID)
    {
        return templateMapper.selectTemplatesByClient(clientID);
    }

    @Override
    public boolean deleteTemplate(String templateID)
    {
        //不仅仅要删除模板,还要删除模板指标表中的对应的数据
        int result=templateMapper.deleteTemplate(templateID);
        if(result==1)//证明存在
        {
            templateMapper.deleteTemplateIndicators(templateID);
            return true;
        }
        return false;
    }

    @Override
    public boolean findTemplateByID(String templateID)
    {
        return templateMapper.findTemplateByID(templateID)!=null;
    }

    @Override
    public boolean findTemplateByName(String templateName)
    {
        return templateMapper.findTemplateByName(templateName)!=null;
    }
}
