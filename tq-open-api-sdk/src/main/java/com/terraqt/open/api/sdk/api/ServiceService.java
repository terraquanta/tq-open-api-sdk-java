package com.terraqt.open.api.sdk.api;

import com.terraqt.open.api.sdk.annotation.Api;
import com.terraqt.open.api.sdk.dto.service.ServiceDetailDto;
import com.terraqt.open.api.sdk.dto.service.ServiceDto;

import java.util.List;

public interface ServiceService {
    @Api(path = "/v1/service/getAllServices")
    List<ServiceDto> getAllServices();

    @Api(path = "/v1/service/getServiceDetail")
    ServiceDetailDto getServiceDetail(String id);
}
