package com.tongji.mapper;

import com.tongji.domain.Template;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface TemplateMapper
{
    @Insert("insert into template(templateID,templateName,templateInfo,clientID) values(#{templateID},#{templateName},#{templateInfo},#{clientID})")
    void create(String templateID, String templateName, String templateInfo, String clientID);

    @Select("select * from template where templateName like #{templateName}")
    List<Template> findTemplatesByLike(String likeName);

    @Select("select * from template where clientID=#{clientID}")
    List<Template> selectTemplatesByClient(String clientID);

    @Delete("delete from template where templateID=#{templateID}")
    int deleteTemplate(String templateID);

    @Select("select * from template where templateID=#{templateID}")
    Template findTemplateByID(String templateID);

    @Delete("delete from templateindicator where templateID=#{templateID}")
    void deleteTemplateIndicators(String templateID);

    @Select("select * from template where templateName=#{templateName}")
    Template findTemplateByName(String templateName);
}
