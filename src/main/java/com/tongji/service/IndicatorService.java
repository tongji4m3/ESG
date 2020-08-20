package com.tongji.service;


import com.tongji.domain.Indicator;

import java.util.List;

public interface IndicatorService
{
    void deleteTemplateIndicators(String templateID, String indicatorID);

    void addTemplateIndicator(String templateID, String indicatorID);

    List<Indicator> getIndicator(String indicatorID);

    List<String> getIndicatorsID(String templateID);

    boolean findIndicator(String indicatorID);

    boolean isFiveLevelIndicator(String indicatorID);
}
