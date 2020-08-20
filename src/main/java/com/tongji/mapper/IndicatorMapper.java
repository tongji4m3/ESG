package com.tongji.mapper;

import com.tongji.domain.Client;
import com.tongji.domain.Indicator;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

//负责Indicator,Template_Indicator的操作
@Mapper
@Repository
public interface IndicatorMapper
{
    @Delete("delete from templateindicator where templateID=#{templateID} and indicatorID=#{indicatorID}")
    void deleteTemplateIndicators(String templateID, String indicatorID);

    @Insert("insert into templateindicator values (#{indicatorID},#{templateID})")
    void addTemplateIndicator(String templateID, String indicatorID);

    @Select("select parent from indicator where indicatorID=#{indicatorID}")
    String getParent(String indicatorID);

    @Select("select * from templateindicator where templateID=#{templateID}")
    List<String> getIndicatorsID(String templateID);

    @Select("select * from indicator where indicatorID=#{indicatorID}")
    Indicator findIndicator(String indicatorID);

    @Select("select * from indicator where indicatorID=#{indicatorID} and level=5")
    Indicator isFiveLevelIndicator(String indicatorID);
}
