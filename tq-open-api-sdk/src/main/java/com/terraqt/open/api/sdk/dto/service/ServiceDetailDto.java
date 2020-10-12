package com.terraqt.open.api.sdk.dto.service;

import com.terraqt.open.api.sdk.dto.common.CdpDto;
import com.terraqt.open.api.sdk.dto.common.ShapeDto;
import com.terraqt.open.api.sdk.dto.common.TimeRangeDto;
import lombok.Data;

import java.util.List;

@Data
public class ServiceDetailDto {
    private ShapeDto shape;
    private TimeRangeDto timeRange;
    private List<CdpDto> cdps;
}
