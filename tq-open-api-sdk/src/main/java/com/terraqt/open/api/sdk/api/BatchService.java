package com.terraqt.open.api.sdk.api;

import com.terraqt.open.api.sdk.annotation.Api;
import com.terraqt.open.api.sdk.dto.batch.PaginationBatchDto;
import com.terraqt.open.api.sdk.dto.common.Paging;

public interface BatchService {

    @Api(path = "/v1/batch/getBatchesWithPaging")
    PaginationBatchDto getBatchesWithPaging(String id, Paging paging);
}
