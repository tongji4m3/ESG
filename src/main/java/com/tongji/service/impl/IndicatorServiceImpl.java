package com.tongji.service.impl;

import com.tongji.domain.Indicator;
import com.tongji.mapper.IndicatorMapper;
import com.tongji.service.IndicatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;


@Service
public class IndicatorServiceImpl implements IndicatorService
{
    @Autowired
    private IndicatorMapper indicatorMapper;

    @Override
    public void deleteTemplateIndicators(String templateID, String indicatorID)
    {
        indicatorMapper.deleteTemplateIndicators(templateID, indicatorID);
    }

    @Override
    public void addTemplateIndicator(String templateID, String indicatorID)
    {
        indicatorMapper.addTemplateIndicator(templateID, indicatorID);
    }

    @Override
    public List<Indicator> getIndicator(String indicatorID)
    {
        List<Indicator> indicators = new LinkedList<>();
        while (indicatorID!=null && !indicatorID.equals(""))
        {
            Indicator indicator=getInformation(indicatorID);
            indicatorID = getParent(indicatorID);
            indicators.add(indicator);
        }
        return indicators;
    }

    @Override
    public List<String> getIndicatorsID(String templateID)
    {
        return indicatorMapper.getIndicatorsID(templateID);
    }

    @Override
    public boolean findIndicator(String indicatorID)
    {
        return indicatorMapper.findIndicator(indicatorID)!=null;
    }

    @Override
    public boolean isFiveLevelIndicator(String indicatorID)
    {
        return indicatorMapper.isFiveLevelIndicator(indicatorID)!=null;
    }

    private String getParent(String indicatorID)
    {
        return indicatorMapper.getParent(indicatorID);
    }

    private Indicator getInformation(String indicatorID)
    {
        return indicatorMapper.findIndicator(indicatorID);
    }
}
