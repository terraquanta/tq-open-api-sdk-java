package com.terraqt.open.api.sdk.api;

import com.terraqt.open.api.sdk.annotation.Api;
import com.terraqt.open.api.sdk.dto.batch.BatchDto;

import java.util.List;

public interface BatchService {

    @Api(path = "/v1/batch/getBatches")
    List<BatchDto> getBatches(String id);
}
