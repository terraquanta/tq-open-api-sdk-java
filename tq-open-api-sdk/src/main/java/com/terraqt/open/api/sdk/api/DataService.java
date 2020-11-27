package com.terraqt.open.api.sdk.api;

import com.terraqt.open.api.sdk.annotation.Api;
import com.terraqt.open.api.sdk.dto.stats.StatsDto;

import java.util.List;

public interface DataService {

    @Api(path = "/v1/data/getTiff")
    String getTiff(String batchId);

    @Api(path = "/v1/data/getStats")
    List<StatsDto> getStats(String batchId);

    @Api(path = "/v1/data/getGeo")
    String getGeo(String batchId);

    @Api(path = "/v1/data/getImage")
    String getImage(String batchId);
}
